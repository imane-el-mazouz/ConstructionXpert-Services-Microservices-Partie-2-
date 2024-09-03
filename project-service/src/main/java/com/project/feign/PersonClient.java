package com.project.feign;


import com.project.dto.PersonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "person-service")
    public interface PersonClient {

    @GetMapping("/api/auth/person")
    PersonDTO getPersonInfo(@RequestHeader("Authorization") String token);
    }


