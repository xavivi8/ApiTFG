package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.BuildComand;
import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.BuildDTO;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Champions;

import java.util.List;
import java.util.Optional;

public interface IBuildService {

    List<Build> findAll();

    Optional<Build> findById(Long id);

    BuildDTO save(BuildComand buildComand);

    void deleteById(Long id);

    List<BuildDTO> findByChampionsId(ChampionComand championComand);
}
