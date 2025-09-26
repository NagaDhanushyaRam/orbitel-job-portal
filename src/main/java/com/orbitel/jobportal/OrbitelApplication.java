package com.orbitel.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrbitelApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrbitelApplication.class, args);
		System.out.println("Active profile: " + System.getProperty("spring.profiles.active"));
	}

}
