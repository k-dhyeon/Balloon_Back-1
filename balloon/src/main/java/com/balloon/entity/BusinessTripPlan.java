package com.balloon.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.balloon.dto.BizTpDTO;
import com.balloon.vo.DocVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "business_trip_plan")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BusinessTripPlan {

	@Id
	@Column(name = "business_trip_id", length = 20)
	private String businessTripId;

	@Column(name = "document_title", length = 20)
	private String documentTitle;

	@Column(name = "document_content", length = 2000)
	private String documentContent;

	@Column(name = "document_status", length = 1)
	private Byte documentStatus;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "destination", length = 40)
	private String destination;

	@Column(name = "visiting_purpose", length = 200)
	private String visitingPurpose;

	@Column(name = "emp_name", length = 30)
	private String empName;

	@Column(name = "position", length = 20)
	private String position;

	@CreatedDate
	@Column(name = "write_date", updatable = false)
	private LocalDateTime writeDate;

	@LastModifiedDate
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "unit_name", length = 20)
	private String unitName;

	@JsonIgnore
	@ManyToOne(targetEntity = Unit.class)
	@JoinColumn(name = "unit_code")
	private Unit unit;

	@JsonIgnore
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "emp_id")
	private Employee emp;

	public BizTpDTO toDTO(BusinessTripPlan businessTripPlan) {
		BizTpDTO bizTpDTO = BizTpDTO.builder().businessTripId(businessTripPlan.getBusinessTripId())
				.documentTitle(businessTripPlan.getDocumentTitle())
				.documentContent(businessTripPlan.getDocumentContent())
				.documentStatus(businessTripPlan.getDocumentStatus()).startDate(businessTripPlan.getStartDate())
				.endDate(businessTripPlan.getEndDate()).destination(businessTripPlan.getDestination())
				.visitingPurpose(businessTripPlan.getVisitingPurpose()).empName(businessTripPlan.getEmpName())
				.position(businessTripPlan.getPosition()).writeDate(businessTripPlan.getWriteDate())
				.updateDate(businessTripPlan.getUpdateDate()).unitName(businessTripPlan.getUnitName())
				.unit(businessTripPlan.getUnit()).emp(businessTripPlan.getEmp()).build();

		return bizTpDTO;
	}

	public DocVO toVO(BusinessTripPlan businessTripPlan) {
//		DocVO docVO = DocVO.builder().docId(businessTripPlan.getBusinessTripId())
//				.documentTitle(businessTripPlan.getDocumentTitle()).updateTime(businessTripPlan.getUpdateDate())
//				.documentStatus(businessTripPlan.getDocumentStatus()).unit(businessTripPlan.getUnit()).build();
		DocVO docVO = DocVO.builder().docId(businessTripPlan.getBusinessTripId())
				.documentTitle(businessTripPlan.getDocumentTitle()).updateTime(businessTripPlan.getUpdateDate())
				.build();
		return docVO;
	}
}
