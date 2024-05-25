package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.ChampionComand;
import com.mobabuild.api_build.controller.dto.ChampionsDTO;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.Champions;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IChampionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/champions")
public class ChampionsController {

    @Autowired
    private IChampionsService championsService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Champions> championsOptional = championsService.findById(id);

        if(championsOptional.isPresent()){
            Champions champions = championsOptional.get();

            ChampionsDTO championsDTO = ChampionsDTO.builder()
                    .id(champions.getId())
                    .name(champions.getName())
                    .builds(champions.getBuilds())
                    .build();

            return  ResponseEntity.ok(championsDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<Champions> champions = championsService.findAll();

        if(!champions.isEmpty()){
            List<ChampionsDTO> championsDTOS = new ArrayList<>();
            for(Champions champion : champions){
                ChampionsDTO championsDTO = ChampionsDTO.builder()
                        .id(champion.getId())
                        .name(champion.getName())
                        .builds(champion.getBuilds())
                        .build();
                championsDTOS.add(championsDTO);
            }
            return ResponseEntity.ok(championsDTOS);
        }

        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            championsService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @GetMapping("/setChampion/{name}")
    public ResponseEntity<?> setChampion(@PathVariable String name){
        int response = championsService.setChampion(name);

        if(response == 1){

            return ResponseEntity.ok(1);
        }

        return  ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateChampion(@RequestBody ChampionComand championComand){
        try {
            ChampionsDTO championsDTO = championsService.updateChampion(championComand);
            return ResponseEntity.ok(championsDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
