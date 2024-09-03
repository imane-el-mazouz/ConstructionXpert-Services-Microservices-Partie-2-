package com.PersonService.service;

import com.PersonService.dto.AuthRequestDTO;
import com.PersonService.dto.JwtResponseDTO;
import com.PersonService.dto.PersonDTO;
import com.PersonService.model.Person;
import com.PersonService.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class PersonAuthService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    private JwtService jwtService;

    @Autowired
    private PersonService personService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(person.getEmail())
                .password(person.getPassword())
                .roles(person.getRole().name())
                .build();
    }

    public JwtResponseDTO signUp(Person personRequest) {
        if (personRepository.findByEmail(personRequest.getEmail()) != null) {
            throw new RuntimeException("Email is already taken.");
        }
        personRequest.setPassword(passwordEncoder.encode(personRequest.getPassword()));
        Person savedPerson = personRepository.save(personRequest);

        String token = jwtService.generateToken(savedPerson.getEmail(), savedPerson.getRole());

        return JwtResponseDTO.builder()
                .accessToken(token)
                .person(savedPerson)
                .build();
    }

    public JwtResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            Person person = personRepository.findByEmail(authRequestDTO.getEmail());
            String token = jwtService.generateToken(person.getEmail(), person.getRole());
            return JwtResponseDTO.builder()
                    .accessToken(token)
                    .person(person)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }
    @GetMapping("/person")
    public ResponseEntity<PersonDTO> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            PersonDTO person = personService.getPersonInfo(userDetails.getUsername());
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
