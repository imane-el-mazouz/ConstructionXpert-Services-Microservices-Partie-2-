package com.PersonService.feign;

import com.PersonService.dto.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PersonService")
public interface PersonClient {

    @GetMapping("/api/auth/person/{email}")
    PersonDTO getPersonByEmail(@PathVariable("email") String email);

    @GetMapping("/api/auth/person")
    PersonDTO getPersonInfo(@RequestHeader("Authorization") String token);
}

