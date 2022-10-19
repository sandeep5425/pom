package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Project;
import com.persistent.pom.repositories.ProjectRepo;

@Component
public class ProjectService {
	
	@Autowired
	ProjectRepo projectRepo;
	
	
	public List<Project> getProjects() {
		return (List<Project>) projectRepo.findAll();
	}
	
	public Optional<Project> getProject(int id) throws IllegalArgumentException {
		return projectRepo.findById(id);
	}
	
	public Project addProject(Project project) throws IllegalArgumentException {
		return projectRepo.save(project);
	}
	
	public Project updateProject(Project project) throws IllegalArgumentException {
		return projectRepo.save(project);
	}
	public void deleteProject(int id) throws IllegalArgumentException {
		projectRepo.deleteById(id);
	}
	
}
