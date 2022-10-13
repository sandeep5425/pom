package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Roles;

public interface RolesRepo extends CrudRepository<Roles, Integer> {
	
	
}
