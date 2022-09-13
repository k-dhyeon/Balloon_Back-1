package com.balloon.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.balloon.entity.BusinessTripPlan;
import com.balloon.entity.Employee;
import com.balloon.entity.Unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BizTpDTO {
	private String businessTripId;

	private String documentTitle;

	private String documentContent;

	private Byte documentStatus;

	private LocalDate startDate;

	private LocalDate endDate;

	private String destination;

	private String visitingPurpose;

	private String empName;

	private String position;

	private LocalDateTime writeDate;

	private LocalDateTime updateDate;

	private String unitName;

	private Unit unit;

	private Employee emp;

	public BusinessTripPlan toEntity(BizTpDTO bizTpDTO) {
		BusinessTripPlan businessTripPlan = BusinessTripPlan.builder().businessTripId(bizTpDTO.getBusinessTripId())
				.documentTitle(bizTpDTO.getDocumentTitle()).documentContent(bizTpDTO.getDocumentContent())
				.documentStatus(bizTpDTO.getDocumentStatus()).startDate(bizTpDTO.getStartDate())
				.endDate(bizTpDTO.getEndDate()).destination(bizTpDTO.getDestination())
				.visitingPurpose(bizTpDTO.getVisitingPurpose()).empName(bizTpDTO.getEmpName())
				.position(bizTpDTO.getPosition()).writeDate(bizTpDTO.getWriteDate())
				.updateDate(bizTpDTO.getUpdateDate()).unitName(bizTpDTO.getUnitName()).unit(bizTpDTO.getUnit())
				.emp(bizTpDTO.getEmp()).build();

		return businessTripPlan;
	}
}
