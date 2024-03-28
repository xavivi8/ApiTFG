package com.application.lol.build.ApiUsuario.persistence;

import com.application.lol.build.ApiUsuario.entities.User;

import java.util.List;

public interface IUserDAO {

    List<User> findAll();

    User findById(Long id);

    void save(User user);

    void deleteById(Long id);
}
