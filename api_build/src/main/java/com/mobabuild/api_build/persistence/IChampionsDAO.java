package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Champions;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

public interface IChampionsDAO {

    List<Champions> findAll();

    Optional<Champions> findById(Long id);

    void save(Champions champions);

    void deleteById(Long id);

    int setChampion(String name);

    int updateChampion(Long id, String name, Blob image);
}
