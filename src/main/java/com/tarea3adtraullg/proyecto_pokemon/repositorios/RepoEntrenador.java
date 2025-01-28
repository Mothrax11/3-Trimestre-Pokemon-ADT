package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;

public interface RepoEntrenador extends JpaRepository<Entrenador,Long> {
    
}
