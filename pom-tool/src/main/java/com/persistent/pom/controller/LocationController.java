package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Location;
import com.persistent.pom.repositories.LoactionRepo;

@RestController
public class LocationController {

	@Autowired
	LoactionRepo locationRepo;

	@GetMapping(value = "/locations")
	public List<Location> getLocations( ) {
		return (List<Location>) locationRepo.findAll();
	}

}
