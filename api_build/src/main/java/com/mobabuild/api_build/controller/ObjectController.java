package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.service.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/object")
public class ObjectController {

    @Autowired
    private IObjectService objectService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Object> objectOptional = objectService.findById(id);

        if(objectOptional.isPresent()){
            Object object = objectOptional.get();

            ObjectDTO objectDTO = ObjectDTO.builder()
                    .id(object.getId())
                    .name(object.getName())
                    .objectSets(object.getObjectSets())
                    .build();

            return ResponseEntity.ok(objectDTO);
        }

        return  ResponseEntity.notFound().build();
    }

    @GetMapping("/setObject/{name}")
    public ResponseEntity<?> setObject(@PathVariable String name){
        int response = objectService.setObject(name);

        if(response == 1){

            return ResponseEntity.ok().build();
        }

        return  ResponseEntity.notFound().build();
    }
}
