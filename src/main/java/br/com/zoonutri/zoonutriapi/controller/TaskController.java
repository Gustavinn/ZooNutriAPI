package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.dto.TaskDTO;
import br.com.zoonutri.zoonutriapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<TaskDTO>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @GetMapping(value = "/user/{userId}", produces = "application/json")
    public ResponseEntity<List<TaskDTO>> findUserTasks(@PathVariable final Integer userId) {
        return ResponseEntity.ok(taskService.findUserTasks(userId));
    }

    @GetMapping(value = "/animal/{animalId}", produces = "application/json")
    public ResponseEntity<List<TaskDTO>> findAnimalTasks(@PathVariable final Integer animalId) {
        return ResponseEntity.ok(taskService.findAnimalTasks(animalId));
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody final TaskDTO taskDTO) {
        taskService.saveTask(taskDTO, Boolean.FALSE);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping(consumes = "application/json")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody final TaskDTO taskDTO) {
        taskService.saveTask(taskDTO, Boolean.TRUE);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<TaskDTO> deleteTask(@PathVariable final Integer taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
