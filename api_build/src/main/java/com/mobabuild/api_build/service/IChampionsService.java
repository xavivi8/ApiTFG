package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.Champions;

import java.util.List;
import java.util.Optional;

public interface IChampionsService {

    List<Champions> findAll();

    Optional<Champions> findById(Long id);

    void save(Champions champions);

    void deleteById(Long id);
}
