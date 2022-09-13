package com.balloon.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChatroomDTO;
import com.balloon.dto.ChatroomEmployeeDTO;
import com.balloon.service.ChatREmpServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cre")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://15.164.224.26:8080" })

public class ChatREmpRestController {

	private final ChatREmpServiceImpl chatREmpServicImpl;

	// headCount 2인 채팅방 목록 불러오기
	@GetMapping(value = "/allchatemp/{empId}")
	public List<ChatroomEmployeeDTO> allChatEmp(@PathVariable String empId) {
		List<ChatroomEmployeeDTO> chatroomDTOList = chatREmpServicImpl.getallChatEmp(empId);
		return chatroomDTOList;
	}

	// chatroomEmployee T에 chatroomId로 사원정보 가져오기
	@GetMapping(value = "/onechatemp/{chatroomId}")
	public List<ChatroomEmployeeDTO> chatroomEmp(@PathVariable(value = "chatroomId") Long chatroomId) {
		return chatREmpServicImpl.getChatroomEmp(chatroomId);
	}

	// 이미 일정봇과 채팅이 존재하는 사원 찾기
	@PostMapping(value = "/schbotchatroom", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<ChatroomEmployeeDTO> calendarBot(@RequestBody List<String> empIds) {
		return chatREmpServicImpl.getSchBotchatroom(empIds);
	}

	// 이미 결재봇과 채팅이 존재하는 사원 찾기
	@PostMapping(value = "/apvlbotchatroom", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<ChatroomEmployeeDTO> approvalBot(@RequestBody List<String> empIds) {
		return chatREmpServicImpl.getAprlBotchatroom(empIds);
	}

	// chatroomEmployee T에 초대할 사람과 초대한 사람 넣어주기
	@PostMapping(value = "/insertchatemp/{chatroomId}")
	public List<ChatroomEmployeeDTO> insertChatEmpList(@PathVariable(name = "chatroomId") Long chatroomId,
			@RequestBody List<ChatroomEmployeeDTO> chatroomEmployeeDTO) {
		// 채팅방 id 값 넣기
		ChatroomDTO chatroomDTO = new ChatroomDTO();
		chatroomDTO.setChatroomId(chatroomId);
		// 모든 사원 리스트
		for (ChatroomEmployeeDTO chatroomEmpDTO : chatroomEmployeeDTO) {
			chatroomEmpDTO.setChatroomId(chatroomDTO);
		}
		return chatREmpServicImpl.getInsertChatEmp(chatroomEmployeeDTO);
	}

	// 채팅방 혼자 나가기
	@DeleteMapping(value = "/deleteroom/{chatroomId}/{empId}")
	public void deleteChat(@PathVariable("chatroomId") Long chatroomId, @PathVariable("empId") String empId) {
		chatREmpServicImpl.getdeleteChatroom(chatroomId, empId);
	}

}