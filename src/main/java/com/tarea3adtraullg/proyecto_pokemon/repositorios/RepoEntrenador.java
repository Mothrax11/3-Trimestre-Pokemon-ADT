package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;

@Repository
public interface RepoEntrenador extends JpaRepository<Entrenador,Long> {
    @Query("SELECT e FROM entrenador e WHERE e.nombre = ?1 AND e.password = ?2")
    Entrenador findByNombreAndPassword(String nombre, String password);
    
    @Query("SELECT e FROM entrenador e WHERE e.nombre = ?1 AND e.nacionalidad = ?2")
    Entrenador findByNombreAndReg(String nombre, String reg);

}
