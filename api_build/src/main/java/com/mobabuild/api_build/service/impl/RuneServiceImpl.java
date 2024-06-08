package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.controller.comand.RuneComand;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.persistence.IRuneDAO;
import com.mobabuild.api_build.service.IRuneService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuneServiceImpl implements IRuneService {

    @Autowired
    private IRuneDAO runeDAO;

    @Override
    public List<RuneDTO> findAll() {
        List<Rune> runeList = runeDAO.findAll();
        List<RuneDTO> runeDTOList = new ArrayList<>();
        if (!runeList.isEmpty()) {

            for (Rune rune : runeList) {
                RuneDTO runeDTO = RuneDTO.builder()
                        .id(rune.getId())
                        .name(rune.getName())
                        .rowType(rune.getRowType())
                        .group_name(rune.getGroup_name())
                        .description(rune.getDescription())
                        .long_description(rune.getLong_description())
                        .image(BlobUtils.blobToBytes(rune.getImage()))
                        .build();
                runeDTOList.add(runeDTO);
            }
            return runeDTOList;
        } else {
            return runeDTOList;
        }
    }

    @Override
    public RuneDTO findById(Long id) {
        Optional<Rune> runeOptional = runeDAO.findById(id);

        if (runeOptional.isPresent()) {
            Rune rune = runeOptional.get();

            RuneDTO runeDTO = RuneDTO.builder()
                    .id(rune.getId())
                    .name(rune.getName())
                    .rowType(rune.getRowType())
                    .group_name(rune.getGroup_name())
                    .description(rune.getDescription())
                    .long_description(rune.getLong_description())
                    .image(BlobUtils.blobToBytes(rune.getImage()))
                    .build();

            return runeDTO;
        } else {
            return RuneDTO.builder().build();
        }
    }

    @Override
    public RuneDTO save(RuneComand runeComand) {
        Rune rune = Rune.builder()
                .name(runeComand.getName())
                .rowType(runeComand.getRowType())
                .group_name(runeComand.getGroup_name())
                .description(runeComand.getDescription())
                .long_description(runeComand.getLong_description())
                .image(BlobUtils.bytesToBlob(runeComand.getImage()))
                .build();

        runeDAO.save(rune);

        RuneDTO runeDTO = RuneDTO.builder()
                .name(runeComand.getName())
                .rowType(runeComand.getRowType())
                .group_name(runeComand.getGroup_name())
                .description(runeComand.getDescription())
                .long_description(runeComand.getLong_description())
                .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(runeComand.getImage())))
                .build();

        return runeDTO;
    }

    @Override
    public void deleteById(Long id) {
        runeDAO.deleteById(id);
    }

    @Override
    public int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image) {
        return runeDAO.insertRune(name, row, group_name, description, long_description, image);
    }

    @Override
    public int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description) {
        return runeDAO.insertRuneWithoutImage(name, row, group_name, description, long_description);
    }

    @Override
    public RuneDTO update(RuneComand runeComand) {
        Optional<Rune> runeOptional = runeDAO.findById(runeComand.getId());
        if(runeOptional.isPresent()){
            Rune rune = Rune.builder()
                    .id(runeComand.getId())
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.bytesToBlob(runeComand.getImage()))
                    .build();

            runeDAO.save(rune);

            RuneDTO runeDTO = RuneDTO.builder()
                    .id(runeComand.getId())
                    .name(runeComand.getName())
                    .rowType(runeComand.getRowType())
                    .group_name(runeComand.getGroup_name())
                    .description(runeComand.getDescription())
                    .long_description(runeComand.getLong_description())
                    .image(BlobUtils.blobToBytes(BlobUtils.bytesToBlob(runeComand.getImage())))
                    .build();

            return runeDTO;
        }else {
            throw new RuntimeException("Champion not found");
        }
    }
}
