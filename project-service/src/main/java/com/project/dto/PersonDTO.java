package com.project.dto;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private Long id;
    private String name;
    private String email;
    private String role;
}
