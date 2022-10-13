package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Location;

public interface LocationsRepo extends CrudRepository<Location, Integer> {
	
}
