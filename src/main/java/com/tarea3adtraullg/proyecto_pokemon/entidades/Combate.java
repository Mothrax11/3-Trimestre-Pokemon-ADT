package com.tarea3adtraullg.proyecto_pokemon.entidades;

import java.time.LocalDate;

public class Combate {
    private LocalDate fecha;
    private int id;
    private int idTorneo;
    
    public Combate(int id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    

}
