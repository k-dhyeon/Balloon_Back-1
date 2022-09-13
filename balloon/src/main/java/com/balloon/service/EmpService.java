package com.balloon.service;

import java.util.List;

import com.balloon.dto.EmpDTO;
import com.balloon.dto.EmpResByAdminDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.entity.Employee;

public interface EmpService {

	// 사원 정보 출력 - 페이징 처리
//	public PageResultDTO<EmpDTO, Employee> findEmpList(PageRequestDTO pageRequestDTO);

	// 사번으로 회원찾기
	public EmpDTO findEmpByEmpId(String empId) throws Exception;

	// 시큐리티로 로그인한 회원 정보 찾기
	public EmpResByAdminDTO findEmpInfoByEmpIdUseAdmin(String empId) throws Exception;

	// 사원 정보 모두 출력
	public List<EmpDTO> findEmps();

	// 시큐리티로 로그인한 내 정보 찾기
	public EmpResponseDTO getMyInfoBySecurity();

	// 같은 조직내 사원 출력
	public List<Employee> findEmpListInUnitCode(String unitCode);

	public List<EmpDTO> findApvrListInSameUnit(String empId);

	// 상위 조직이 있으면 상위조직의 사원까지 출력 (팀이면 조직까지)
	public List<EmpDTO> findEmpListInSameUnit(String empId);

	// 사번으로 사원 삭제
	public void deleteByEmpId(String empId);

	// 관리자가 사원 수정
	public void updateEmpByAdmin(EmpResByAdminDTO empDTO);

	// 사원이 사원 수정
	public void updateEmpByMypage(EmpResponseDTO empDTO);

	// 사번과 이름 입력받아 사원 이름 수정
	public EmpResponseDTO changeEmpName(String empId, String empName);

	// 사번과 비밀번호들 입력받아 사원 비밀번호 수정
	public EmpResponseDTO changePassword(String empId, String exPasssword, String newPassword);

}
