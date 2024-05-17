package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.Hibernate;

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

            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(user.getImage())
                    .build();
            return  ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }
}
