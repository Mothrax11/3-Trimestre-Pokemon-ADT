package com.tarea3adtraullg.proyecto_pokemon.entidades;


import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "carnet")
@Table(name = "carnet")
public class Carnet implements Serializable {

    @Id
    private Long idEntrenador; 
    @Column(name = "fecha_expedicion")
    private LocalDate fechaExpedicion;
    @Column(name = "puntos")
    private float puntos;
    @Column(name = "num_victorias")
    private int numVictorias;

    
    public Carnet() {
        
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
