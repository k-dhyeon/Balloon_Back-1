package com.balloon.service;

import java.util.List;

import com.balloon.dto.ChatroomEmployeeDTO;

public interface ChatREmpService {

	public List<ChatroomEmployeeDTO> getallChatEmp(String empId);

	public List<ChatroomEmployeeDTO> getChatroomEmp(Long chatroomId);

	public List<ChatroomEmployeeDTO> getSchBotchatroom(List<String> empIds);

	public List<ChatroomEmployeeDTO> getAprlBotchatroom(List<String> empIds);

	public List<ChatroomEmployeeDTO> getInsertChatEmp(List<ChatroomEmployeeDTO> chatroomEmployeeDTO);

	public void getdeleteChatroom(Long chatroomId, String empId);

}