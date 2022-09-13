package com.balloon.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3ServiceImpl implements S3Service {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	private final AmazonS3 s3Client;

	@Override
	public String uploadProfile(MultipartFile multiFile, String empId) {
		File file = convertMultipartFileToFile(multiFile);
		String fileName = System.currentTimeMillis() + "_" + multiFile.getOriginalFilename();

		s3Client.putObject(new PutObjectRequest(bucket, "images/" + fileName, file));

		file.delete();

		return "File uploaded : " + fileName;
	}

	@Override
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucket, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();

		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucket, fileName);
		return fileName + " removed!";
	}

	private File convertMultipartFileToFile(MultipartFile multiFile) {
		File convertedFile = new File(multiFile.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(multiFile.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
}
