package com.balloon.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balloon.dto.ChangePasswordRequestDTO;
import com.balloon.dto.EmpDTO;
import com.balloon.dto.EmpRequestDTO;
import com.balloon.dto.EmpResByAdminDTO;
import com.balloon.dto.EmpResponseDTO;
import com.balloon.entity.Employee;
import com.balloon.service.EmpServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
//@CrossOrigin(origins = { "http://localhost:3000" })
public class EmpRestController {

	private final EmpServiceImpl empSvc;

//	@GetMapping("/list")
//	public PageResultDTO<EmpDTO, Employee> findEmpList(PageRequestDTO pageRequestDTO) throws Exception {
//		try {
//			if (pageRequestDTO == null) {
//				throw new Exception("입력받은 page, size 값이 없습니다.");
//			} else {
//				PageResultDTO<EmpDTO, Employee> pageResultDTO = empSvc.findEmpList(pageRequestDTO);
//				return pageResultDTO;
//			}
//		} catch (Exception e) {
//			e.getMessage();
//			throw new Exception("출력할 사원 정보가 없습니다.");
//		}
//	}

	@GetMapping("/emps")
	public List<EmpDTO> findEmps() throws Exception {

		List<EmpDTO> empDTOList = empSvc.findEmps();
		if (empDTOList == null) {
			throw new Exception("리스트 값이 없습니다.");
		} else {
			return empDTOList;
		}
	};

	@GetMapping(value = "/{empId}")
	public EmpDTO findEmpByEmpId(@Valid @PathVariable String empId) throws Exception {
		try {
			if (empId == null) {
				throw new Exception("사원번호를 입력받지 못했습니다.");
			} else {
				return empSvc.findEmpByEmpId(empId);
			}
		} catch (Exception e) {
			throw new Exception("입력받은 사원번호가 없습니다.");
		}
	}

	@GetMapping("/management/{empId}")
	public EmpResByAdminDTO findMyEmpInfoByAdmin(@Valid @PathVariable String empId) throws Exception {
		try {
			if (empId == null) {
				throw new Exception("사원번호를 입력받지 못했습니다.");
			} else {
				return empSvc.findEmpInfoByEmpIdUseAdmin(empId);
			}
		} catch (Exception e) {
			throw new Exception("입력받은 사원번호가 없습니다.");
		}
	}

	/**/
	@GetMapping("/me")
	public EmpResponseDTO getMyEmpInfo() throws Exception {
		EmpResponseDTO myInfoBySecurity = empSvc.getMyInfoBySecurity();
		if (myInfoBySecurity == null) {
			throw new Exception("로그인 된 사원의 정보가 없습니다.");
		} else {
			return myInfoBySecurity;
		}
	}

	@PostMapping("/name")
	public ResponseEntity<EmpResponseDTO> updateEmpName(@RequestBody EmpRequestDTO empRequestDTO) {
		return ResponseEntity.ok(empSvc.changeEmpName(empRequestDTO.getEmpId(), empRequestDTO.getEmpName()));
	}

	@PostMapping("/password")
	public ResponseEntity<EmpResponseDTO> updatePassword(@RequestBody ChangePasswordRequestDTO requestDTO) {
		return ResponseEntity.ok(
				empSvc.changePassword(requestDTO.getEmpId(), requestDTO.getExPassword(), requestDTO.getNewPassword()));
	}

	@GetMapping("/approval/line/{unitCode}")
	public List<EmpDTO> findEmpListInUnitCode(@Valid @PathVariable String unitCode) throws Exception {
		try {
			if (unitCode == null) {
				throw new Exception("조직이 존재하지 않습니다.");
			} else {
				List<Employee> empEntityList = empSvc.findEmpListInUnitCode(unitCode);
				if (empEntityList == null) {
					return null;
				} else {
					List<EmpDTO> empDTOList = new ArrayList<EmpDTO>();

					empEntityList.forEach(empEntity -> empDTOList.add(empEntity.toDTO(empEntity)));

					return empDTOList;
				}
			}
		} catch (Exception e) {
			throw new Exception("입력받은 조직번호가 없습니다.");
		}

	}

	@GetMapping("/unit/list/{empId}")
	public List<EmpDTO> findEmpListInSameUnit(@Valid @PathVariable String empId) throws Exception {
		try {
			if (empId == null) {
				throw new Exception("empId 값이 들어오지 않음.");
			} else {
				List<EmpDTO> sameUnitList = empSvc.findEmpListInSameUnit(empId);
				return sameUnitList;
			}

		} catch (Exception e) {
			throw new Exception("터짐.");
		}
	}

	@GetMapping("/apvr/unit/list/{empId}")
	public List<EmpDTO> findApvrListInSameUnit(@Valid @PathVariable String empId) throws Exception {
		try {
			if (empId == null) {
				throw new Exception("empId 값이 들어오지 않음.");
			} else {
				List<EmpDTO> sameUnitList = empSvc.findApvrListInSameUnit(empId);
				return sameUnitList;
			}

		} catch (Exception e) {
			throw new Exception("터짐.");
		}
	}

	// delete
	@CrossOrigin(origins = { "http://localhost:3000" })
	@DeleteMapping("/{empId}")
	public void deleteByEmpId(@Valid @PathVariable String empId) {
		try {
			if (empId == null) {
				throw new Exception("empId 값이 들어오지 않음.");
			} else {
				empSvc.deleteByEmpId(empId);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@CrossOrigin(origins = { "http://localhost:3000" })
	@PutMapping("/update/admin")
	public void updateEmpByAdmin(@Valid @RequestBody EmpResByAdminDTO empDTO) {
		try {
			if (empDTO == null) {
				throw new Exception("사원 정보가 들어오지 않음");
			} else {
				empSvc.updateEmpByAdmin(empDTO);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@CrossOrigin(origins = { "http://localhost:3000" })
	@PutMapping("/update/mypage")
	public void updateEmpByMypage(@Valid @RequestBody EmpResponseDTO empDTO) {
		try {
			if (empDTO == null) {
				throw new Exception("사원 정보가 들어오지 않음");
			} else {
				System.out.println(empDTO);
				empSvc.updateEmpByMypage(empDTO);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
