package com.mobabuild.api_build.controller.comand;

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
public class ObjectComand {

    private Long id;
    private String name;
    private List<ObjectSetComand> objectSets = new ArrayList<>();
}
