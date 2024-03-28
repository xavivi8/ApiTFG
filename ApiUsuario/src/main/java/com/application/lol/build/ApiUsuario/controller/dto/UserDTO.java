package com.application.lol.build.ApiUsuario.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id_user;
    private String name;
    private String user;
    private String password;
    private byte[] image;
}
