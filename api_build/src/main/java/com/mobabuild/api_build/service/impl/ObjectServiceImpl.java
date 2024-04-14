package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.persistence.IObjectDAO;
import com.mobabuild.api_build.service.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectServiceImpl implements IObjectService {

    @Autowired
    private IObjectDAO objectDAO;

    @Override
    public List<Object> findAll() {
        return objectDAO.findAll();
    }

    @Override
    public Optional<Object> findById(Long id) {
        return objectDAO.findById(id);
    }

    @Override
    public void save(Object object) {
        objectDAO.save(object);
    }

    @Override
    public void deleteById(Long id) {
        objectDAO.deleteById(id);
    }
}
