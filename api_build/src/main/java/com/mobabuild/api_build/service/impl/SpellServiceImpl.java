package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.ISpellDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpellServiceImpl implements ISpellDAO {

    @Autowired
    private ISpellDAO spellDAO;

    @Override
    public List<Spell> findAll() {
        return spellDAO.findAll();
    }

    @Override
    public Optional<Spell> findById(Long id) {
        return spellDAO.findById(id);
    }

    @Override
    public void save(Spell spell) {
        spellDAO.save(spell);
    }

    @Override
    public void deleteById(Long id) {
        spellDAO.deleteById(id);
    }
}
