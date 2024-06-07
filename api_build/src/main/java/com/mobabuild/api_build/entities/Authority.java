package com.mobabuild.api_build.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mobabuild.api_build.utils.AuthorityName;
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
@Table(name = "authorities" )
public class Authority {

    public Authority(AuthorityName authorityName){
        this.name = authorityName;
    }

    public Authority(String authorityName) {
        this.name = AuthorityName.valueOf(authorityName);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("authorities")
    @Builder.Default
    private List<User> users = new ArrayList<>();
}
