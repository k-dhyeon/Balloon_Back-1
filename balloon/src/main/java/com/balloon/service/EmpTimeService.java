package com.balloon.service;

import java.util.List;

import com.balloon.dto.EmpTimeDTO;

public interface EmpTimeService {

	public List<EmpTimeDTO> findEmpTimeList();

	public Integer findWorkOn(String empId);

	public Integer findWorkOff(String empId);

	public Integer findWorkIn(String empId);

	public boolean startWork(String empId);

	public boolean endWork(String empId);

//	public boolean endlessWork(String empId);
}
