package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@SpringBootApplication
public class MediCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediCareApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder pass() {
		return NoOpPasswordEncoder.getInstance();
	}

}
