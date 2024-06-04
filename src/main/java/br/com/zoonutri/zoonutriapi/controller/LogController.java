package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.dto.LogDTO;
import br.com.zoonutri.zoonutriapi.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping(produces = "application/json")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<List<LogDTO>> findUserTasks() {
        return ResponseEntity.ok(logService.findAllLogs());
    }

    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<List<LogDTO>> findUserTasks(@PathVariable final Integer userId) {
        return ResponseEntity.ok(logService.findLogsByUser(userId));
    }
}
