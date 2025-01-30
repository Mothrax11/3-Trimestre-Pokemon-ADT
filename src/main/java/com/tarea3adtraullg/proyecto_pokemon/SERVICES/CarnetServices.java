package com.tarea3adtraullg.proyecto_pokemon.SERVICES;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarea3adtraullg.proyecto_pokemon.entidades.Carnet;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoCarnet;


@Service
public class CarnetServices {
@Autowired

    private final RepoCarnet repoCarnet;
    private static CarnetServices instancia;

    public static CarnetServices getInstancia(RepoCarnet repoCarnet) {
        if (instancia == null) {
            instancia = new CarnetServices(repoCarnet);
        }
        return instancia;
    }

    public CarnetServices(RepoCarnet repoCarnet) {
       this.repoCarnet = repoCarnet;
    }

    public void crearCarnet(Carnet carnet) {
        repoCarnet.save(carnet);
    }

    public void actualizarCarnet(Carnet carnet) {
        repoCarnet.save(carnet);
    }

    public void eliminarCarnetPorId(int idCarnet) {
        repoCarnet.deleteById(idCarnet);
    }

    public Carnet obtenerCarnetPorId(int idCarnet) {
        return repoCarnet.findById(idCarnet).orElse(null);
    }

    public List<Carnet> obtenerTodosLosCarnets() {
        return repoCarnet.findAll();
    }
}
