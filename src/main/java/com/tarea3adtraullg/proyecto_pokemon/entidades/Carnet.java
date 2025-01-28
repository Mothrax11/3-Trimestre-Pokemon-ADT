package com.tarea3adtraullg.proyecto_pokemon.entidades;


import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carnet implements Serializable {

    @Id
    private Long idEntrenador; 

    private LocalDate fechaExpedicion;
    private float puntos;
    private int numVictorias;

    
    public Carnet() {
    }

    
    public Carnet(Long idEntrenador, LocalDate fechaExpedicion, float puntos, int numVictorias) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
        this.puntos = puntos;
        this.numVictorias = numVictorias;
    }

    
    public Carnet(Long idEntrenador, LocalDate fechaExpedicion) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
    }

  
    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public float getPuntos() {
        return puntos;
    }

    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    public int getNumVictorias() {
        return numVictorias;
    }

    public void setNumVictorias(int numVictorias) {
        this.numVictorias = numVictorias;
    }
}
