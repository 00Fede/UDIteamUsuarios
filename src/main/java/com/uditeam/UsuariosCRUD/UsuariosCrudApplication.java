package com.uditeam.UsuariosCRUD;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.uditeam.UsuariosCRUD.dao"})
public class UsuariosCrudApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(UsuariosCrudApplication.class, args);
	}
	
}
