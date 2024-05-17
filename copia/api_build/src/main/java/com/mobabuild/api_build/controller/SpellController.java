package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.service.ISpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spell")
public class SpellController {

    @Autowired
    private ISpellService spellService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Spell> spellOptional = spellService.findById(id);

        if(spellOptional.isPresent()){
            Spell spell = spellOptional.get();

            SpellDTO spellDTO = SpellDTO.builder()
                    .id(spell.getId())
                    .name(spell.getName())
                    .champion_level(spell.getChampion_level())
                    .game_mode(spell.getGame_mode())
                    .description(spell.getDescription())
                    .cooldown(spell.getCooldown())
                    .image(spell.getImage())
                    .spellSets(spell.getSpellSets())
                    .build();

            return  ResponseEntity.ok(spellDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Spell> spellList = spellService.findAll();

        if(!spellList.isEmpty()){
            List<SpellDTO> spellDTOList = new ArrayList<>();
            for(Spell spell : spellList){
                SpellDTO spellDTO = SpellDTO.builder()
                        .id(spell.getId())
                        .name(spell.getName())
                        .champion_level(spell.getChampion_level())
                        .game_mode(spell.getGame_mode())
                        .description(spell.getDescription())
                        .cooldown(spell.getCooldown())
                        .image(spell.getImage())
                        .spellSets(spell.getSpellSets())
                        .build();
                spellDTOList.add(spellDTO);
            }
            return ResponseEntity.ok(spellDTOList);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        spellService.deleteById(id);

        return ResponseEntity.ok("ok");
    }
}
