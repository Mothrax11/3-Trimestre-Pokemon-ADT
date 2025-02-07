package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Torneo;

public interface RepoTorneo extends JpaRepository<Torneo, Long> {
    
    @Query("SELECT DISTINCT ce.combate.torneo FROM CombateEntrenadores ce " +
            "WHERE ce.idEntrenador1 = :idEntrenador OR ce.idEntrenador2 = :idEntrenador")
    List<Torneo> buscarTorneosPorIdEntrenadorParticipante(@Param("idEntrenador") long idEntrenador);
    
}

