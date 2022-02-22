package com.example.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Employee;
import com.example.repository.EmployeeRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepo employeeRepository;

	@Test
	void deleteEmployeeById() throws Exception {
		Employee employee = new Employee();
		employee.setEmpId(66);
		employee.setEmpName("Ronit");
		employee.setAddress("abd");
		employee.setSalary(5000);
		employee.setDeptId(1);

		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

		mockMvc.perform(delete("/api/v1/employees/66")).andExpect(status().isNoContent());
	}

	@Test
	void addEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setEmpId(66);
		employee.setEmpName("Ronit");
		employee.setAddress("abd");
		employee.setSalary(5000);
		employee.setDeptId(1);

		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		mockMvc.perform(
				post("/api/v1/employees")
						.content("{\n" + "    \"salary\": 10000,\n" + "    \"empName\": \"john\",\n"
								+ "    \"deptId\": 2,\n" + "    \"address\": \" nbht\"\n" + "}")
						.contentType("application/json"))
				.andExpect(status().isCreated());
	}

	@Test
	void updateEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setEmpId(66);
		employee.setEmpName("Ronit");
		employee.setAddress("abd");
		employee.setSalary(5000);
		employee.setDeptId(1);

		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(employee));

		Integer emp_id = 66;

		Employee newEmployee = new Employee();
		newEmployee.setEmpId(emp_id);
		newEmployee.setEmpName("RonitABC");
		newEmployee.setAddress("abd");
		newEmployee.setSalary(15000);
		newEmployee.setDeptId(1);

		when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

		mockMvc.perform(put("/api/v1/employees/" + emp_id)
				.content("{\n" + "    \"salary\": 15000,\n" + "    \"empName\": \"RonitABC\",\n"
						+ "    \"deptId\": 1,\n" + "    \"address\": \" abd\"\n" + "}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("{\n" + "        \"empId\": 66,\n" + "  \"empName\" : \"RonitABC\",\n"
						+ "    \"salary\": 15000,\n" + "    \"deptId\": 1,\n" + "    \"address\": \"abd\"\n" + "}"));
	}

}
