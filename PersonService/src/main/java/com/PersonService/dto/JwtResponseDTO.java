package com.PersonService.dto;

import com.PersonService.model.Person;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    private PersonDTO personDTO ;

    private Person person ;

    public JwtResponseDTO(String error, Object o) {
    }
}
