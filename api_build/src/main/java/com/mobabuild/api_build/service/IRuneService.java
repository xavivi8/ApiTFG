package com.mobabuild.api_build.service;

import com.mobabuild.api_build.entities.Rune;

import java.util.List;
import java.util.Optional;

public interface IRuneService {

    List<Rune> findAll();

    Optional<Rune> findById(Long id);

    void save(Rune rune);

    void deleteById(Long id);
}
