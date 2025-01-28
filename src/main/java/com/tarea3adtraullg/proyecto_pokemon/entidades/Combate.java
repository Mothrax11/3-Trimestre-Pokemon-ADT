package com.tarea3adtraullg.proyecto_pokemon.entidades;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Combate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que JPA genere el ID automáticamente
    private Long id;

    private LocalDate fecha;
    private int idTorneo;

    // Constructor con id y fecha
    public Combate(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    // Constructor sin parámetros (por JPA)
    public Combate() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }
}
