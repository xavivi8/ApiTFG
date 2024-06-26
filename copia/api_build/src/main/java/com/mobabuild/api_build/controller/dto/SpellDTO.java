package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.SpellSet;
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
public class SpellDTO {

    private Long id;
    private String name;
    private int champion_level;
    private String game_mode;
    private String description;
    private String cooldown;
    private byte[] image;
    private List<SpellSet> spellSets = new ArrayList<>();
}
