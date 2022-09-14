package com.balloon.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.balloon.dto.EmpDTO;
import com.balloon.dto.EmpResByAdminDTO;
import com.balloon.dto.EmpResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Employee implements Persistable<String> {

	@Id
	@Column(name = "emp_id", length = 10)
	private String empId;

	@NotNull
	@Column(name = "password")
	private String password; // ??

	@NotNull
	@Column(name = "emp_name", length = 30)
	private String empName;

	@NotNull
	@Column(name = "position", length = 20)
	private String position;

	@NotNull
	@Column(name = "mobile", length = 15)
	private String mobile;

	@NotNull
	@Column(name = "hiredate")
	private LocalDate hiredate;

	@NotNull
	@Column(name = "salary")
	private Float salary;

	@Column(name = "commission")
	private Float commission;

	@NotNull
	@Column(name = "emp_mail", length = 30)
	private String empMail;

	@Column(name = "photo")
	private String photo;

	@NotNull
	@Column(name = "emp_bell", length = 15)
	private String empBell;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Column(name = "address", length = 100)
	private String address;

	@Column(name = "license_plate")
	private String licensePlate;

	@Column(name = "responsibility")
	private String responsibility;

	@NotNull
	@Column(name = "user_role_grade", length = 45)
	@Enumerated(EnumType.STRING)
	private UserRole userRoleGrade;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_code")
	private Unit unit;

	@OneToMany(mappedBy = "employee")
	@JsonIgnore
	List<Chat> chat = new ArrayList<Chat>();

	@OneToMany(mappedBy = "empId")
	@JsonIgnore
	List<ChatroomEmployee> chatroomEmployee = new ArrayList<ChatroomEmployee>();

	public void updatePassword(String password) {
		this.password = password;
	}

	public void updateEmpName(String empName) {
		this.empName = empName;
	}

	public EmpDTO toDTO(Employee employeeEntity) {
		EmpDTO employeeDTO = EmpDTO.builder().empId(employeeEntity.getEmpId()).password(employeeEntity.getPassword())
				.empName(employeeEntity.getEmpName()).position(employeeEntity.getPosition())
				.mobile(employeeEntity.getMobile()).hiredate(employeeEntity.getHiredate())
				.salary(employeeEntity.getSalary()).commission(employeeEntity.getCommission())
				.empMail(employeeEntity.getEmpMail()).photo(employeeEntity.getPhoto())
				.empBell(employeeEntity.getEmpBell()).birthday(employeeEntity.getBirthday())
				.address(employeeEntity.getAddress()).licensePlate(employeeEntity.getLicensePlate())
				.responsibility(employeeEntity.getResponsibility()).unit(employeeEntity.getUnit())
				.userRoleGrade(employeeEntity.getUserRoleGrade()).build();

		return employeeDTO;
	}

	public EmpResByAdminDTO toDTOByAdmin(Employee employeeEntity) {
		EmpResByAdminDTO employeeDTO = EmpResByAdminDTO.builder().empId(employeeEntity.getEmpId())
				.password(employeeEntity.getPassword()).empName(employeeEntity.getEmpName())
				.position(employeeEntity.getPosition()).responsibility(employeeEntity.getResponsibility())
				.salary(employeeEntity.getSalary()).commission(employeeEntity.getCommission())
				.photo(employeeEntity.getPhoto()).empBell(employeeEntity.getEmpBell()).unit(employeeEntity.getUnit())
				.userRoleGrade(employeeEntity.getUserRoleGrade()).build();

		return employeeDTO;
	}

	@Override
	public String getId() {
		return empId;
	}

	@Override
	public boolean isNew() {
		return hiredate == null;
	}

	public void updatePhoto(EmpDTO empDTO) {
		this.photo = empDTO.getPhoto();
	}

	/* 관리자가 사원 정보 수정 */
	public void updateEmpByAdmin(EmpResByAdminDTO empDTO, PasswordEncoder passwordEncoder) {
		this.empId = empDTO.getEmpId();
		if (empDTO.getPassword() != null) {
			this.password = passwordEncoder.encode(empDTO.getPassword());
		}
		this.empName = empDTO.getEmpName();
		this.position = empDTO.getPosition();
		this.responsibility = empDTO.getResponsibility();
		this.salary = empDTO.getSalary();
		this.commission = empDTO.getCommission();
		this.empBell = empDTO.getEmpBell();
		if (empDTO.getPhoto() != null) {
			this.photo = empDTO.getPhoto();
		}
		this.unit = empDTO.getUnit();
		this.userRoleGrade = empDTO.getUserRoleGrade();
	}

	public void updateEmpByUser(EmpResponseDTO empDTO) {
		this.mobile = empDTO.getMobile();
		this.empMail = empDTO.getEmpMail();
		this.address = empDTO.getAddress();
		this.licensePlate = empDTO.getLicensePlate();
	}

	public void updatePhotoByUser(String photo) {
		this.photo = photo;
	}

	public void updateEmpByPerssonelAppointment(EmpDTO empDTO) {
		this.unit = empDTO.getUnit();
		this.position = empDTO.getPosition();
	}

}
