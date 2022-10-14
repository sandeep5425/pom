package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Module;
import com.persistent.pom.service.ModuleService;

@RestController
public class ModuleController {
	
	@Autowired
	ModuleService moduleService;
	
	@GetMapping(value = "/modules")
	public List<Module> getModules() {
		return moduleService.getModules();
	}
	
	@GetMapping(value = "/module/{id}")
	public Module getParticularModule(@PathVariable("id")int id) {
		//check if a module with id exists 
		 return moduleService.getModule(id);
	}

	@PostMapping(value = "/module")
	public Module addModule(@RequestBody Module module) {
		//add only if doesn't exist already
		return moduleService.addModule(module);
	}
	
	@PutMapping(value = "/module")
	public Module updateModule(@RequestBody Module module) {
		//check module exists or not and 
		return moduleService.updateModule(module);
	}

	@DeleteMapping(value = "/module/{id}")
	public void delteModule(@PathVariable("id")int id) {
		//check if it is already present and send resoponse
		 moduleService.deleteModule(id);
	}
}
