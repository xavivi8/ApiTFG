package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.ChampionsDTO;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.persistence.IChampionsDAO;
import com.mobabuild.api_build.service.IChampionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChampionsServiceImpl implements IChampionsService {

    @Autowired
    private IChampionsDAO championsDAO;

    @Override
    public List<Champions> findAll() {
        return championsDAO.findAll();
    }

    @Override
    public Optional<Champions> findById(Long id) {
        return championsDAO.findById(id);
    }

    @Override
    public void save(Champions champions) {
        championsDAO.save(champions);
    }

    @Override
    public void deleteById(Long id) {
        championsDAO.deleteById(id);
    }

    @Override
    public int setChampion(String name) {
        return championsDAO.setChampion(name);
    }

    @Override
    public ChampionsDTO updateChampion(ChampionComand championComand) {
        Optional<Champions> championsOptional = championsDAO.findById(championComand.getId());
        if(championsOptional.isPresent()){
            Champions championsExist = Champions.builder()
                    .id(championsOptional.get().getId())
                    .name(championsOptional.get().getName())
                    .builds(championsOptional.get().getBuilds())
                    .build();

            ChampionsDTO championsDTO =ChampionsDTO.builder()
                    .id(championsExist.getId())
                    .name(championsExist.getName())
                    .builds(championsExist.getBuilds())
                    .build();

            championsDAO.save(championsExist);
            return championsDTO;
        } else {
            throw new RuntimeException("Champion not found");
        }
    }
}
