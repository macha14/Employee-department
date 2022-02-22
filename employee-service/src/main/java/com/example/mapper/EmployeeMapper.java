package com.example.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeResonseDTO;
import com.example.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {

	@Override
	@InheritInverseConfiguration
	EmployeeDTO toDTO(Employee e);

	@Override
	Employee toEntity(EmployeeDTO d);

	EmployeeResonseDTO toResDTO(Employee e);

}
