package com.example.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.contract.DepartmentClient;
import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeResonseDTO;
import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.model.Employee;
import com.example.repository.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepo employeeRepo;

	private final EmployeeMapper employeeMapper;

	private final DepartmentClient departmentClient;

	@Override
	public EmployeeResonseDTO getById(Integer empId) {
		Employee employee = employeeRepo.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("There is no employee with given id = " + empId));
		EmployeeResonseDTO empResDTO = employeeMapper.toResDTO(employee);
		if (Objects.nonNull(employee.getDeptId()))
			empResDTO.setDepartment(departmentClient.getById(employee.getDeptId()));
		return empResDTO;
	}

	@Override
	public List<EmployeeResonseDTO> getAll() {
		return employeeRepo.findAll().stream().map(emp -> {
			EmployeeResonseDTO empResDTO = employeeMapper.toResDTO(emp);
			if (Objects.nonNull(emp.getDeptId()))
				empResDTO.setDepartment(departmentClient.getById(emp.getDeptId()));
			return empResDTO;
		}).collect(Collectors.toList());

	}

	@Override
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		Employee employee = employeeMapper.toEntity(employeeDTO);
		return employeeMapper.toDTO(employeeRepo.save(employee));
	}

	@Override
	public void deleteById(Integer empId) {
		Employee employee = employeeRepo.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("There is no employee with given id = " + empId));
		if (Objects.nonNull(employee))
			employeeRepo.deleteById(empId);
	}

	@Override
	public EmployeeDTO updateById(Integer empId, EmployeeDTO employeeDTO) {
		Employee existitngEmployee = employeeRepo.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("There is no employee with given id = " + empId));

		Employee employee = employeeMapper.toEntity(employeeDTO);

		existitngEmployee.setAddress(employee.getAddress());
		existitngEmployee.setDeptId(employee.getDeptId());
		existitngEmployee.setEmpName(employee.getEmpName());
		existitngEmployee.setSalary(employee.getSalary());

		Employee savedEmployee = employeeRepo.save(existitngEmployee);

		return employeeMapper.toDTO(savedEmployee);

	}

}
