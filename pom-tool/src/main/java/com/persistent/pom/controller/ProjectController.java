package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Project;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(value = "/projects")
	public ResponseEntity<ResponseMessage<List<Project>>> getProjects() {

		List<Project> projects = projectService.getProjects();
		ResponseMessage<List<Project>> response = new ResponseMessage<>();

		if (projects.size() == 0) {
			response.setLength(0);
			response.setStatusCode(HttpStatus.OK);
			response.setMessage("No projects found");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/project")
	public ResponseEntity<ResponseMessage<List<Project>>> getParticularProject(@RequestParam("id") int id) {

		return null;
	}

	@PostMapping(value = "/project")
	public ResponseEntity<ResponseMessage<List<Project>>> addProject(@RequestBody Project project) {
		return null;
	}

	@PutMapping(value = "/project")
	public ResponseEntity<ResponseMessage<List<Project>>> updateProject(@RequestBody Project project) {
		return null;
	}

	@DeleteMapping(value = "/project")
	public ResponseEntity<ResponseMessage<List<Project>>> deleteProject(@RequestParam("id") int id) {
		return null;
	}

}
