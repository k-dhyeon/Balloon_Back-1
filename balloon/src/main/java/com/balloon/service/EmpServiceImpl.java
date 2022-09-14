package com.balloon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balloon.config.SecurityUtil;
import com.balloon.dto.EmpDTO;
import com.balloon.dto.EmpResByAdminDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.entity.Employee;
import com.balloon.repository.EmpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpRepository empRepo;
	private final PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	@Override
	public EmpDTO findEmpByEmpId(String empId) throws Exception {
		Employee empEntity = empRepo.findEmpByEmpId(empId);
		if (empEntity == null) {
			throw new Exception("사원 정보가 존재하지 않습니다.");
		} else {
			return empEntity.toDTO(empEntity);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public EmpResByAdminDTO findEmpInfoByEmpIdUseAdmin(String empId) throws Exception {
		Employee empEntity = empRepo.findEmpByEmpId(empId);
		if (empEntity == null) {
			throw new Exception("사원 정보가 존재하지 않습니다.");
		} else {
			return empEntity.toDTOByAdmin(empEntity);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<EmpDTO> findEmps() {
		List<Employee> empEntityList = empRepo.findAll();
		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		empEntityList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;
	};

	@Transactional(readOnly = true)
	@Override
	public EmpResponseDTO getMyInfoBySecurity() {
		if (SecurityUtil.getCurrentEmpId() == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}

		return empRepo.getByEmpId(SecurityUtil.getCurrentEmpId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Employee> findEmpListInUnitCode(String unitCode) {
		return empRepo.findEmpListInUnitCode(unitCode);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EmpDTO> findEmpListInSameUnit(String empId) {

		List<Employee> parentCodeList = empRepo.findEmpListOnParentCode(empId);
		List<Employee> sameParentCodeList = empRepo.findEmpListOnSameParentCode(empId);

		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		parentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));
		sameParentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;
	}

	@Transactional
	@Override
	public List<EmpDTO> findApvrListInSameUnit(String empId) {

		List<Employee> parentCodeList = empRepo.findApvrListOnParentCode(empId);
		List<Employee> sameParentCodeList = empRepo.findApvrListOnSameParentCode(empId);

		List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

		parentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));
		sameParentCodeList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

		return empDTOList;

	}

	@Transactional
	@Override
	public void deleteByEmpId(String empId) {
		empRepo.deleteById(empId);
	}

	@Transactional
	@Override
	public void updateEmpByAdmin(EmpResByAdminDTO empDTO) {
		Employee employee = empRepo.findEmpByEmpId(empDTO.getEmpId());
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		employee.updateEmpByAdmin(empDTO, passwordEncoder);
		empRepo.save(employee);
	}

	@Transactional
	@Override
	public void updateEmpByMypage(EmpResponseDTO empDTO) {
		Employee employee = empRepo.findEmpByEmpId(empDTO.getEmpId());
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		employee.updateEmpByUser(empDTO);
		empRepo.save(employee);
	}

	@Transactional
	@Override
	public void updateEmpByProfile(String empId, String photo) {
		Employee employee = empRepo.findEmpByEmpId(empId);
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		employee.updatePhotoByUser(photo);
		empRepo.save(employee);
	}

	@Transactional
	@Override
	public EmpResponseDTO changeEmpName(String empId, String empName) {
		Employee employee = empRepo.findEmpByEmpId(empId);
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		employee.updateEmpName(empName);
		return EmpResponseDTO.of(empRepo.save(employee));
	}

	@Transactional
	@Override
	public EmpResponseDTO changePassword(String empId, String exPasssword, String newPassword) {
		Employee employee = empRepo.findEmpByEmpId(empId);
		if (employee == null) {
			throw new RuntimeException("로그인 유저 정보가 없습니다.");
		}
		if (!passwordEncoder.matches(exPasssword, employee.getPassword())) {
			throw new RuntimeException("비밀번호가 맞지 않습니다.");
		}
		employee.updatePassword(passwordEncoder.encode(newPassword));
		return EmpResponseDTO.of(empRepo.save(employee));
	}

}
