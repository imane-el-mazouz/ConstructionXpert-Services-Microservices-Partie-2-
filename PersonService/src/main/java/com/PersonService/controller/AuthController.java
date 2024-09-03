package com.PersonService.controller;


import com.PersonService.dto.AuthRequestDTO;
import com.PersonService.dto.JwtResponseDTO;
import com.PersonService.dto.PersonDTO;
import com.PersonService.model.Person;
import com.PersonService.service.PersonAuthService;
import com.PersonService.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")

public class AuthController {
    @Autowired
    private PersonAuthService personAuthService;

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            JwtResponseDTO jwtResponseDTO = personAuthService.login(authRequestDTO);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<JwtResponseDTO> registerUser(@RequestBody Person person) {
        try {
            JwtResponseDTO response = personAuthService.signUp(person);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JwtResponseDTO("Error", null));
        }
    }


}
