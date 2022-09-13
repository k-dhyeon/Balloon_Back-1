package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.dto.EmpTimeDTO;
import com.balloon.entity.EmployeeTime;
import com.balloon.repository.EmpTimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpTimeServiceImpl implements EmpTimeService {

	private final EmpTimeRepository empTimeRepo;

	@Transactional(readOnly = true)
	@Override
	public List<EmpTimeDTO> findEmpTimeList() {
		List<EmployeeTime> empTimeEntityList = empTimeRepo.findAll();
		List<EmpTimeDTO> empTimeDTOList = new ArrayList<EmpTimeDTO>();

		empTimeEntityList.forEach(empTimeEntity -> empTimeDTOList.add(empTimeEntity.toDTO(empTimeEntity)));

		return empTimeDTOList;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer findWorkIn(String empId) {
		Integer workOn = empTimeRepo.findWorkOn(empId);
		Integer workOff = empTimeRepo.findWorkOff(empId);
		if (workOn == 1 && workOn > workOff) {
			return workOn;
		}
		return 0;
	}

	@Transactional
	@Override
	public boolean startWork(String empId) {

		if (empId != null) {
			empTimeRepo.startWork(empId);
			return true;
		}
		return false;
	}

	@Transactional
	@Override
	public boolean endWork(String empId) {
		if (empId != null) {
			empTimeRepo.endWork(empId);
			return false;
		}
		return true;
	}

//	@Transactional
//	@Override
//	public boolean endlessWork(String empId) {
//		if (empId != null) {
//			empTimeRepo.endlessWork(empId);
//			return true;
//		}
//		return false;
//	}

}
