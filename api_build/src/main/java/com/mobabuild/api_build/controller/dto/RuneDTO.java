package com.mobabuild.api_build.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuneDTO {

    private Long id;
    private String name;
    private String row;
    private String group;
    private String description;
    private String long_description;
    private byte[] image;
}
