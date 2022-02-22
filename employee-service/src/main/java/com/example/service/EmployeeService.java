package com.example.service;

import java.util.List;

import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeResonseDTO;

public interface EmployeeService {

	EmployeeResonseDTO getById(Integer empId);

	List<EmployeeResonseDTO> getAll();

	void deleteById(Integer empId);

	EmployeeDTO updateById(Integer empId, EmployeeDTO employeeDTO);

	EmployeeDTO save(EmployeeDTO employeeDTO);

}
