package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.*;
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
public class ChampionsDTO {

    private Long id;
    private String buildName;
    private List<Build> builds;
}
