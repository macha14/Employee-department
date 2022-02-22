package com.example.service;

import com.example.exception.DepartmentNotFoundException;
import com.example.model.Department;

public interface DepartmentService {
	Department findById(Integer deptId) throws DepartmentNotFoundException;
}
