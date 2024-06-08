package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.SpellComand;
import com.mobabuild.api_build.controller.dto.ChampionsDTO;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.controller.dto.UserDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Spell;
import com.mobabuild.api_build.service.ISpellService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spell")
public class SpellController {

    @Autowired
    private ISpellService spellService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            SpellDTO spellDTO = spellService.findById(id);

            if (spellDTO != null && spellDTO.getId() != null) {
                return ResponseEntity.ok(spellDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al encontrar el hechizo por el id: " + id);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        try {
            List<SpellDTO> spellDTOList = spellService.findAll();
            return ResponseEntity.ok(spellDTOList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar hechizos: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            spellService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SpellComand spellComand){
        try {

            SpellDTO spellDTO = spellService.save(spellComand);

            return ResponseEntity.ok(spellDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SpellComand spellComand){
        try {
            SpellDTO spellDTO = spellService.update(spellComand);
            return ResponseEntity.ok(spellDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
