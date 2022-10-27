package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.customexception.NoSuchIDException;
import com.persistent.pom.customexception.NullValueInsertion;
import com.persistent.pom.entities.Employee;
import com.persistent.pom.entities.Module;
import com.persistent.pom.repositories.ModuleRepo;

@Component
public class ModuleService {
	
	@Autowired
	ModuleRepo moduleRepo;
	
	
	public List<Module> getModules() {
		return (List<Module>) moduleRepo.findAll();
	}
	
	public Module addModule(Module module) throws IllegalArgumentException {
		return moduleRepo.save(module);
		
	}
	
	public Optional<Module> getModule(int id) throws IllegalArgumentException {
			return moduleRepo.findById(id);
		}
	
	public Module updateModule(Module module) throws IllegalArgumentException {
		return moduleRepo.save(module);
	}
	
	public void deleteModule(int id) throws IllegalArgumentException {
		moduleRepo.deleteById(id);
	}
	
	public List<Module> getModuleByUsername(String username) {
		return moduleRepo.findByName(username);
	}

}
