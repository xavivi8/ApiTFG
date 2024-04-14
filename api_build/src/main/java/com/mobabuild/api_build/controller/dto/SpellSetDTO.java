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
public class SpellSetDTO {

    private Long id;
    private Build build;
}
