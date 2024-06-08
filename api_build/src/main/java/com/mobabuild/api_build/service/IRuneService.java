package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.RuneComand;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.entities.Rune;

import java.util.List;
import java.util.Optional;

public interface IRuneService {

    List<RuneDTO> findAll();

    RuneDTO findById(Long id);

    RuneDTO save(RuneComand runeComand);

    void deleteById(Long id);

    int insertRune(String name, String row, String group_name, String description, String long_description, byte[] image);

    int insertRuneWithoutImage(String name, String row, String group_name, String description, String long_description);

    RuneDTO update(RuneComand runeComand);
}
