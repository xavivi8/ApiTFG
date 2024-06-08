package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Spell;

import java.util.List;
import java.util.Optional;

public interface ISpellService {

    List<SpellDTO> findAll();

    SpellDTO findById(Long id);

    SpellDTO save(SpellComand spellComand);

    void deleteById(Long id);

    SpellDTO update(SpellComand spellComand);
}
