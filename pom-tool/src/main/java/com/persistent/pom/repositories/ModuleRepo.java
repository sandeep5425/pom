package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Module;


public interface ModuleRepo extends CrudRepository<Module, Integer> {

}
