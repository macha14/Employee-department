package com.example.exception;

public class DepartmentNotFoundException extends RuntimeException {

	public DepartmentNotFoundException(String msg) {
		super(msg);
	}

}
