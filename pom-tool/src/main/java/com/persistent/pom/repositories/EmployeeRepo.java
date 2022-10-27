package com.persistent.pom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Employee;
import com.persistent.pom.entities.Project;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	public Employee findByEmail(String email); //only one employee

	public List<Employee> findByProject(Project projectName); // list of employees
	
}
