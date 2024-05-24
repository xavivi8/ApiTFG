package com.mobabuild.api_build.service.impl;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.persistence.IUserDAO;
import com.mobabuild.api_build.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public User findByUserAndPass(String email, String pass) {
        return userDAO.findByUserAndPass(email, pass);
    }

    @Override
    public User updateUser(User newUser) {
        Optional<User> existingUserOptional = userDAO.findById(newUser.getId());
        List<Authority> authorities = new ArrayList<>();
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setEmail(newUser.getEmail());
            existingUser.setUser_name(newUser.getUser_name());
            existingUser.setPass(newUser.getPass());
            existingUser.setImage(newUser.getImage());
            existingUser.setAuthorities(newUser.getAuthorities());
            existingUser.setBuilds(newUser.getBuilds());
            existingUser.setFavoriteBuild(newUser.getFavoriteBuild());
            return userDAO.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User addUserWithoutImage(String email, String user_name, String pass) {
        User newUser = User.builder()
                .email(email)
                .user_name(user_name)
                .pass(pass)
                .build();
        return userDAO.save(newUser);
    }
}
