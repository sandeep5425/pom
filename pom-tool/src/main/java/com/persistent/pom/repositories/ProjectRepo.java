package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Project;

public interface ProjectRepo extends CrudRepository<Project	, Integer> {

}
