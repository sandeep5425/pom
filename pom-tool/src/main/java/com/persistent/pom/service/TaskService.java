package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Task;
import com.persistent.pom.repositories.TaskRepo;

@Component
public class TaskService {

	@Autowired
	TaskRepo taskRepo;

	public List<Task> getTasks() {
		return (List<Task>) taskRepo.findAll();
	}

	public Optional<Task> getTask(int id) throws IllegalArgumentException {
		return taskRepo.findById(id);
	}

	public Task addTask(Task task) throws IllegalArgumentException {
		return taskRepo.save(task);
	}

	public Task updateTask(Task task) throws IllegalArgumentException {
		return taskRepo.save(task);
	}

	public void deleteTask(int id) throws IllegalArgumentException {
		taskRepo.deleteById(id);
	}
}
