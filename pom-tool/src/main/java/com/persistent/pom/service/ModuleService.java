package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.CustomException.NoSuchIDException;
import com.persistent.pom.CustomException.NullValueInsertion;
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
		try {
			return moduleRepo.save(module);
		}
		catch(Exception e) {
			throw new NullValueInsertion();
		}
		
	}
	
	public Module getModule(int id) {
		try{
			return moduleRepo.findById(id).get();
		}catch(Exception e) {
		   throw new NoSuchIDException();
		}
		
	}
	
	public Module updateModule(Module module) {
		try {
			return moduleRepo.save(module);
		}
		catch(Exception e) {
			throw new NullValueInsertion();
		}
		
	}
	
	public void deleteModule(int id) {
		try{
			moduleRepo.deleteById(id);
		}catch(Exception e) {
		   throw new NoSuchIDException();
		}
		
	}
	
	
	
	

}
