package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.customexception.EntityAlreadyExistException;
import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.entities.Module;
import com.persistent.pom.response.ErrorMessage;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.ModuleService;

@RestController
@CrossOrigin
public class ModuleController {

	@Autowired
	ModuleService moduleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

	@GetMapping(value = "/modules")
	public ResponseEntity<ResponseMessage<List<Module>>> getModules(HttpServletRequest request)
			throws IllegalArgumentException, Exception {
		LOGGER.info("Entered Module controller to get modules list :: method = getModules");

		// token -> validated (in order to get roles)

		// check token is valid and then also

		// if the user admin we need throw an exception
//		getClaims(Token token)

		List<Module> modules = moduleService.getModules();
		ResponseMessage<List<Module>> responseMessage = new ResponseMessage<>();
		if (modules.size() == 0) {
			responseMessage.setStatusCode(HttpStatus.OK);
			responseMessage.setLength(0);
			responseMessage.setData(new ArrayList<>());
			responseMessage.setMessage("No modules found");
			LOGGER.error("Exit Module controller with no modules found :: method = getModules ");
			return new ResponseEntity<>(responseMessage, HttpStatus.OK);

		}
		responseMessage.setStatusCode(HttpStatus.OK);
		responseMessage.setLength(modules.size());
		responseMessage.setMessage("List of modules records that are found");
		responseMessage.setData(modules);
		LOGGER.info("Exit Module controller by fetching list of modules :: method = getModules ");
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	@GetMapping(value = "/module")
	public ResponseEntity<ResponseMessage<Module>> getParticularEmployee(@RequestParam("id") int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Module controller to find module with their id:: method = getModule");
		Optional<Module> optModule = moduleService.getModule(id);
		if (!optModule.isPresent()) {
			LOGGER.info("Exit Module controller with no such id found :: method = getModule");
			throw new NoSuchIDException();
		}
		Module module = optModule.get();
		ResponseMessage<Module> response = new ResponseMessage<>();
		response.setData(module);
		response.setLength(1);
		response.setMessage("Details of the module with id : " + id);
		LOGGER.info("Exit Module controller finding module with given id :: method = getModule");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/module")
	public ResponseEntity<ResponseMessage<Module>> addModule(@RequestBody Module mod)
			throws EntityAlreadyExistException, Exception {
		LOGGER.info("Entered Employee controller to add an employee:: method = addEmployee");
		Optional<Module> optModule = moduleService.getModule(mod.getId());
		if (optModule.isPresent()) {
			LOGGER.info("Exit Module controller as same module exists :: method = addModule ");
			throw new EntityAlreadyExistException();
		}
		Module module = moduleService.addModule(mod);
		ResponseMessage<Module> response = new ResponseMessage<>();
		response.setData(module);
		response.setMessage("Module data");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Module controller after adding module :: method = addModule ");
		return new ResponseEntity<ResponseMessage<Module>>(response, HttpStatus.OK);
	}

	@PutMapping(value = "/module")
	public ResponseEntity<ResponseMessage<Module>> updateModule(@RequestBody Module mod)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Module controller to update an Module :: method = updateModule ");
		Optional<Module> optModule = moduleService.getModule(mod.getId());
		if (!optModule.isPresent()) {
			LOGGER.error("Exit Module controller as no module exists with that id:: method = updateModule ");
			throw new NoSuchIDException();
		}
		Module module = optModule.get();
		ResponseMessage<Module> response = new ResponseMessage<>();
		response.setData(module);
		response.setMessage("Data after updating the module");
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Module controller after updating module :: method = updateModule ");
		return new ResponseEntity<ResponseMessage<Module>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/module")
	public ResponseEntity<ResponseMessage<Module>> deleteModule(@RequestParam int id)
			throws NoSuchIDException, Exception {
		LOGGER.info("Entered Module controller to delete a module :: method = deleteModule ");
		Optional<Module> optModule = moduleService.getModule(id);
		if (!optModule.isPresent()) {
			LOGGER.info("Exit Module controller as no module exists with that id:: method = deleteModule ");
			throw new NoSuchIDException();
		}
		moduleService.deleteModule(id);
		ResponseMessage<Module> response = new ResponseMessage<>();
		response.setData(optModule.get());
		response.setMessage("Deletion sucessful");
		LOGGER.info("Exit Module controller after deleting module sucessfully:: method = deleteModule ");
		return new ResponseEntity<ResponseMessage<Module>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/moduleByName")
	public List<Module> getModuleByName(@RequestParam("name") String name) {
		return moduleService.getModuleByUsername(name);
	}

}
