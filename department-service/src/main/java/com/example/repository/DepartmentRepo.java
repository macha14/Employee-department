package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
