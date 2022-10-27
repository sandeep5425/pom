package com.persistent.pom.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.customexception.NullValueInsertion;
import com.persistent.pom.entities.Employee;
import com.persistent.pom.entities.Project;
import com.persistent.pom.repositories.EmployeeRepo;
import com.persistent.pom.response.ResponseMessage;

import io.jsonwebtoken.io.IOException;

@Component
public class EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getEmployees() {
		return (List<Employee>) employeeRepo.findAll();
	}

	public Optional<Employee> getEmployee(int id) throws IllegalArgumentException {
		return employeeRepo.findById(id);
	}

	public Employee updateEmployee(Employee emp) throws IllegalArgumentException {
		return employeeRepo.save(emp);
	}

	public Employee addEmployee(Employee emp) throws IllegalArgumentException {
		return employeeRepo.save(emp);
	}

	public void deleteEmployee(int id) throws IllegalArgumentException {
		employeeRepo.deleteById(id);
	}

	public Employee getEmployeeByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}

	public List<Employee> getEmployeeByProject(Project projectName) {
		
		return employeeRepo.findByProject(projectName);
	}

}
