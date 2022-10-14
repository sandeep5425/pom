package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Module;
import com.persistent.pom.repositories.ModuleRepo;

@Component
public class ModuleService {
	
	@Autowired
	ModuleRepo moduleRepo;
	
	public List<Module> getModules() {
		return (List<Module>) moduleRepo.findAll();
	}
	
	public Module addModule(Module module) {
		return moduleRepo.save(module);
	}
	
	public Module getModule(int id) {
		return moduleRepo.findById(id).get();
	}
	
	public Module updateModule(Module module) {
		return moduleRepo.save(module);
	}
	
	public void deleteModule(int id) {
		moduleRepo.deleteById(id);
	}
	
	
	
	

}
