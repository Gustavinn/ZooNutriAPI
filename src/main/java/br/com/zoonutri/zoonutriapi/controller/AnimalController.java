package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.dto.AnimalDTO;
import br.com.zoonutri.zoonutriapi.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<AnimalDTO>> findAnimals() {
        return ResponseEntity.ok(animalService.findAnimals());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AnimalDTO> saveAnimal(@RequestBody final AnimalDTO animalDTO) {
        animalService.saveAnimal(animalDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<AnimalDTO> updateAnimal(@RequestBody final AnimalDTO animalDTO) {
        animalService.saveAnimal(animalDTO);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping("/animalId")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<AnimalDTO> deleteAnimal(@PathVariable final Integer animalId) {
        animalService.deleteAnimal(animalId);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}
