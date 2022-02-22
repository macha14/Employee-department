package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.example.contract.DepartmentClient;
import com.example.dto.EmployeeDTO;
import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.EmployeeMapperImpl;
import com.example.model.Employee;
import com.example.repository.EmployeeRepo;

public class EmployeeServiceTest {

	private EmployeeRepo employeeRepo;
	private EmployeeService employeeService;
	private DepartmentClient departmentClient;
	private EmployeeMapper employeeMapper;

	@BeforeEach
	void setUp() {
		employeeRepo = mock(EmployeeRepo.class);
		employeeMapper = new EmployeeMapperImpl();
		employeeService = new EmployeeServiceImpl(employeeRepo, employeeMapper, departmentClient);
	}

	@Nested
	@DisplayName("All the test cases for adding employee into the system")
	class AddEmployeeTestScenarios {

		@Test
		@DisplayName("Happy Path - employee added into the system")
		void addEmployeeIntoTheSystem() {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmpName("Ronit");
			employeeDTO.setAddress("abd");
			employeeDTO.setSalary(5000);
			employeeDTO.setDeptId(1);

			Employee employee = employeeMapper.toEntity(employeeDTO);

			when(employeeRepo.save(any(Employee.class))).thenReturn(employee);

			EmployeeDTO actualResponse = employeeService.save(employeeDTO);

			EmployeeDTO expectedResponse = employeeDTO;

			assertThat(expectedResponse.getEmpName()).isEqualTo(actualResponse.getEmpName());
			assertThat(expectedResponse.getAddress()).isEqualTo(actualResponse.getAddress());
			assertThat(expectedResponse.getSalary()).isEqualTo(actualResponse.getSalary());
			assertThat(expectedResponse.getDeptId()).isEqualTo(actualResponse.getDeptId());

		}

	}

	@Nested
	@DisplayName("All the cases to fetch employees list from the system")
	class ListAllEmployeesFromTheSystem {
	}

	@Nested()
	@DisplayName("All test cases for employee to be searched by given Id")
	class EmployeeByIdTestScenarios {
	}

	@Nested
	@DisplayName("All the test cases for update employee")
	class UpdateEmployeeTestScenarios {

		@Test
		@DisplayName("Employee to be updated not found")
		void updateEmployee_NotFound() {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmpName("Ronit");
			employeeDTO.setAddress("abd");
			employeeDTO.setSalary(5000);
			employeeDTO.setDeptId(1);

			assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateById(66, employeeDTO));
		}

		@Test
		@DisplayName("Employee updated - happy path")
		void updateEmployee() {
			Employee employee = new Employee();
			employee.setEmpId(66);
			employee.setEmpName("Ronit");
			employee.setAddress("abd");
			employee.setSalary(5000);
			employee.setDeptId(1);

			when(employeeRepo.findById(anyInt())).thenReturn(Optional.of(employee));

			Integer emp_id = 66;

			Employee newEmployee = new Employee();
			newEmployee.setEmpId(emp_id);
			newEmployee.setEmpName("RonitABC");
			newEmployee.setAddress("abd");
			newEmployee.setSalary(15000);
			newEmployee.setDeptId(1);

			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmpName("RonitABC");
			employeeDTO.setAddress("abd");
			employeeDTO.setSalary(15000);
			employeeDTO.setDeptId(1);

			when(employeeRepo.save(any(Employee.class))).thenReturn(newEmployee);

			EmployeeDTO actualResponse = employeeService.updateById(emp_id, employeeDTO);

			EmployeeDTO expectedResponse = employeeMapper.toDTO(newEmployee);

			assertThat(expectedResponse.getEmpId()).isEqualTo(actualResponse.getEmpId());
			assertThat(expectedResponse.getAddress()).isEqualTo(actualResponse.getAddress());
			assertThat(expectedResponse.getDeptId()).isEqualTo(actualResponse.getDeptId());
			assertThat(expectedResponse.getSalary()).isEqualTo(actualResponse.getSalary());
			assertThat(expectedResponse.getEmpName()).isEqualTo(actualResponse.getEmpName());
		}

	}

	@Nested
	@DisplayName("All the test cases for delete employee")
	class DeletedEmployeeTestScenarios {

		@Test
		@DisplayName("Employee to be deleted not found")
		void deleteEmployee_NotFound() {
			assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteById(66));
		}

		@Test
		@DisplayName("Employee deleted - happy path")
		void deleteEmployee() {
			when(employeeRepo.findById(anyInt())).thenReturn(Optional.of(new Employee()));
			employeeService.deleteById(anyInt());
			verify(employeeRepo, times(1)).deleteById(anyInt());
		}

	}

}
