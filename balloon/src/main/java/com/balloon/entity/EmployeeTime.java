package com.balloon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.EmpTimeDTO;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "employee_time")
@EntityListeners(AuditingEntityListener.class)
@IdClass(EmployeeTimeId.class)
@DynamicInsert
public class EmployeeTime {

	@Id
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "emp_id")
	private Employee empId;

	@Id
//	@CreatedDate
	@Column(name = "work_date")
	private LocalDate workDate;

	@NotNull
//	@CreatedDate
	@Column(name = "work_time")
	private LocalDateTime workTime;

	@Column(name = "out_time")
	private LocalDateTime outTime;

	@Column(name = "attendance")
	private String attendance;

	@Column(name = "leave_work")
	private String leaveWork;

	public EmpTimeDTO toDTO(EmployeeTime empTimeEntity) {
		EmpTimeDTO empTimeDTO = EmpTimeDTO.builder().empId(empTimeEntity.getEmpId())
				.workDate(empTimeEntity.getWorkDate()).workTime(empTimeEntity.getWorkTime())
				.outTime(empTimeEntity.getOutTime()).attendance(empTimeEntity.getAttendance())
				.leaveWork(empTimeEntity.getLeaveWork()).build();

		return empTimeDTO;
	}

}
