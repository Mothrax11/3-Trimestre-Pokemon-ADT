package com.tarea3adtraullg.proyecto_pokemon.entidades;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase que representa un carnet para los entrenadores. Almacena la información
 * esencial del carnet, como el ID del entrenador, la fecha de expedición,
 * los puntos obtenidos y el número de victorias. Implementa Serializable para
 * permitir su serialización y almacenamiento.
 * 
 * Existen dos constructores: uno para crear un carnet completo con todos los
 * atributos, y otro que solo requiere el ID del entrenador y la fecha de
 * expedición.
 * 
 * @author raullg97
 */
public class Carnet implements Serializable {
    private int idEntrenador; // ID único del entrenador
    private LocalDate fechaExpedicion; // Fecha en la que se expidió el carnet
    private float puntos; // Puntos acumulados por el entrenador
    private int numVictorias; // Número de victorias del entrenador

    /**
     * Constructor que permite crear un carnet con todos los detalles:
     * ID de entrenador, fecha de expedición, puntos y número de victorias.
     *
     * @param idEntrenador    ID del entrenador al que pertenece el carnet.
     * @param fechaExpedicion Fecha en la que se expide el carnet.
     * @param puntos          Puntos acumulados del entrenador.
     * @param numVictorias    Número de victorias del entrenador.
     */
    public Carnet(int idEntrenador, LocalDate fechaExpedicion, float puntos, int numVictorias) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
        this.puntos = puntos;
        this.numVictorias = numVictorias;
    }

    public Carnet(){
        
    }

    /**
     * Constructor que permite crear un carnet solo con el ID del entrenador
     * y la fecha de expedición. Útil cuando no se necesitan detalles completos.
     *
     * @param idEntrenador    ID del entrenador al que pertenece el carnet.
     * @param fechaExpedicion Fecha en la que se expide el carnet.
     */
    public Carnet(int idEntrenador, LocalDate fechaExpedicion) {
        this.idEntrenador = idEntrenador;
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * Devuelve el ID del entrenador asociado al carnet.
     *
     * @return ID del entrenador.
     */
    public int getIdEntrenador() {
        return idEntrenador;
    }

    /**
     * Establece el ID del entrenador asociado al carnet.
     *
     * @param idEntrenador Nuevo ID del entrenador.
     */
    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    /**
     * Devuelve la fecha de expedición del carnet.
     *
     * @return Fecha de expedición del carnet.
     */
    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    /**
     * Establece la fecha de expedición del carnet.
     *
     * @param fechaExpedicion Nueva fecha de expedición del carnet.
     */
    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * Devuelve los puntos acumulados por el entrenador.
     *
     * @return Puntos del entrenador.
     */
    public float getPuntos() {
        return puntos;
    }

    /**
     * Establece los puntos acumulados del entrenador.
     *
     * @param puntos Nuevos puntos a asignar al entrenador.
     */
    public void setPuntos(float puntos) {
        this.puntos = puntos;
    }

    /**
     * Devuelve el número de victorias del entrenador.
     *
     * @return Número de victorias del entrenador.
     */
    public int getNumVictorias() {
        return numVictorias;
    }

    /**
     * Establece el número de victorias del entrenador.
     *
     * @param numVictorias Nuevo número de victorias.
     */
    public void setNumVictorias(int numVictorias) {
        this.numVictorias = numVictorias;
    }
}
