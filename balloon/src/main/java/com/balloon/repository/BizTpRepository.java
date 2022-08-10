package com.balloon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balloon.entity.BusinessTripPlan;

@Repository
public interface BizTpRepository extends JpaRepository<BusinessTripPlan, String> {

	public BusinessTripPlan findBusinessTripPlanByBusinessTripId(String businessTripId);

	public List<BusinessTripPlan> findBusinessTripIdAndDocumentTitleAndUpdateDateByEmpEmpId(String empId);

//	public BusinessTripPlan findBusinessTripPlanByEmpId(String empId);
}
