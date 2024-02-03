package com.managersystem.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableScheduling
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}
