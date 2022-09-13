package com.balloon.entity;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTimeId implements Serializable {

	private String empId;

	private LocalDate workDate;
}
