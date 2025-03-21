package com.tarea3adtraullg.proyecto_pokemon.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea3adtraullg.proyecto_pokemon.entidades.Torneo;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoTorneo;

@Service
public class TorneoServices {
    
    @Autowired
    private final RepoTorneo repoTorneo;

    public TorneoServices(RepoTorneo repoTorneo) {
        this.repoTorneo = repoTorneo;
    }

    public void crearTorneo(Torneo torneo) {
        repoTorneo.save(torneo);
    }

    public void actualizarTorneo(long id, Torneo torneo) {
        repoTorneo.save(torneo);
    }

    public void eliminarTorneo(long id) {
        repoTorneo.deleteById(id);
    }

    public Torneo obtenerTorneoPorId(long id) {
        return repoTorneo.findById(id).orElse(null);
    }

    public List<Torneo> obtenerTodosLosTorneos() {
        return repoTorneo.findAll();
    }

    public List<Torneo> buscarTorneosPorIdEntrenadorParticipante(long idEntrenador){
        return repoTorneo.buscarTorneosPorIdEntrenadorParticipante(idEntrenador);
    }
 




}
