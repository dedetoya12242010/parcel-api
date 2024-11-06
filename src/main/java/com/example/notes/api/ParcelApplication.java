package com.example.notes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@ComponentScan({"com.example.notes.api.controller","com.example.notes.api.service"})
@EntityScan("com.example.notes.api.entity")
@EnableJpaRepositories("com.example.notes.api.repository")
@SpringBootApplication
public class ParcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelApplication.class, args);
	}

}
