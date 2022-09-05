package com.balloon.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.CalDTO;
import com.balloon.service.CalServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cal")
@RequiredArgsConstructor
@CrossOrigin(origins = { "http://localhost:3000" })
// CICD 테스트 주석임
public class CalRestController {

	private final CalServiceImpl calSvc;

	@GetMapping(value = "/list")
	public List<CalDTO> findAll() {

		return calSvc.findAll();
	}

	@GetMapping(value = "/all/{scheduleId}")
	public CalDTO CalByScheduleId(@PathVariable(name = "scheduleId") Long scheduleid) {
		System.out.println("스케쥬우우울");
		return calSvc.getCalByscheduleId(scheduleid);
	}

	//empId가져오기
	@GetMapping(value = "/{empId}")
	public List<CalDTO> CalByEmpId(@PathVariable(name = "empId") String empId) {
		System.out.println(empId);
		return calSvc.getCalByempId(empId);
	}

	@PostMapping(value = "/insert", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void CalByInsert(@Valid @RequestBody CalDTO calDTO) {
		System.out.println(calDTO);
		calSvc.insertBycal(calDTO);
	}

	@PostMapping(value = "/schedule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void scheduleListAdd(@RequestBody List<CalDTO> calDTOList) {
		calSvc.scheduleListAdd(calDTOList);
	}

	@PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void updateCalByscheduleId(@RequestBody CalDTO calDTO) {
		System.out.println(calDTO);
		try {
			calSvc.updateByCal(calDTO);
		} catch (Exception e) {
			System.out.println("에러에러");
			throw new NullPointerException("scheduleId is null.");
		}
	}

	@DeleteMapping(value = "/delete/{scheduleId}")
	public void scheduleIdDelete(@PathVariable(name = "scheduleId") Long scheduleid) {
		calSvc.deleteByCalId(scheduleid);
	}

}
