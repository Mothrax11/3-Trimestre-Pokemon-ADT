package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;

@Repository
public interface RepoEntrenador extends JpaRepository<Entrenador,Long> {
    @Query
    Entrenador findByNombreAndContrasena(String nombre, String contrasena);
}
