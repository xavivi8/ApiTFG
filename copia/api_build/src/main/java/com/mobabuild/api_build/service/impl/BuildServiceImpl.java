package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.service.IBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildServiceImpl implements IBuildService {

    @Autowired
    private IBuildDAO buildDAO;

    @Override
    public List<Build> findAll() {
        return buildDAO.findAll();
    }

    @Override
    public Optional<Build> findById(Long id) {
        return buildDAO.findById(id);
    }

    @Override
    public void save(Build build) {
        buildDAO.save(build);
    }

    @Override
    public void deleteById(Long id) {
        buildDAO.deleteById(id);
    }
}
