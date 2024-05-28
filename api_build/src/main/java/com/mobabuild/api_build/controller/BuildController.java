package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.BuildComand;
import com.mobabuild.api_build.controller.comand.ObjectComand;
import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.*;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/build")
public class BuildController {

    @Autowired
    private IBuildService buildService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BuildComand buildComand){
        try {
            Build newBuild = convertToBuild(buildComand);

            buildService.save(newBuild);

            UserDTO userDTO = convertToUserDTO(buildComand.getUser());

            BuildDTO buildDTO = BuildDTO.builder()
                    .buildName(buildComand.getBuildName())
                    .user(userDTO)
                    .champions(buildComand.getChampions())
                    .spellSets(buildComand.getSpellSets().stream() // Conversión directa de SpellSet a SpellSetDTO
                            .map(spellSet -> SpellSetDTO.builder()
                                    .id(spellSet.getId())
                                    .name(spellSet.getName())
                                    .build())
                            .collect(Collectors.toList()))
                    .objectSet(buildComand.getObjectSet().stream() // Conversión directa de ObjectSet a ObjectSetDTO
                            .map(objectSet -> ObjectSetDTO.builder()
                                .id(objectSet.getId())
                                .name(objectSet.getName())
                                // Agrega más campos según sea necesario
                                .build())
                    .collect(Collectors.toList()))
                    .runeSet(buildComand.getRuneSet().stream()
                            .map(runeSet -> RuneSetDTO.builder()
                                    .id(runeSet.getId())
                                    .name(runeSet.getName())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();

            return ResponseEntity.ok(buildDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al añadir la build: " + e.getMessage());
        }
    }

    private Build convertToBuild(BuildComand buildComand){
        List<SpellSet> spellSets = buildComand.getSpellSets().stream()
                .map(spellSet -> SpellSet.builder()
                        .id(spellSet.getId())
                        .name(spellSet.getName())
                        .spells(spellSet.getSpells().stream()
                                .map(this::convertToSpell)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<ObjectSet> objectSets = buildComand.getObjectSet().stream()
                .map(objectSet -> ObjectSet.builder()
                        .id(objectSet.getId())
                        .name(objectSet.getName())
                        .objects(objectSet.getObjects().stream()
                                .map(this::convertToObject)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        List<RuneSet> runeSets = buildComand.getRuneSet().stream()
                .map(runeSet -> RuneSet.builder()
                        .id(runeSet.getId())
                        .name(runeSet.getName())
                        .main_rune(runeSet.getMain_rune())
                        .main_sub_rune(runeSet.getMain_sub_rune())
                        .secondary_rune(runeSet.getSecondary_rune())
                        .secondary_sub_rune(runeSet.getSecondary_sub_rune())
                        .additional_advantages(runeSet.getAdditional_advantages())
                        .build())
                .collect(Collectors.toList());

        return Build.builder()
                .buildName(buildComand.getBuildName())
                .user(buildComand.getUser())
                .champions(buildComand.getChampions())
                .spellSets(spellSets)
                .objectSet(objectSets)
                .runeSet(runeSets)
                .build();
    }

    private Spell convertToSpell(SpellComand spellComand) {
        return Spell.builder()
                .id(spellComand.getId())
                .name(spellComand.getName())
                .champion_level(spellComand.getChampion_level())
                .game_mode(spellComand.getGame_mode())
                .description(spellComand.getDescription())
                .cooldown(spellComand.getCooldown())
                .image(spellComand.getImage())
                .build();
    }

    private Object convertToObject(ObjectComand objectComand) {
        return Object.builder()
                .id(objectComand.getId())
                .name(objectComand.getName())
                .build();
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .user_name(user.getUser_name())
                .pass(user.getPass())
                .image(user.getImage())
                //.builds(user.getBuilds())
                .build();

        // Si deseas incluir la conversión de otras entidades dentro de User, hazlo aquí

        return userDTO;
    }


}
