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
		System.out.println(projects.size());
		response.setLength(projects.size());
		response.setStatusCode(HttpStatus.OK);
		response.setMessage("No projects found");
		response.setData(projects);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> getParticularProject(@RequestParam("id") int id) {
		Project project = projectService.getProejct(id);

		ResponseMessage<Project> response = new ResponseMessage<>();
		response.setData(project);
		response.setLength(1);
		response.setMessage("Project with the id: " + id);
		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);

	}

	@PostMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> addProject(@RequestBody Project project) {
		ResponseMessage<Project> response = new ResponseMessage<>();
		Project addedProject = projectService.addProject(project);
		response.setData(addedProject);
		response.setLength(1);

		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);

	}

	@PutMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> updateProject(@RequestBody Project project) {
		ResponseMessage<Project> response = new ResponseMessage<>();
		Project updatedProject = projectService.updateProject(project);
		response.setData(updatedProject);
		response.setLength(1);
		response.setMessage("Project details update sucessfully");
		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);

	}

	@DeleteMapping(value = "/project")
	public ResponseEntity<ResponseMessage<List<Project>>> deleteProject(@RequestParam("id") int id) {
		return null;
	}

}
