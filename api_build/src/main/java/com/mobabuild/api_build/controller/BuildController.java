package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.BuildComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.entities.User;
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
            Build newBuild = Build.builder()
                    .buildName(buildComand.getBuildName())
                    .user(buildComand.getUser())
                    .champions(buildComand.getChampions())
                    .spellSets(buildComand.getSpellSets())
                    .objectSet(buildComand.getObjectSet())
                    .runeSet(buildComand.getRuneSet())
                    .build();

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
                    .runeSet(buildComand.getRuneSet())
                    .build();

            return ResponseEntity.ok(buildDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al añadir la build: " + e.getMessage());
        }
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
