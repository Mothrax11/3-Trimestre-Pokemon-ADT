package com.tarea3adtraullg.proyecto_pokemon.SERVICES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea3adtraullg.proyecto_pokemon.entidades.CombateEntrenadores;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoCombateEntrenadores;

@Service
public class CombateEntrenadoresServices {

    @Autowired
    private RepoCombateEntrenadores repoCombateEntrenadores;

    public CombateEntrenadoresServices(RepoCombateEntrenadores repoCombateEntrenadores) {
       this.repoCombateEntrenadores = repoCombateEntrenadores;
    }
    public void crearCombateEntrenadores(CombateEntrenadores combateEntenadores) {
        repoCombateEntrenadores.save(combateEntenadores);
    }

    public void actualizarCombateEntrenadores(CombateEntrenadores combateEntrenadores) {
        repoCombateEntrenadores.save(combateEntrenadores);
    }

    public void eliminarCombateEntrenadoresPorId(long idCombateEntrenadores) {
        repoCombateEntrenadores.deleteById(idCombateEntrenadores);
    }

    public CombateEntrenadores obtenerCombateEntrenadoresPorId(long idCombateEntrenadores) {
        return repoCombateEntrenadores.findById(idCombateEntrenadores).orElse(null);
    }

    public List<CombateEntrenadores> obtenerTodosLosCombateEntrenadores() {
        return repoCombateEntrenadores.findAll();
    }
}
