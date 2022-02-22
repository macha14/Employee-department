package com.example.service;

import org.springframework.stereotype.Service;

import com.example.exception.DepartmentNotFoundException;
import com.example.model.Department;
import com.example.repository.DepartmentRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

	private final DepartmentRepo deptRepo;

	@Override
	public Department findById(Integer deptId) throws DepartmentNotFoundException {
		return deptRepo.findById(deptId).orElse(null);
	}

}
