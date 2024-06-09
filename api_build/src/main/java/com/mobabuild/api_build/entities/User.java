package com.mobabuild.api_build.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mobabuild.api_build.utils.BlobDeserializer;
import com.mobabuild.api_build.utils.BlobSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;
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

    public User(String email, String user_name, String pass, List<Authority> authorities){
        this.email = email;
        this.user_name = user_name;
        this.pass = pass;
        this.authorities = authorities;
    }

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

    @Column(name = "image", columnDefinition = "LONGBLOB")
    //@JsonSerialize(using = BlobSerializer.class)
    //@JsonDeserialize(using = BlobDeserializer.class)
    private Blob image;

    /*
    * FetchType.LAZ para no sobrecargar el listado
    * orphanRemoval = true para eliminar las builds asociadas al usuario
    * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<Build> builds = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "name_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties("users")
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private FavoriteBuild favoriteBuild;

    // Métodos para sincronizar relaciones bidireccionales
    public void addBuild(Build build) {
        builds.add(build);
        build.setUser(this);
    }

    public void removeBuild(Build build) {
        builds.remove(build);
        build.setUser(null);
    }
}
