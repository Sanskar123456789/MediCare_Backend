package com.example.demo.Security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Cors {
	@Value("${allowed.origin}")
	private String allowedOrigin;
	Logger log = Logger.getAnonymousLogger();
	
	@Bean
	public WebMvcConfigurer getCorsConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				log.info(allowedOrigin);
				registry.addMapping("/**")
				.allowedOrigins(allowedOrigin)
				.allowedMethods("*")
				.allowedHeaders("*");
			}
			
		};
	}

}
