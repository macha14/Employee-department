package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {

	private Integer empId;
	private String empName;
	private Integer salary;
	private String address;
	private Integer deptId;

}
