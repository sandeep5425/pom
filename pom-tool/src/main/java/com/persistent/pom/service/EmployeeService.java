package com.persistent.pom.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.persistent.pom.CustomException.NoSuchIDException;
import com.persistent.pom.CustomException.NullValueInsertion;
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
		try{
			return employeeRepo.findById(id).get();
		}catch(Exception e) {
		   throw new NoSuchIDException();
		}
	}

	public Employee updateEmployee(Employee emp) {
		try {
			return employeeRepo.save(emp);
		}
		catch(Exception e) {
			throw new NullValueInsertion();
		}
	}

	public Employee addEmployee(Employee emp) {
		try {
			return employeeRepo.save(emp);
		}
		catch(Exception e) {
			throw new NullValueInsertion();
		}
	}

}
