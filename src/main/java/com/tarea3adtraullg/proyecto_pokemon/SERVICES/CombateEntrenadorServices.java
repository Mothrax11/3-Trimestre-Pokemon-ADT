package com.proyectopokemonadt.SERVICES;

import java.util.List;
import com.proyectopokemonadt.DAO.CombateEntrenadorDAOImplementacion;
import com.proyectopokemonadt.ENTIDADES.Combate;
import com.proyectopokemonadt.ENTIDADES.Entrenador;


public class CombateEntrenadorServices {

    private static CombateEntrenadorServices instancia;
    
    private CombateEntrenadorDAOImplementacion combateEntrenadorDAOImplementacion;

    private CombateEntrenadorServices() {
        this.combateEntrenadorDAOImplementacion = CombateEntrenadorDAOImplementacion.getInstancia();
    }

    public static CombateEntrenadorServices getInstancia() {
        if (instancia == null) {
            instancia = new CombateEntrenadorServices();
        }
        return instancia;
    }

    public List<Combate> obtenerTodosCombatesPorIdEntrenador(int idEntrenador) {
        return combateEntrenadorDAOImplementacion.obtenerTodosCombatesPorIdEntrenador(idEntrenador);
    }

    public List<Entrenador> obtenerEntrenadoresPorIdCombate(int idCombate) {
        
        return combateEntrenadorDAOImplementacion.obtenerEntrenadoresPorIdCombate(idCombate);
    }
    } 
