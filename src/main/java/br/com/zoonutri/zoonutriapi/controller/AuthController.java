package br.com.zoonutri.zoonutriapi.controller;

import br.com.zoonutri.zoonutriapi.domain.MyUserDetails;
import br.com.zoonutri.zoonutriapi.domain.dto.AuthenticationRequestDTO;
import br.com.zoonutri.zoonutriapi.domain.dto.AuthenticationResponseDTO;
import br.com.zoonutri.zoonutriapi.domain.dto.UserChangeDTO;
import br.com.zoonutri.zoonutriapi.security.MyUserDetailsService;
import br.com.zoonutri.zoonutriapi.service.UserService;
import br.com.zoonutri.zoonutriapi.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.getMessage;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(produces = "application/json")
    public ResponseEntity<AuthenticationResponseDTO> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(getMessage("msg.error.authentication.01"));
        }

        final MyUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }

    @PostMapping("/change/pwd")
    public ResponseEntity<Boolean> verifyHashAndUser(@RequestBody UserChangeDTO userChangeDTO) throws Exception {
        return ResponseEntity.ok(userService.changePassword(userChangeDTO));
    }

}
