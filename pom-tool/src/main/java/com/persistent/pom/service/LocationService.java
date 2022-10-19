package com.persistent.pom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.persistent.pom.dto.LocationDto;
import com.persistent.pom.repositories.LoactionRepo;

public class LocationService {

	@Autowired
	LoactionRepo locationRepo;
	
	public List<LocationDto> getLocations(){
		
		return  null;
	}
	
	

}
