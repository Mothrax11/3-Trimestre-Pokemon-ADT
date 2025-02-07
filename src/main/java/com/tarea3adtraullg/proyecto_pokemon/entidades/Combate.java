package com.tarea3adtraullg.proyecto_pokemon.entidades;


import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "combate")
public class Combate implements Serializable { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que JPA genere el ID automáticamente
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_torneo", nullable = false)
    private Torneo torneo;

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

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    
}
