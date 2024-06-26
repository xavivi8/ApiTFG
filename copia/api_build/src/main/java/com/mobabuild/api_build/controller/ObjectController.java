package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/object")
public class ObjectController {

    @Autowired
    private IObjectService objectService;

    @GetMapping("/findById/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Object> objectOptional = objectService.findById(id);

        if(objectOptional.isPresent()){
            Object object = objectOptional.get();

            ObjectDTO objectDTO = ObjectDTO.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .objectSets(object.getObjectSets())
                    .build();

            return  ResponseEntity.ok(objectDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
    public ResponseEntity<?> findAll(){
        List<Object> objects = objectService.findAll();

        if(!objects.isEmpty()){
            List<ObjectDTO> objectDTOs = new ArrayList<>();
            for(Object object : objects){
                ObjectDTO objectDTO = ObjectDTO.builder()
                        .id(object.getId())
                        .name(object.getName())
                        .objectSets(object.getObjectSets())
                        .build();
                objectDTOs.add(objectDTO);
            }
            return ResponseEntity.ok(objectDTOs);
        }

        return  ResponseEntity.notFound().build();
    }


    @GetMapping("/deleteById/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            objectService.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }



    @GetMapping("/setObject/{name}")
    @CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
    public ResponseEntity<?> setObject(@PathVariable String name){
        int response = objectService.setObject(name);

        if(response == 1){

            return ResponseEntity.ok(1);
        }

        return  ResponseEntity.notFound().build();
    }
}
