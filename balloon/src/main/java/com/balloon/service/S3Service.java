package com.balloon.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

	public String uploadProfile(MultipartFile multiFile, String empId);

	public byte[] downloadFile(String fileName);

	public String deleteFile(String fileName);
}
