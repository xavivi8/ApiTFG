package com.mobabuild.api_build.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users" )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "pass")
    private String pass;

    @Column(name = "image")
    private byte[] image;

    /*
    * FetchType.LAZ para no sobrecargar el listado
    * orphanRemoval = true para eliminar las builds asociadas al usuario
    * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Build> builds = new ArrayList<>();
}
