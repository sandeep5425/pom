package com.persistent.pom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Employee;
import com.persistent.pom.repositories.EmployeeRepo;
import com.persistent.pom.response.ResponseMessage;

@Component
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getEmployees() {

		return (List<Employee>) employeeRepo.findAll();
	}

	public Employee getEmployeeById(int id) {
		try {			
			return employeeRepo.findById(id).get();
		}
		catch (Exception e) {
			return null;
		}
	}

	public Employee updateEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}

	public Employee addEmployee(Employee emp) {
		return employeeRepo.save(emp);
	}

}
