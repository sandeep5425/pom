package com.persistent.pom.repositories;

import org.springframework.data.repository.CrudRepository;

import com.persistent.pom.entities.Remark;

public interface RemarkRepo extends CrudRepository<Remark, Integer> {

}
