package com.tarea3adtraullg.proyecto_pokemon.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tarea3adtraullg.proyecto_pokemon.entidades.CombateEntrenadores;


@Repository
public interface RepoCombateEntrenadores extends JpaRepository<CombateEntrenadores,Long> {

}
