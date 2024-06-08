package com.mobabuild.api_build.service;

import com.mobabuild.api_build.controller.comand.UserComand;
import com.mobabuild.api_build.controller.comand.UserLoginComand;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO findById(Long id);

    User save(User user);

    void deleteById(Long id);

    UserDTO findByUserAndPass(UserLoginComand userLoginComand);

    UserDTO updateUser(UserComand newUser);

    User addUserWithoutImage(String email, String user_name, String pass);
}
