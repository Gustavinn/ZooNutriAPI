package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.dto.UserDTO;
import br.com.zoonutri.zoonutriapi.service.MailerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class MailerController {

    private final MailerService mailerService;

    @PostMapping("/password")
    public ResponseEntity<Boolean> sendResetPasswdEmail(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(mailerService.sendResetPasswdEmail(userDTO.getEmail()));
    }
}
