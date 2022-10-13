package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Location;
import com.persistent.pom.response.Response;
import com.persistent.pom.service.LocationsService;

@RestController
public class LocationsController {
	
	@Autowired
	LocationsService locationsService;

	
	@GetMapping(value = "/locations")
	public ResponseEntity<Object> getLocations() {
		List<Location> locations = locationsService.getLocations();
		return Response.createResponse("List of Locations", HttpStatus.OK, locations);
	}
}
