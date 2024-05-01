package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Champions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionsRepository extends CrudRepository<Champions, Long> {
}
