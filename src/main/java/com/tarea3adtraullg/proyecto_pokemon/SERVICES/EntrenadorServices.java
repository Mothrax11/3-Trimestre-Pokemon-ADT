package com.tarea3adtraullg.proyecto_pokemon.SERVICES;
import java.lang.classfile.instruction.ReturnInstruction;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;

@Service
public class EntrenadorServices {
    
    @Autowired
    private final RepoEntrenador repoEntrenador; 
    @Autowired
    private static EntrenadorServices instancia;

    public static EntrenadorServices getInstancia(RepoEntrenador repoEntrenador) {
        if (instancia == null) {
            instancia = new EntrenadorServices(repoEntrenador);
        }
        return instancia;
    }

    public EntrenadorServices (RepoEntrenador repoEntrenador){
        this.repoEntrenador = repoEntrenador;
    }

    public void crearEntrenador(Entrenador entrenador) {
        repoEntrenador.save(entrenador);
    }

    public void actualizarEntrenador(long id, Entrenador entrenador) {
        repoEntrenador.save(entrenador);
    }

    public void eliminarEntrenador(long id) {
        repoEntrenador.deleteById(id);
    }

    public Entrenador obtenerEntrenadorPorId(long id) {
        return repoEntrenador.findById(id).orElse(null);
    }

    public List<Entrenador> obtenerTodosLosEntrenadores() {
        return repoEntrenador.findAll();
    }

    public Entrenador buscarPorNombreYContrasena(String nombre, String contrasena) {
         return repoEntrenador.findByNombreAndPassword(nombre, contrasena);
    }
    
}
