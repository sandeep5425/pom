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
import com.persistent.pom.entities.Task;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.TaskService;

@RestController
public class TaskController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskService taskService;

	@GetMapping(value = "/tasks")
	public ResponseEntity<ResponseMessage<List<Task>>> getTasks() throws Exception {
		LOGGER.info("Entered Task controller to get employees list :: method = getTasks");

		List<Task> tasks = taskService.getTasks();
		ResponseMessage<List<Task>> responseMessage = new ResponseMessage<>();
		if (tasks.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No tasks found");
			LOGGER.info("Exit Task controller with no tasks found :: method = getTasks ");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(tasks.size());
		responseMessage.setMessage("List of tasks that are found");
		responseMessage.setData(tasks);
		LOGGER.info("Exit Task controller by fetching list of tasks :: method = getTasks ");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/task")
	public ResponseEntity<ResponseMessage<Task>> getParticularTask(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Task controller to find task with its id:: method = getTask");
		Optional<Task> optTask = taskService.getTask(id);
		if (!optTask.isPresent()) {
			LOGGER.info("Exit Task controller with no such id found :: method = getTask");
			throw new NoSuchIDException();
		}
		Task task = optTask.get();
		ResponseMessage<Task> response = new ResponseMessage<>();
		response.setData(task);
		response.setLength(1);
		response.setMessage("Details of the task with id : " + id);
		LOGGER.info("Exit Task controller finding task with given id :: method = getTask");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/task")
	public ResponseEntity<ResponseMessage<Task>> addTask(@RequestBody Task tsk)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered Task controller to add an task:: method = addTask");
		Optional<Task> optTask = taskService.getTask(tsk.getId());
		if (optTask.isPresent()) {
			LOGGER.info("Exit Task controller as same task exists :: method = addTask ");
			throw new EntityAlreadyExistException();
		}
		Task task = taskService.addTask(tsk);
		ResponseMessage<Task> response = new ResponseMessage<>();
		response.setData(task);
		response.setMessage("Task data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Task controller after adding task :: method = addTask ");
		return new ResponseEntity<ResponseMessage<Task>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/task")
	public ResponseEntity<ResponseMessage<Task>> updateTask(@RequestBody Task tsk) throws NoSuchIDException, Exception {
		LOGGER.info("Entered Task controller to update an task :: method = updateTask ");
		Optional<Task> optTask = taskService.getTask(tsk.getId());
		if (!optTask.isPresent()) {
			LOGGER.info("Exit Task controller as no task exists with that id:: method = updateTask ");
			throw new NoSuchIDException();
		}
		Task task = optTask.get();
		ResponseMessage<Task> response = new ResponseMessage<>();
		response.setData(task);
		response.setMessage("Data after updating the task");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Task controller after updating task :: method = updateTask ");
		return new ResponseEntity<ResponseMessage<Task>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/task")
	public ResponseEntity<ResponseMessage<Task>> deleteTask(@RequestParam int id) throws NoSuchIDException, Exception {
		LOGGER.info("Entered Task controller to delete an task :: method = deleteTask");
		Optional<Task> optTask = taskService.getTask(id);
		if (!optTask.isPresent()) {
			LOGGER.info("Exit Task controller as no task exists with that id:: method = deleteTask ");
			throw new NoSuchIDException();
		}
		taskService.deleteTask(id);
		ResponseMessage<Task> response = new ResponseMessage<>();
		response.setData(optTask.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit Task controller after deleting task sucessfully:: method = deleteTask ");
		return new ResponseEntity<ResponseMessage<Task>>(response, HttpStatus.OK);

	}

}
