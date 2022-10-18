package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.ProjectFile;
import com.persistent.pom.repositories.ProjectFileRepo;

@Component
public class ProjectFileService {
	@Autowired
	ProjectFileRepo projectFileRepo;

	public List<ProjectFile> getFiles() {
		return (List<ProjectFile>) projectFileRepo.findAll();
	}

	public Optional<ProjectFile> getFile(int id) throws IllegalArgumentException {
		return projectFileRepo.findById(id);
	}

	public ProjectFile addFile(ProjectFile file) throws IllegalArgumentException {
		return projectFileRepo.save(file);
	}

	public ProjectFile updateFile(ProjectFile file) throws IllegalArgumentException {
		return projectFileRepo.save(file);
	}

	public void deleteFile(int id) throws IllegalArgumentException {
		projectFileRepo.deleteById(id);
	}
}
