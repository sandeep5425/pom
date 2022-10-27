package com.persistent.pom.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Module;


public interface ModuleRepo extends CrudRepository<Module, Integer> {

	List<Module> findByName(String name);
}
