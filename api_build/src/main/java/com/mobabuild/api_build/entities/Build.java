package com.mobabuild.api_build.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "build" )
public class Build {

    public Build(Long id, String buildName, User user, Champions champions, List<SpellSet> spellSets, List<ObjectSet> objectSet, List<RuneSet> runeSet) {
        this.id = id;
        this.buildName = buildName;
        this.user = user;
        this.champions = champions;
        this.spellSets = spellSets;
        this.objectSet = objectSet;
        this.runeSet = runeSet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "build_name")
    private String buildName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "champions_id", referencedColumnName = "id")
    private Champions champions;

    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<SpellSet> spellSets = new ArrayList<>();

    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<ObjectSet> objectSet = new ArrayList<>();

    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    @Builder.Default
    private List<RuneSet> runeSet = new ArrayList<>();
}
