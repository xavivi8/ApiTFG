package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.controller.dto.ObjectDTO;
import com.mobabuild.api_build.controller.dto.RuneDTO;
import com.mobabuild.api_build.entities.Object;
import com.mobabuild.api_build.entities.Rune;
import com.mobabuild.api_build.service.IRuneService;
import org.springframework.beans.factory.annotation.Autowired;
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
        Optional<Rune> runeOptional = runeService.findById(id);

        if (runeOptional.isPresent()) {
            Rune rune = runeOptional.get();

            RuneDTO runeDTO = RuneDTO.builder()
                    .id(rune.getId())
                    .name(rune.getName())
                    .group_name(rune.getGroup_name())
                    .description(rune.getDescription())
                    .long_description(rune.getLong_description())
                    .image(rune.getImage())
                    .build();

            return ResponseEntity.ok(runeDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Rune> runeList = runeService.findAll();

        if (!runeList.isEmpty()) {
            List<RuneDTO> runeDTOList = new ArrayList<>();
            for (Rune rune : runeList) {
                RuneDTO runeDTO = RuneDTO.builder()
                        .id(rune.getId())
                        .name(rune.getName())
                        .group_name(rune.getGroup_name())
                        .description(rune.getDescription())
                        .long_description(rune.getLong_description())
                        .image(rune.getImage())
                        .build();
                runeDTOList.add(runeDTO);
            }
            return ResponseEntity.ok(runeDTOList);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        runeService.deleteById(id);

        return ResponseEntity.ok("ok");
    }

    @PostMapping("/insertRune")
    @ResponseBody
    public ResponseEntity<?> insertRune(@RequestBody RuneDTO runeDTO) {

        if (runeDTO.getImage() == null || runeDTO.getImage().length == 0) {
            // El campo de imagen está vacío o nulo
            // Realiza la inserción sin la imagen
            int result = runeService.insertRuneWithoutImage(runeDTO.getName(), runeDTO.getRowType(), runeDTO.getGroup_name(), runeDTO.getDescription(), runeDTO.getLong_description());

            return ResponseEntity.ok(result);

        } else {

            int result = runeService.insertRune(runeDTO.getName(), runeDTO.getRowType(), runeDTO.getGroup_name(), runeDTO.getDescription(), runeDTO.getLong_description(), runeDTO.getImage());

            return ResponseEntity.ok(result);
        }
    }
}
