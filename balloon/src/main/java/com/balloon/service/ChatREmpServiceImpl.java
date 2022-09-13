package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.balloon.dto.ChatroomDTO;
import com.balloon.dto.ChatroomEmployeeDTO;
import com.balloon.dto.EmpDTO;
import com.balloon.entity.ChatroomEmployee;
import com.balloon.entity.ChatroomEmployeeId;
import com.balloon.repository.ChatREmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatREmpServiceImpl implements ChatREmpService {

	private final ChatREmpRepository chatREmpRepository;

	@Override
	@Transactional
	public List<ChatroomEmployeeDTO> getallChatEmp(String empId) {
		List<ChatroomEmployeeDTO> chatroomEmployeeDTOList = new ArrayList<ChatroomEmployeeDTO>();
		List<ChatroomEmployee> chatroomEmployeeEntityList = chatREmpRepository.findAll(empId);
		for (ChatroomEmployee chatroomEmployeeEntity : chatroomEmployeeEntityList) {
			chatroomEmployeeDTOList.add(chatroomEmployeeEntity.toDTO(chatroomEmployeeEntity));
		}
		return chatroomEmployeeDTOList;
	}

	@Override
	public List<ChatroomEmployeeDTO> getChatroomEmp(Long chatroomId) {
		List<ChatroomEmployeeDTO> chatroomEmployeeDTOList = new ArrayList<ChatroomEmployeeDTO>();
		List<ChatroomEmployee> chatroomEmployeeEntityList = chatREmpRepository
				.findAllByChatroomIdChatroomId(chatroomId);
		for (ChatroomEmployee chatroomEmployeeEntity : chatroomEmployeeEntityList) {
			chatroomEmployeeDTOList.add(chatroomEmployeeEntity.toDTO(chatroomEmployeeEntity));
		}
		return chatroomEmployeeDTOList;
	}

	@Override
	@Transactional
	public List<ChatroomEmployeeDTO> getSchBotchatroom(List<String> empId) {
		List<ChatroomEmployeeDTO> chatroomEmployeeDTOList = new ArrayList<ChatroomEmployeeDTO>();
		List<ChatroomEmployee> chatroomEmployeeEntityList = chatREmpRepository.findChatroomEmployeeByempId(empId);
		for (ChatroomEmployee chatroomEmployeeEntity : chatroomEmployeeEntityList) {
			chatroomEmployeeDTOList.add(chatroomEmployeeEntity.toDTO(chatroomEmployeeEntity));
		}
		return chatroomEmployeeDTOList;
	}

	@Override
	@Transactional
	public List<ChatroomEmployeeDTO> getAprlBotchatroom(List<String> empId) {
		List<ChatroomEmployeeDTO> chatroomEmployeeDTOList = new ArrayList<ChatroomEmployeeDTO>();
		List<ChatroomEmployee> chatroomEmployeeEntityList = chatREmpRepository.findChatroomEmployeeByempId2(empId);
		for (ChatroomEmployee chatroomEmployeeEntity : chatroomEmployeeEntityList) {
			chatroomEmployeeDTOList.add(chatroomEmployeeEntity.toDTO(chatroomEmployeeEntity));
		}
		return chatroomEmployeeDTOList;
	}

	@Override
	@Transactional
	public List<ChatroomEmployeeDTO> getInsertChatEmp(List<ChatroomEmployeeDTO> chatroomEmployeeDTO) {
		List<ChatroomEmployeeDTO> chatroomEmployeeDTOList = new ArrayList<ChatroomEmployeeDTO>();
		List<ChatroomEmployee> chatroomEmployeeEntityList = new ArrayList<ChatroomEmployee>();
		for (ChatroomEmployeeDTO chatEmpDTO : chatroomEmployeeDTO) {
			chatroomEmployeeEntityList.add(chatEmpDTO.toEntity(chatEmpDTO));
		}
		chatroomEmployeeEntityList = chatREmpRepository.saveAll(chatroomEmployeeEntityList);
		for (ChatroomEmployee chatroomEmployeeEntity : chatroomEmployeeEntityList) {
			chatroomEmployeeDTOList.add(chatroomEmployeeEntity.toDTO(chatroomEmployeeEntity));
		}
		return chatroomEmployeeDTOList;

	}

	@Override
	@Transactional
	public void getdeleteChatroom(Long chatroomId, String empId) {
		ChatroomEmployeeDTO chatroomEmployeeDTO = new ChatroomEmployeeDTO();
		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(chatroomId);
		chatroomEmployeeDTO.setChatroomId(chatroomDTO);

		EmpDTO empDTO = new EmpDTO();
		empDTO.setEmpId(empId);
		chatroomEmployeeDTO.setEmpId(empDTO);

		ChatroomEmployeeId chatroomEmployeeIdEntity = chatroomEmployeeDTO.toId(chatroomEmployeeDTO);
		chatREmpRepository.deleteById(chatroomEmployeeIdEntity);
	}

}