package com.example.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.example.dto.DepartmentDTO;
import com.example.model.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends AbstractMapper<Department, DepartmentDTO> {

	@Override
	@InheritInverseConfiguration
	Department toEntity(DepartmentDTO d);

	@Override
	DepartmentDTO toDTO(Department e);

}
