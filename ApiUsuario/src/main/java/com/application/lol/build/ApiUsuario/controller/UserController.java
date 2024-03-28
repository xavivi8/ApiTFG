package com.application.lol.build.ApiUsuario.controller;

import com.application.lol.build.ApiUsuario.controller.dto.UserDTO;
import com.application.lol.build.ApiUsuario.entities.User;
import com.application.lol.build.ApiUsuario.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            UserDTO userDTO = UserDTO.builder()
                    .id_user(user.getId_user())
                    .name(user.getName())
                    .user(user.getUser())
                    .password(user.getPassword())
                    .image(user.getImage())
                    .build();
            return  ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
