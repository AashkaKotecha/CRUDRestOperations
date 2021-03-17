package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudRestOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudRestOperationsApplication.class, args);
	}

}
