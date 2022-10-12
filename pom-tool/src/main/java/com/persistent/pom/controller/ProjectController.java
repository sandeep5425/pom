package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Project;
import com.persistent.pom.service.ProjectService;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@GetMapping(value = "/projects")
	public List<Project> getProjects() {
		return projectService.getProjects();
	}
	
	
	
	
}
