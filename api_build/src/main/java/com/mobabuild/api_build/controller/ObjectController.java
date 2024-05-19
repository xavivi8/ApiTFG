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
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            objectService.deleteById(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }



    @GetMapping("/setObject/{name}")
    public ResponseEntity<?> setObject(@PathVariable String name){
        int response = objectService.setObject(name);

        if(response == 1){

            return ResponseEntity.ok(1);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/updateObjectById/{id}/{name}")
    public ResponseEntity<String> updateObjectName(@PathVariable Long id, @PathVariable String name) {
        boolean isUpdated = objectService.updateObjectName(id, name);
        if (isUpdated) {
            return ResponseEntity.ok("1");
        } else {
            return ResponseEntity.status(404).body("0");
        }
    }
}
