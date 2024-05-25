package com.mobabuild.api_build.controller.comand;

import com.mobabuild.api_build.entities.Build;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChampionComand {
    private Long id;
    private String name;
    private List<Build> builds;
}
