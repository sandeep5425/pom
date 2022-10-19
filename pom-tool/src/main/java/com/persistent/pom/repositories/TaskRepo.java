package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}
