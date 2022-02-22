package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class DepartmentDTO {
	@NonNull
	private Integer deptId;
	private String deptName;
	private String deptDesc;
}
