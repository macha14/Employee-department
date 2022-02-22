package com.example.contract;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("department-service")
public interface DepartmentClient {

	@GetMapping("/api/v1/departments/{deptId}")
	public Department getById(@PathVariable Integer deptId);
}
