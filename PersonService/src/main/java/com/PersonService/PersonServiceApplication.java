package com.PersonService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication(scanBasePackages = "com.PersonService")
@SpringBootApplication()
@EnableFeignClients

public class PersonServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}

}
