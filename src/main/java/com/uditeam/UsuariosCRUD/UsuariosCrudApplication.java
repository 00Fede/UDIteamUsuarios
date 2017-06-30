package com.uditeam.UsuariosCRUD;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Clase principal del sistema, contiene el main para ejecutar todo el proyecto
 * @author Federico Ocampo - cc 1039464102 - feedkiko@gmail.com
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.uditeam.UsuariosCRUD.dao"})
public class UsuariosCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosCrudApplication.class, args);
	}
	
}
