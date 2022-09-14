package com.balloon.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.EmpTimeDTO;
import com.balloon.service.EmpTimeServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/time")
@RequiredArgsConstructor
public class EmpTimeRestController {

	private final EmpTimeServiceImpl empTimeSvc;

	@GetMapping("/list")
	public List<EmpTimeDTO> findEmpTimeList() throws Exception {

		List<EmpTimeDTO> empTimeDTOList = empTimeSvc.findEmpTimeList();
		if (empTimeDTOList == null) {
			throw new Exception("리스트 값이 없습니다.");
		} else {
			return empTimeDTOList;
		}
	};

	@GetMapping("/on/{empId}")
	public Integer findWorkOn(@Valid @PathVariable(value = "empId") String empId) {
		try {
			if (empId == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}
			return empTimeSvc.findWorkOn(empId);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@GetMapping("/off/{empId}")
	public Integer findWorkOff(@Valid @PathVariable(value = "empId") String empId) {
		try {
			if (empId == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}

			return empTimeSvc.findWorkOff(empId);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping(value = "/in/{empId}")
	public boolean findWorkIn(@Valid @PathVariable(value = "empId") String empId) {
		try {
			if (empId == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}

			Integer workStatus = empTimeSvc.findWorkIn(empId);
			if (workStatus == 1) {
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	@PostMapping(value = "/attendance/{empId}")
	public boolean startWork(@Valid @PathVariable String empId) {

		try {
			if (empId == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}

			boolean success = empTimeSvc.startWork(empId);
			return success;
		} catch (Exception e) {
			e.getMessage();
		}

		return false;
	}

	@PutMapping(value = "/leave/{empId}")
	public boolean endWork(@Valid @PathVariable String empId) {

		try {
			if (empId == null) {
				throw new Exception("입력받은 값이 없습니다.");
			}

			boolean success = empTimeSvc.endWork(empId);
			return success;
		} catch (Exception e) {
			e.getMessage();
		}

		return true;
	}

}
