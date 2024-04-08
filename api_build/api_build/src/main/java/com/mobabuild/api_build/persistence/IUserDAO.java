package com.mobabuild.api_build.persistence;

import com.mobabuild.api_build.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<User> findAll();

    Optional<User> findById(Long id);

    void save(User user);

    void deleteById(Long id);

    User findByUserAndPass(String email, String pass);
}
