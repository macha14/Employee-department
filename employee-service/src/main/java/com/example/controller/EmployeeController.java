package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeResonseDTO;
import com.example.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = { "/api/v1" }, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@ApiResponse(responseCode = "201", description = "Employee is created", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) })
	@Operation(summary = "Crate a new employee")
	@PostMapping(path = "/employees", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO savedEmployeeDTO = employeeService.save(employeeDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployeeDTO.getEmpId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Get all employees")
	@ApiResponse(responseCode = "200", description = "Found all employees", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResonseDTO.class)) })
	@GetMapping(path = "/employees")
	public ResponseEntity<List<EmployeeResonseDTO>> getAllEmployees() {
		List<EmployeeResonseDTO> empResDTO = employeeService.getAll();
		return new ResponseEntity<>(empResDTO, HttpStatus.OK);
	}

	@Operation(summary = "Get an employee by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the employee", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResonseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping(path = "/employees/{empId}")
	public ResponseEntity<EmployeeResonseDTO> getById(@PathVariable Integer empId) {
		EmployeeResonseDTO employeeResonseDTO = employeeService.getById(empId);
		return new ResponseEntity<>(employeeResonseDTO, HttpStatus.OK);
	}

	@Operation(summary = "Delete an employee by its id")
	@ApiResponse(responseCode = "204", description = "Delete the employee")
	@DeleteMapping("/employees/{empId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer empId) {
		employeeService.deleteById(empId);
	}

	@Operation(summary = "Update an employee by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the employee", content = {
			@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@PutMapping("/employees/{empId}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Integer empId,
			@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO savedEmployeeDTO = employeeService.updateById(empId, employeeDTO);
		return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.OK);
	}

}
