package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Build;
import com.mobabuild.api_build.entities.Spell;
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
public class SpellSetDTO {

    private Long id;
    private String name;
    private Build build;
    private List<Spell> spells = new ArrayList<>();
}
