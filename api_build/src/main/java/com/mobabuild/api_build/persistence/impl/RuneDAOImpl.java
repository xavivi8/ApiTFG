package com.mobabuild.api_build.persistence.impl;

import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.persistence.IRuneDAO;
import com.mobabuild.api_build.repository.RuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RuneDAOImpl implements IRuneDAO {

    @Autowired
    private RuneRepository runeRepository;

    @Override
    public List<Rune> findAll() {
        return (List<Rune>) runeRepository.findAll();
    }

    @Override
    public Optional<Rune> findById(Long id) {
        return runeRepository.findById(id);
    }

    @Override
    public void save(Rune rune) {
        runeRepository.save(rune);
    }

    @Override
    public void deleteById(Long id) {
        runeRepository.deleteById(id);
    }
}
