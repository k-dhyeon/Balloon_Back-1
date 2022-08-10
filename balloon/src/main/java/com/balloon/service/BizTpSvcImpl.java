package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.balloon.dto.BizTpDTO;
import com.balloon.entity.BusinessTripPlan;
import com.balloon.repository.BizTpRepository;
import com.balloon.vo.DocVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BizTpSvcImpl implements BizTpSvc {
	private final BizTpRepository bizTpRepo;

	@Override
	public void insertBizTp(BizTpDTO bizTpDTO) {
		BusinessTripPlan bizTpEntity = bizTpDTO.toEntity(bizTpDTO);
		bizTpRepo.save(bizTpEntity);
	}

	@Override
	public List<DocVO> getDoc(String empId) {
		String id = empId;
		List<DocVO> voList = new ArrayList<DocVO>();
		List<BusinessTripPlan> businessTripPlanList = bizTpRepo
				.findBusinessTripIdAndDocumentTitleAndUpdateDateByEmpEmpId(id);
		for (BusinessTripPlan businessTripPlan : businessTripPlanList) {
			voList.add(businessTripPlan.toVO(businessTripPlan));
		}
		return voList;
	}

	@Override
	public BizTpDTO getBizTpByBizTpId(String bizTpId) {
		BusinessTripPlan businessTripPlan = bizTpRepo.findBusinessTripPlanByBusinessTripId(bizTpId);
		BizTpDTO bizTpDTO = businessTripPlan.toDTO(businessTripPlan);
		return bizTpDTO;
	}

	@Override
	public void deleteBizTpByBizTpId(String bizTpId) {
		bizTpRepo.deleteById(bizTpId);
	}

}