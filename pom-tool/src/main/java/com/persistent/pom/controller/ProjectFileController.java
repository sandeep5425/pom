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
import com.persistent.pom.entities.ProjectFile;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.ProjectFileService;
import com.persistent.pom.service.TaskService;

@RestController
public class ProjectFileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectFileController.class);

	@Autowired
	ProjectFileService projectFileService;

	@GetMapping(value = "/files")
	public ResponseEntity<ResponseMessage<List<ProjectFile>>> getFiles() throws Exception {
		LOGGER.info("Entered ProjectFile controller to get employees list :: method = getFiles");

		List<ProjectFile> projectFile = projectFileService.getFiles();
		ResponseMessage<List<ProjectFile>> responseMessage = new ResponseMessage<>();
		if (projectFile.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No Files found");
			LOGGER.info("Exit ProjectFile controller with no files found :: method = getFiles ");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(projectFile.size());
		responseMessage.setMessage("List of files that are found");
		responseMessage.setData(projectFile);
		LOGGER.info("Exit ProjectFile controller by fetching list of files :: method = getFiles ");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/file")
	public ResponseEntity<ResponseMessage<ProjectFile>> getParticularFile(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered ProjectFile controller to find task with its id:: method = getFile");
		Optional<ProjectFile> optFile = projectFileService.getFile(id);
		if (!optFile.isPresent()) {
			LOGGER.info("Exit ProjectFile controller with no such id found :: method = getFile");
			throw new NoSuchIDException();
		}
		ProjectFile file = optFile.get();
		ResponseMessage<ProjectFile> response = new ResponseMessage<>();
		response.setData(file);
		response.setLength(1);
		response.setMessage("Details of the file with id : " + id);
		LOGGER.info("Exit ProjectFile controller finding file with given id :: method = getFile");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/file")
	public ResponseEntity<ResponseMessage<ProjectFile>> addFile(@RequestBody ProjectFile projectFile)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered ProjectFile controller to add an task:: method = addFile");
		Optional<ProjectFile> optFile = projectFileService.getFile(projectFile.getId());
		if (optFile.isPresent()) {
			LOGGER.info("Exit ProjectFile controller as same file exists :: method = addFile ");
			throw new EntityAlreadyExistException();
		}
		ProjectFile file = projectFileService.addFile(projectFile);
		ResponseMessage<ProjectFile> response = new ResponseMessage<>();
		response.setData(file);
		response.setMessage("ProjectFile data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit ProjectFile controller after adding file :: method = addFile ");
		return new ResponseEntity<ResponseMessage<ProjectFile>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/file")
	public ResponseEntity<ResponseMessage<ProjectFile>> updateFile(@RequestBody ProjectFile projectFile)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered ProjectFile controller to update an file :: method = updateFile");
		Optional<ProjectFile> optFile = projectFileService.getFile(projectFile.getId());
		if (!optFile.isPresent()) {
			LOGGER.info("Exit ProjectFile controller as no task exists with that id:: method = updateFile ");
			throw new NoSuchIDException();
		}
		ProjectFile file = optFile.get();
		ResponseMessage<ProjectFile> response = new ResponseMessage<>();
		response.setData(file);
		response.setMessage("Data after updating the file");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit ProjectFile controller after updating file :: method = updateFile ");
		return new ResponseEntity<ResponseMessage<ProjectFile>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/file")
	public ResponseEntity<ResponseMessage<ProjectFile>> deleteFile(@RequestParam int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered ProjectFile controller to delete an file :: method = deleteFile");
		Optional<ProjectFile> optFile = projectFileService.getFile(id);
		if (!optFile.isPresent()) {
			LOGGER.info("Exit ProjectFile controller as no file exists with that id:: method = deleteFile ");
			throw new NoSuchIDException();
		}
		projectFileService.deleteFile(id);
		ResponseMessage<ProjectFile> response = new ResponseMessage<>();
		response.setData(optFile.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit ProjectFile controller after deleting file sucessfully:: method = deleteFile ");
		return new ResponseEntity<ResponseMessage<ProjectFile>>(response, HttpStatus.OK);

	}

}
