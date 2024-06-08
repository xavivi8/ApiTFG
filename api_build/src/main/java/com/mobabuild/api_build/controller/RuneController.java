package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.comand.RuneComand;
import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.controller.dto.SpellDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.service.IRuneService;
import com.mobabuild.api_build.utils.BlobUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rune")
public class RuneController {

    @Autowired
    private IRuneService runeService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            RuneDTO runeDTO = runeService.findById(id);
            if (runeDTO != null && runeDTO.getId() != null) {
                return ResponseEntity.ok(runeDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al encontrar la runa por el id: " + id);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<RuneDTO> runeDTOList = runeService.findAll();
            if(!runeDTOList.isEmpty()){
                return ResponseEntity.ok(runeDTOList);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar hechizos: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            runeService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> insertRune(@RequestBody RuneComand runeComand) {
        try {

            RuneDTO runeDTO = runeService.save(runeComand);

            return ResponseEntity.ok(runeDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar el campeon: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RuneComand runeComand){
        try {
            RuneDTO runeDTO = runeService.update(runeComand);
            return ResponseEntity.ok(runeDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
