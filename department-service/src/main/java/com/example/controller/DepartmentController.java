package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DepartmentDTO;
import com.example.mapper.DepartmentMapper;
import com.example.model.Department;
import com.example.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;

	private final DepartmentMapper departmentMapper;

	@GetMapping("/departments/{id}")
	public ResponseEntity<DepartmentDTO> getById(@PathVariable Integer id) {
		final Department department = departmentService.findById(id);

		final DepartmentDTO existingDepartmentDTO = departmentMapper.toDTO(department);

		return new ResponseEntity<>(existingDepartmentDTO, HttpStatus.OK);
	}
}
