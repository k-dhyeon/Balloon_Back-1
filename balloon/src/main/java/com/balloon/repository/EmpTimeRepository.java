package com.balloon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.entity.EmployeeTime;
import com.balloon.entity.EmployeeTimeId;

@Repository
public interface EmpTimeRepository extends JpaRepository<EmployeeTime, EmployeeTimeId> {

	@Query(value = "SELECT COUNT(*) FROM employee_time WHERE emp_id= :empId AND work_date = current_date;", nativeQuery = true)
	public Integer findWorkOn(@Param(value = "empId") String empId);

	@Query(value = "SELECT COUNT(*) FROM employee_time WHERE emp_id= :empId AND out_time IS NOT NULL AND work_date = current_date;", nativeQuery = true)
	public Integer findWorkOff(@Param(value = "empId") String empId);

	@Query(value = "INSERT employee_time( emp_id, work_date, work_time) VALUES ( :empId, current_date, current_timestamp);", nativeQuery = true)
	@Modifying
	@Transactional
	public int startWork(@Param(value = "empId") String empId);

	@Query(value = "UPDATE employee_time SET leave_work=\"퇴근\" ,out_time= CURRENT_TIMESTAMP WHERE emp_id= :empId AND work_date = current_date();", nativeQuery = true)
	@Modifying
	@Transactional
	public int endWork(@Param(value = "empId") String empId);

//   @Query(value = "UPDATE employee_time SET leave_work=\"야근\" WHERE emp_id= :empId AND work_date = current_date();", nativeQuery = true)
//   @Modifying
//   @Transactional
//   public int endlessWork(@Param(value = "empId") String empId);

}