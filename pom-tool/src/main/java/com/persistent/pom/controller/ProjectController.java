package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.customexception.EntityAlreadyExistException;
import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.entities.Project;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService projectService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@GetMapping(value = "/projects")
	public ResponseEntity<ResponseMessage<List<Project>>> getProjects() throws Exception {
		LOGGER.info("Entered Project controller to get projects list :: method = getProjects");

		List<Project> projects = projectService.getProjects();
		ResponseMessage<List<Project>> responseMessage = new ResponseMessage<>();
		if (projects.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No projects found");
			LOGGER.info("Exit Project controller with no projects found :: method = getProjects ");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(projects.size());
		responseMessage.setMessage("List of projects records that are found");
		responseMessage.setData(projects);
		LOGGER.info("Exit Project controller by fetching list of projects :: method = getProjects ");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> getParticularProject(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Project controller to find Project with their id:: method = getProject");
		Optional<Project> optProject = projectService.getProject(id);
		if (!optProject.isPresent()) {
			LOGGER.info("Exit Project controller with no such id found :: method = getProject");
			throw new NoSuchIDException();
		}
		Project project = optProject.get();
		ResponseMessage<Project> response = new ResponseMessage<>();
		response.setData(project);
		response.setLength(1);
		response.setMessage("Details of the Project with id : " + id);
		LOGGER.info("Exit Project controller finding Project with given id :: method = getProject");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> addProject(@RequestBody Project proj)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered Project controller to add an Project:: method = addEmployee");
		Optional<Project> optEmployee = projectService.getProject(proj.getId());
		if (optEmployee.isPresent()) {
			LOGGER.info("Exit Project controller as same Project exists :: method = addEmployee ");
			throw new EntityAlreadyExistException();
		}
		Project project = projectService.addProject(proj);
		ResponseMessage<Project> response = new ResponseMessage<>();
		response.setData(project);
		response.setMessage("Project data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Project controller after adding Project :: method = addEmployee ");
		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> updateProject(@RequestBody Project proj)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Project controller to update an Project :: method = updateProject ");
		Optional<Project> optProject = projectService.getProject(proj.getId());
		if (!optProject.isPresent()) {
			LOGGER.info("Exit Project controller as no Project exists with that id:: method = updateProject ");
			throw new NoSuchIDException();
		}
		Project project = optProject.get();
		ResponseMessage<Project> response = new ResponseMessage<>();
		response.setData(project);
		response.setMessage("Data after updating the Project");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Project controller after updating Project :: method = updateProject ");
		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/project")
	public ResponseEntity<ResponseMessage<Project>> deleteProject(@RequestParam int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Project controller to delete an Project :: method = deleteProject");
		Optional<Project> optProject = projectService.getProject(id);
		if (!optProject.isPresent()) {
			LOGGER.info("Exit Project controller as no Project exists with that id:: method = deleteProject ");
			throw new NoSuchIDException();
		}
		projectService.deleteProject(id);
		ResponseMessage<Project> response = new ResponseMessage<>();
		response.setData(optProject.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit Project controller after deleting Project sucessfully:: method = deleteProject ");
		return new ResponseEntity<ResponseMessage<Project>>(response, HttpStatus.OK);

	}

}
