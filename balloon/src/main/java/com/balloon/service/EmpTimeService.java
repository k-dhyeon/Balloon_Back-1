package com.balloon.service;

import java.util.List;

import com.balloon.dto.EmpTimeDTO;

public interface EmpTimeService {

	public List<EmpTimeDTO> findEmpTimeList();

	public Integer findWorkIn(String empId);

	public boolean startWork(String empId);

	public boolean endWork(String empId);

//	public boolean endlessWork(String empId);
}
