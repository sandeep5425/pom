package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
