package com.mobabuild.api_build.repository;

import com.mobabuild.api_build.entities.Rune;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuneRepository extends CrudRepository<Rune, Long> {
}
