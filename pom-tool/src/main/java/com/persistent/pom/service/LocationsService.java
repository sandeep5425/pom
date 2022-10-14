package com.persistent.pom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Location;
import com.persistent.pom.repositories.LocationsRepo;

@Component
public class LocationsService {
	
	@Autowired
	LocationsRepo locationsRepo;

	public List<Location> getLocations() {
		return (List<Location>) locationsRepo.findAll();
	}
	

}
