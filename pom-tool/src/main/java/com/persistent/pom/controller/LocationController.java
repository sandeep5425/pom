package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Location;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.LocationService;

@RestController
public class LocationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	LocationService locationService;

	@GetMapping(value = "/locations")
	public ResponseEntity<ResponseMessage<List<Location>>> getLocations() throws Exception {
		LOGGER.info("Entered Roles controller to get the list of roles :: method = getRoles");
		List<Location> locations = locationService.getLocations();
		ResponseMessage<List<Location>> response = new ResponseMessage<>();
		if (locations.size() == 0) {
			response.setMessage("No locations found");
			response.setData(new ArrayList<>());
			response.setLength(0);
			LOGGER.info("Exit Locations controller  as no locations found :: method = getLocations");
			return new ResponseEntity<ResponseMessage<List<Location>>>(response, HttpStatus.NO_CONTENT);
		}

		response.setLength(locations.size());
		response.setMessage("List of locations");
		response.setData(locations);
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Locations controller with list of locations :: method = getLocations");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
