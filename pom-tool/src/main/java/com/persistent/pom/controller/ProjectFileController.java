package com.persistent.pom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.service.ProjectFileService;

@RestController
public class ProjectFileController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectFileController.class);
	
	@Autowired
	ProjectFileService projectFileService;
	

}
