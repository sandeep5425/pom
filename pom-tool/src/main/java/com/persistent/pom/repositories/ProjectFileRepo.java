package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.ProjectFile;

public interface ProjectFileRepo extends CrudRepository<ProjectFile, Integer>{

}
