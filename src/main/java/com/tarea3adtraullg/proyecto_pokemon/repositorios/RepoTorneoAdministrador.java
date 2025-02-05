package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tarea3adtraullg.proyecto_pokemon.entidades.TorneoAdministrador;

public interface RepoTorneoAdministrador extends JpaRepository<TorneoAdministrador, Long> {
   
    @Query("SELECT t FROM torneo t JOIN t.entrenadores e WHERE e.idEntrenador = :idAdmin")
    List<TorneoAdministrador> findTournamentByIdAdmin(@Param("idAdmin") long idAdmin);

    @Query("SELECT e FROM entrenador e JOIN e.torneos t WHERE t.idTorneo = :idTorneo")
    List<TorneoAdministrador> findAdministradoresByIdTorneo(@Param("idTorneo") long idTorneo);

}
