package com.persistent.pom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Employee;
import com.persistent.pom.repositories.EmployeeRepo;

@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepo.findAll();
	}

}
