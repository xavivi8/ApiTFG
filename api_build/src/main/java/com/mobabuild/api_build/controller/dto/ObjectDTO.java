package com.mobabuild.api_build.controller.dto;

import com.mobabuild.api_build.entities.ObjectSet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectDTO {
    private Long id;
    private String name;
    private List<ObjectSet> objectSets = new ArrayList<>();
}
