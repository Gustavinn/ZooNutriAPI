package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.dto.UserDTO;
import br.com.zoonutri.zoonutriapi.domain.dto.UserWithPasswordDTO;
import br.com.zoonutri.zoonutriapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findUsers());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserWithPasswordDTO> saveUser(@RequestBody final UserWithPasswordDTO userDTO) {
        userService.saveUser(userDTO);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping(consumes = "application/json")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<UserWithPasswordDTO> updateUser(@RequestBody final UserWithPasswordDTO userDTO) {
        userService.updateUser(userDTO);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable final Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
