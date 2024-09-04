package com.PersonService.dto;


import com.PersonService.enums.Role;
import com.PersonService.model.Person;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 225)
    private Role role;

    public PersonDTO(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;

    }



    public PersonDTO(Long id, String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public PersonDTO(Person person) {
        this.name = person.getName();
        this.email = person.getEmail();
        this.password= person.getPassword();
        this.role = person.getRole();
    }


    public String getRoles() {
        return this.getRoles();
    }
}
