package com.mobabuild.api_build.controller.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuneDTO {

    private Long id;
    private String name;
    private String rowType;
    private String group_name;
    private String description;
    private String long_description;
    private byte[] image;
}
