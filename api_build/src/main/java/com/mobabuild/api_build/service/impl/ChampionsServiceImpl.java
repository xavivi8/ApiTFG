package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.*;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.persistence.IBuildDAO;
import com.mobabuild.api_build.persistence.IChampionsDAO;
import com.mobabuild.api_build.service.IChampionsService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChampionsServiceImpl implements IChampionsService {

    @Autowired
    private IChampionsDAO championsDAO;

    @Override
    public List<ChampionsDTO> findAll() {
        List<Champions> champions = championsDAO.findAll();
        List<ChampionsDTO> championsDTOS = new ArrayList<>();

        if(!champions.isEmpty()){
            championsDTOS = new ArrayList<>();
            for(Champions champion : champions){
                // Convierte los builds a BuildDTO
                List<BuildDTO> buildDTOs = champion.getBuilds().stream()
                        .map(build -> BuildDTO.builder()
                                .id(build.getId())
                                .buildName(build.getBuildName())
                                .user(UserDTO.builder() // Conversión directa de User a UserDTO
                                        .id(build.getUser().getId())
                                        .user_name(build.getUser().getUser_name())
                                        .email(build.getUser().getEmail())
                                        .build())
                                .spellSets(build.getSpellSets().stream() // Conversión directa de SpellSet a SpellSetDTO
                                        .map(spellSet -> SpellSetDTO.builder()
                                                .id(spellSet.getId())
                                                .name(spellSet.getName())
                                                .build())
                                        .collect(Collectors.toList()))
                                .objectSet(build.getObjectSet().stream() // Conversión directa de ObjectSet a ObjectSetDTO
                                        .map(objectSet -> ObjectSetDTO.builder()
                                                .id(objectSet.getId())
                                                .name(objectSet.getName())
                                                // Agrega más campos según sea necesario
                                                .build())
                                        .collect(Collectors.toList()))
                                .runeSet(null)
                                .build())
                        .collect(Collectors.toList());

                ChampionsDTO championsDTO = ChampionsDTO.builder()
                        .id(champion.getId())
                        .name(champion.getName())
                        .builds(buildDTOs)
                        .build();
                championsDTOS.add(championsDTO);

            }
            return championsDTOS;
        } else {
            return championsDTOS;
        }
    }

    @Override
    public Optional<Champions> findById(Long id) {
        return championsDAO.findById(id);
    }

    @Override
    public ChampionsDTO save(ChampionComand championComand) {
        Champions champions = convertToChampion(championComand);

        championsDAO.save(champions);

        return createChampionDTO(champions);
    }

    @Override
    public void deleteById(Long id) {
        championsDAO.deleteById(id);
    }

    @Override
    public int setChampion(String name) {
        return championsDAO.setChampion(name);
    }

    @Override
    public ChampionsDTO updateChampion(ChampionComand championComand) {
        Optional<Champions> championsOptional = championsDAO.findById(championComand.getId());
        if (championsOptional.isPresent()) {
            Champions championsExist = championsOptional.get();
            championsExist.setName(championComand.getName());
            championsExist.setImage(BlobUtils.bytesToBlob(championComand.getImage()));

            ChampionsDTO championsDTO = ChampionsDTO.builder()
                    .id(championsExist.getId())
                    .name(championsExist.getName())
                    .image(championComand.getImage())
                    .builds(null) // Asumiendo que no necesitas devolver la lista de builds en el DTO
                    .build();

            championsDAO.save(championsExist);
            return championsDTO;
        } else {
            throw new RuntimeException("Champion not found");
        }
    }

    private Champions convertToChampion(ChampionComand championComand){
        return Champions.builder()
                .id(championComand.getId())
                .name(championComand.getName())
                .image(BlobUtils.bytesToBlob(championComand.getImage()))
                .build();
    }

    private ChampionsDTO createChampionDTO(Champions champions){
        return ChampionsDTO.builder()
                .id(champions.getId())
                .name(champions.getName())
                .image(BlobUtils.blobToBytes(champions.getImage()))
                .build();
    }
}
