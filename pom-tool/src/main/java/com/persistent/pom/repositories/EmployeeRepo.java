package com.persistent.pom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	List<Employee> findByName(String name);
	
	
}
