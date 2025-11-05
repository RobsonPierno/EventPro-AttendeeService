package com.eventpro.AttendeeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AttendeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendeeServiceApplication.class, args);
	}
}
