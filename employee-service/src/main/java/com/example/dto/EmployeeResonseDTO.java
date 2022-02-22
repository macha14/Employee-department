package com.example.dto;

import com.example.contract.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeResonseDTO {
	private Integer empId;
	private String empName;
	private Integer salary;
	private String address;

	@JsonInclude(Include.NON_NULL)
	private Department department;
}
