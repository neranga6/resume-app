package com.webapp.neo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.webapp.neo.configurations","com.webapp.neo.controller","com.webapp.neo.exceptions", "com.webapp.neo.services"})
@EntityScan(basePackages = "com.webapp.neo.model")
@EnableJpaRepositories(basePackages = {"com.webapp.neo.repositories"})
@SpringBootApplication(scanBasePackages= {"com.webapp.neo.repositories"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
