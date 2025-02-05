package com.tarea3adtraullg.proyecto_pokemon.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Clase que representa un torneo en el sistema. Un torneo tiene un nombre,
 * una región asociada, un responsable, y una lista de participantes.
 * También cuenta con puntos que se otorgan por cada victoria en el torneo.
 * 
 * @author raullg97
 */
@Entity(name = "torneo")
@Table(name = "torneo")
public class Torneo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para que JPA genere el ID automáticamente
    private long idTorneo; // ID único del torneo
    @Column(name = "nombre")
    private String nombre; // Nombre del torneo
    @Column(name = "cod_region")
    private char codRegion; // Código de la región a la que pertenece el torneo
    @Column(name = "puntos_victoria")
    private float puntosVictoria; // Puntos asignados por cada victoria

    @ManyToMany(mappedBy = "torneos")
    private List<Entrenador> entrenadores = new ArrayList<>();

    /**
     * Constructor que inicializa el nombre, el código de región y los puntos de
     * victoria para el torneo.
     *
     * @param nombre         Nombre del torneo.
     * @param codRegion      Código de la región del torneo.
     * @param puntosVictoria Puntos otorgados por cada victoria en el torneo.
     */
    public Torneo(String nombre, char codRegion, float puntosVictoria) {
        this.nombre = nombre;
        this.codRegion = codRegion;
        this.puntosVictoria = puntosVictoria;
    }

    /**
     * Constructor vacío (por JPA).
     */
    public Torneo() {
    }

    /**
     * Devuelve el ID del torneo.
     *
     * @return ID del torneo.
     */
    public long getId() {
        return idTorneo;
    }

    /**
     * Establece el ID del torneo.
     *
     * @param id ID único del torneo.
     */
    public void setId(long id) {
        this.idTorneo = id;
    }

    /**
     * Devuelve el nombre del torneo.
     *
     * @return Nombre del torneo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del torneo.
     *
     * @param nombre Nombre del torneo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el código de la región del torneo.
     *
     * @return Código de la región.
     */
    public char getCodRegion() {
        return codRegion;
    }

    /**
     * Establece el código de la región a la que pertenece el torneo.
     *
     * @param codRegion Código de la región.
     */
    public void setCodRegion(char codRegion) {
        this.codRegion = codRegion;
    }

    /**
     * Devuelve los puntos otorgados por cada victoria en el torneo.
     *
     * @return Puntos por victoria.
     */
    public float getPuntosVictoria() {
        return puntosVictoria;
    }

    /**
     * Establece los puntos que se otorgan por cada victoria en el torneo.
     *
     * @param puntosVictoria Puntos otorgados por victoria.
     */
    public void setPuntosVictoria(float puntosVictoria) {
        this.puntosVictoria = puntosVictoria;
    }
}
