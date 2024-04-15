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
@Table(name = "object_set" )
public class ObjectSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build", nullable = false)
    private Build build;

    @ManyToMany
    @JoinTable(
            name = "object_set_object",
            joinColumns = @JoinColumn(name = "object_set_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id")
    )
    private List<Object> objects = new ArrayList<>();
}
