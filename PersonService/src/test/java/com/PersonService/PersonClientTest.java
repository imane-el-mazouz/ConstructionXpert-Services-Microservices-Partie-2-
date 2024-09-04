package com.PersonService;

import com.PersonService.dto.PersonDTO;
import com.PersonService.feign.PersonClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonClientTest {

    @Autowired
    private PersonClient personClient;

    @Test
    public void testGetPersonInfo() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpbWFuZUBnbWFpbC5jb20iLCJpYXQiOjE3MjU0NDg2NzksInJvbGUiOiJVc2VyIiwiZXhwIjoxNzI3MTc2Njc5fQ.gK6UQ1Or8iLLUAb349plaCrIPnp5_M09tP6fXcFRtnc";
        PersonDTO person = personClient.getPersonInfo(token);
        assertNotNull(person);
        System.out.println(person);
    }
}
