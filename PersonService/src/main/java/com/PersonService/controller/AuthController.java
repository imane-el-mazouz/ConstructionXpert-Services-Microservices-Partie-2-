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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/person")
    public ResponseEntity<PersonDTO> getPersonInfo(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            PersonDTO person = personAuthService.getPersonInfo(userDetails.getUsername());
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/person/{email}")
    public ResponseEntity<PersonDTO> getPersonByEmail(@PathVariable("email") String email) {
        try {
            PersonDTO person = personAuthService.getPersonByEmail(email);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
