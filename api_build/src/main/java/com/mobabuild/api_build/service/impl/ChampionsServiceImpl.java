package com.mobabuild.api_build.service.impl;

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
}
