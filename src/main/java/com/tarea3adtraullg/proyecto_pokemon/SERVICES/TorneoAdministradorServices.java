package com.tarea3adtraullg.proyecto_pokemon.services;

import com.tarea3adtraullg.proyecto_pokemon.entidades.TorneoAdministrador;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoTorneoAdministrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoAdministradorServices {

    @Autowired
    private RepoTorneoAdministrador repoTorneoAdministrador;

    public TorneoAdministradorServices(RepoTorneoAdministrador repoTorneoAdministrador) {
        this.repoTorneoAdministrador = repoTorneoAdministrador;
    }

    // Crear un nuevo TorneoAdministrador
    public void crearTorneoAdministrador(TorneoAdministrador torneoAdministrador) {
        repoTorneoAdministrador.save(torneoAdministrador);
    }

    // Actualizar un TorneoAdministrador
    public void actualizarTorneoAdministrador(long id, TorneoAdministrador torneoAdministrador) {
        repoTorneoAdministrador.save(torneoAdministrador);
    }

    // Eliminar un TorneoAdministrador por su ID
    public void eliminarTorneoAdministrador(long id) {
        repoTorneoAdministrador.deleteById(id);
    }

    // Obtener un TorneoAdministrador por su ID
    public TorneoAdministrador obtenerTorneoAdministradorPorId(long id) {
        return repoTorneoAdministrador.findById(id).orElse(null);
    }

    // Obtener todos los TorneoAdministrador
    public List<TorneoAdministrador> obtenerTodosLosTorneoAdministradores() {
        return repoTorneoAdministrador.findAll();
    }
}
