package com.application.lol.build.ApiUsuario.service.impl;

import com.application.lol.build.ApiUsuario.entities.User;
import com.application.lol.build.ApiUsuario.persistence.IUserDAO;
import com.application.lol.build.ApiUsuario.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findByUserAndPass(String user, String pass) {
        return userDAO.findByUserAndPass(user, pass);
    }
}
