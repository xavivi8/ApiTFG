package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.controller.request.AddUserRequest;
import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.User;
import com.mobabuild.api_build.service.IAuthorityService;
import com.mobabuild.api_build.service.IUserService;
import com.mobabuild.api_build.utils.AuthorityName;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IAuthorityService authorityService;

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
                    .authorities(user.getAuthorities())
                    .build();
            return  ResponseEntity.ok(userDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/update")
    public ResponseEntity<UserDTO> updateUser(
            @RequestParam Long id,
            @RequestParam String email,
            @RequestParam String userName,
            @RequestParam String pass) {

        User updatedUser = userService.updateUser(id, email, userName, pass);
        Hibernate.initialize(updatedUser.getAuthorities());
        UserDTO userDTO = UserDTO.builder()
                .id(updatedUser.getId())
                .email(updatedUser.getEmail())
                .user_name(updatedUser.getUser_name())
                .pass(updatedUser.getPass())
                .image(updatedUser.getImage())
                .authorities(updatedUser.getAuthorities())
                .build();
        return ResponseEntity.ok(userDTO);
    }

    // Endpoint para añadir un usuario sin imagen usando GET
    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUserWithoutImage(@Validated @RequestBody AddUserRequest addUserRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return  ResponseEntity.notFound().build();
        }
        // Extraer los datos del objeto de solicitud
        String email = addUserRequest.getEmail();
        String userName = addUserRequest.getUserName();
        String pass = addUserRequest.getPass();
        List<String> authorityNames = addUserRequest.getAuthorityNames();

        // Crea una lista para almacenar las autoridades asociadas al nuevo usuario
        List<Authority> authorities = new ArrayList<>();

        // Busca las autoridades por nombre y agrégalas a la lista
        for (String authorityName : authorityNames) {
            AuthorityName authorityNameEnum;
            try {
                authorityNameEnum = AuthorityName.valueOf(authorityName);
            } catch (IllegalArgumentException e) {
                // Maneja el caso donde el nombre de la autoridad no es válido
                return ResponseEntity.badRequest().build();
            }

            Optional<Authority> authorityOptional = authorityService.findByName(authorityNameEnum);
            if (authorityOptional.isEmpty()) {
                // Maneja el caso donde no se encuentra ninguna autoridad con el nombre proporcionado
                return ResponseEntity.notFound().build();
            }

            authorities.add(authorityOptional.get());
        }

        // Crea el nuevo usuario
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUser_name(userName);
        newUser.setPass(pass);
        // Asigna las autoridades al nuevo usuario
        newUser.setAuthorities(authorities);

        // Guarda el nuevo usuario en la base de datos
        newUser = userService.save(newUser);

        // Convierte el nuevo usuario a DTO para enviarlo como respuesta
        UserDTO userDTO = UserDTO.builder()
                .id(newUser.getId())
                .email(newUser.getEmail())
                .user_name(newUser.getUser_name())
                .pass(newUser.getPass())
                .image(newUser.getImage())
                .authorities(newUser.getAuthorities())
                .build();

        // Devuelve el nuevo usuario creado como respuesta
        return ResponseEntity.ok(userDTO);
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();

        List<UserDTO> userDTOs = users.stream().map(user -> {
            // Inicializar la colección authorities antes de acceder a ella
            Hibernate.initialize(user.getAuthorities());

            return UserDTO.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .user_name(user.getUser_name())
                    .pass(user.getPass())
                    .image(user.getImage())
                    .authorities(user.getAuthorities())
                    .build();
        }).collect(Collectors.toList());

        return ResponseEntity.ok(userDTOs);
    }
}
