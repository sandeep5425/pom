package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Employee;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(value = "/employees")
	public ResponseEntity<ResponseMessage<List<Employee>>> getEmployees() {

		List<Employee> employees = employeeService.getEmployees();

		ResponseMessage<List<Employee>> responseMessage = new ResponseMessage<>();
		if(employees.size() == 0) {
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(0);
		responseMessage.setMessage("No employees record found");

		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		}
		
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(employees.size());
		responseMessage.setMessage("List of employees records that are found");
		responseMessage.setData(employees);
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
		
	}

	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<ResponseMessage<Employee>> getParticularEmployee(@PathVariable("id") int id) {
		
		
		//logic cheking the validity of token 
		
		Employee employee = employeeService.getEmployeeById(id);
		ResponseMessage<Employee> response = new ResponseMessage<>();
		if (employee == null) {
			response.setStatusCode(HttpStatus.OK);
			response.setLength(0);
			response.setMessage("No such employee with id : " + id + " present");
			return new ResponseEntity<ResponseMessage<Employee>>(response, HttpStatus.OK);
		}
		response.setData(employee);
		response.setLength(1);
		response.setMessage("Details of the employee with id : " + id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> addEmployee(@RequestBody Employee emp) {
		Employee employee = employeeService.addEmployee(emp);
		ResponseMessage<Employee> response = new ResponseMessage<>();
		response.setData(employee);
		response.setMessage("Employee data");
		response.setStatusCode(HttpStatus.OK);
		return new ResponseEntity<ResponseMessage<Employee>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/employee")
	public ResponseEntity<ResponseMessage<Employee>> updateEmployee(@RequestBody Employee emp) {
		Employee employee = employeeService.updateEmployee(emp);
		return null; // need to return the employee by performing checks in proper format
	}

	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<ResponseMessage<Employee>> deleteEmployee(@PathVariable("id") int id) {
		return null;
	}

}
