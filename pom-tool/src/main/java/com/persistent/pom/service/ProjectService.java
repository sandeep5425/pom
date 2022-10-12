package com.persistent.pom.service;

import java.util.List;

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

}
