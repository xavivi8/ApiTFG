package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.persistence.IRuneDAO;
import com.mobabuild.api_build.service.IRuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuneServiceImpl implements IRuneService {

    @Autowired
    private IRuneDAO runeDAO;

    @Override
    public List<Rune> findAll() {
        return runeDAO.findAll();
    }

    @Override
    public Optional<Rune> findById(Long id) {
        return runeDAO.findById(id);
    }

    @Override
    public void save(Rune rune) {
        runeDAO.save(rune);
    }

    @Override
    public void deleteById(Long id) {
        runeDAO.deleteById(id);
    }
}
