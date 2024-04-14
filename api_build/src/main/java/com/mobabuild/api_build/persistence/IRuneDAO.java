package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.Rune;

import java.util.List;
import java.util.Optional;

public interface IRuneDAO {

    List<Rune> findAll();

    Optional<Rune> findById(Long id);

    void save(Rune rune);

    void deleteById(Long id);
}
