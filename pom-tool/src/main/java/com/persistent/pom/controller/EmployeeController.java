package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Employee;
import com.persistent.pom.response.Response;
import com.persistent.pom.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/employees")
	public ResponseEntity<Object> getEmployees() {
		 List<Employee> employees = employeeService.getEmployees(); 
		 return Response.createResponse("List of all Employees", HttpStatus.OK	, employees);
	}

}
