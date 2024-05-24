package com.mobabuild.api_build.controller.comand;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.FavoriteBuild;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserComand {
    private Long id;
    private String email;
    private String user_name;
    private String pass;
    private byte[] image;
    //private List<Build> builds = new ArrayList<>(); // No cargar builds aquí
    private List<Authority> authorities;
}
