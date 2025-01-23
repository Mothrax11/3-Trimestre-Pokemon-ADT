package com.tarea3adtraullg.proyecto_pokemon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tarea3adtraullg.proyecto_pokemon.complementarias.Menus;

@SpringBootApplication
public class ProyectoPokemonApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoPokemonApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menus.menuPrincipal();
	}

}
