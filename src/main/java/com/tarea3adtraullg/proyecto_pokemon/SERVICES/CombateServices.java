package com.tarea3adtraullg.proyecto_pokemon.SERVICES;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Combate;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoCombate;

@Service
public class CombateServices {

    @Autowired
    private final RepoCombate repoCombate;
    private CombateServices instancia;

    private CombateServices(RepoCombate repoCombate){
       this.repoCombate = repoCombate;
    }

    public void crearCombate(Combate combate) {
        repoCombate.save(combate);
    }

    public void eliminarCombatePorId(long id) {
        repoCombate.deleteById(id);
    }

    public void actualizarCombate( Combate combate) {
        repoCombate.save(combate);
    }

    public Combate obtenerCombatePorId(long id) {
        return repoCombate.findById(id).orElse(null);
    }

    public List<Combate> obtenerTodosLosCombates() {
        return repoCombate.findAll();
    }

    
}
