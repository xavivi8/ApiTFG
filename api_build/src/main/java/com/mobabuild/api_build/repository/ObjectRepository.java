package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Object;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends CrudRepository<Object, Long> {
}
