package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.Build;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuneSetDTO {

    private Long id;
    private String name;
    private String main_rune;
    private String main_sub_rune;
    private String secondary_rune;
    private String secondary_sub_rune;
    private String additional_advantages;
    private Build build;
}
