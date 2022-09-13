package com.balloon.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.balloon.entity.Employee;
import com.balloon.entity.EmployeeTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpTimeDTO {

	private Employee empId;

	private LocalDate workDate;

	private LocalDateTime workTime;

	private LocalDateTime outTime;

	private String attendance;

	private String leaveWork;

	public EmployeeTime toEntity(EmpTimeDTO empTimeDTO) {
		EmployeeTime empTimeEntity = EmployeeTime.builder().empId(empTimeDTO.getEmpId())
				.workDate(empTimeDTO.getWorkDate()).workTime(empTimeDTO.getWorkTime()).outTime(empTimeDTO.getOutTime())
				.attendance(empTimeDTO.getAttendance()).leaveWork(empTimeDTO.getLeaveWork()).build();

		return empTimeEntity;

	}
}
