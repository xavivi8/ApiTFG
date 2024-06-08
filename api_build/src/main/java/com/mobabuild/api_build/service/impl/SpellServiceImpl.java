package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.ISpellDAO;
import com.mobabuild.api_build.service.ISpellService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpellServiceImpl implements ISpellService {

    @Autowired
    private ISpellDAO spellDAO;

    @Override
    public List<SpellDTO> findAll() {
        List<Spell> spellList = spellDAO.findAll();
        List<SpellDTO> spellDTOList = new ArrayList<>();
        if(!spellList.isEmpty()){
            for(Spell spell : spellList){
                SpellDTO spellDTO = SpellDTO.builder()
                        .id(spell.getId())
                        .name(spell.getName())
                        .champion_level(spell.getChampion_level())
                        .game_mode(spell.getGame_mode())
                        .description(spell.getDescription())
                        .cooldown(spell.getCooldown())
                        .image(BlobUtils.blobToBytes(spell.getImage()))
                        //.spellSets(spell.getSpellSets())
                        .build();
                spellDTOList.add(spellDTO);
            }
            return spellDTOList;
        } else {
            return spellDTOList;
        }
    }

    @Override
    public SpellDTO findById(Long id) {
        Optional<Spell> spellOptional = spellDAO.findById(id);
        if(spellOptional.isPresent()){
            Spell spell = spellOptional.get();

            SpellDTO spellDTO = SpellDTO.builder()
                    .id(spell.getId())
                    .name(spell.getName())
                    .champion_level(spell.getChampion_level())
                    .game_mode(spell.getGame_mode())
                    .description(spell.getDescription())
                    .cooldown(spell.getCooldown())
                    .image(BlobUtils.blobToBytes(spell.getImage()))
                    //.spellSets(spell.getSpellSets())
                    .build();
            return spellDTO;
        } else {
            return SpellDTO.builder().build();
        }
    }

    @Override
    public SpellDTO save(SpellComand spellComand) {
        Spell newSpell = Spell.builder()
                .name(spellComand.getName())
                .champion_level(spellComand.getChampion_level())
                .game_mode(spellComand.getGame_mode())
                .description(spellComand.getDescription())
                .cooldown(spellComand.getCooldown())
                .image(BlobUtils.bytesToBlob(spellComand.getImage()))
                .build();

        spellDAO.save(newSpell);

        SpellDTO spellDTO = SpellDTO.builder()
                .name(spellComand.getName())
                .champion_level(spellComand.getChampion_level())
                .game_mode(spellComand.getGame_mode())
                .description(spellComand.getDescription())
                .cooldown(spellComand.getCooldown())
                .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(spellComand.getImage())))
                .build();

        return spellDTO;
    }

    @Override
    public void deleteById(Long id) {
        spellDAO.deleteById(id);
    }

    @Override
    public SpellDTO update(SpellComand spellComand) {
        Optional<Spell> spellOptional = spellDAO.findById(spellComand.getId());
        if(spellOptional.isPresent()){
            Spell spell = Spell.builder()
                    .id(spellComand.getId())
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.bytesToBlob(spellComand.getImage()))
                    .build();

            spellDAO.save(spell);

            SpellDTO spellDTO = SpellDTO.builder()
                    .id(spellComand.getId())
                    .name(spellComand.getName())
                    .champion_level(spellComand.getChampion_level())
                    .game_mode(spellComand.getGame_mode())
                    .description(spellComand.getDescription())
                    .cooldown(spellComand.getCooldown())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(spellComand.getImage())))
                    .build();

            return spellDTO;
        }else {
            throw new RuntimeException("Champion not found");
        }
    }
}
