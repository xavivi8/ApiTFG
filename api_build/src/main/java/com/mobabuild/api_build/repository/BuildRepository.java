package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Build;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildRepository extends CrudRepository<Build, Long> {
}
