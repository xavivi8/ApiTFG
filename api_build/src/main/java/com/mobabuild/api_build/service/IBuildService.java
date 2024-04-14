package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.Build;

import java.util.List;
import java.util.Optional;

public interface IBuildService {

    List<Build> findAll();

    Optional<Build> findById(Long id);

    void save(Build build);

    void deleteById(Long id);
}
