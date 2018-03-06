package com.epam.health_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class HealthCheckerToolApplication {
	public static void main(String[] args) {
			SpringApplication.run(HealthCheckerToolApplication.class, args);
	}
}
