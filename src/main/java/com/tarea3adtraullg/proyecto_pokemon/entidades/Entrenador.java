package com.tarea3adtraullg.proyecto_pokemon.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Clase que representa a un entrenador. Contiene información relevante
 * del entrenador, como su nombre, nacionalidad, puntos acumulados y el carnet
 * asociado.
 * También incluye un historial de torneos en los que el entrenador ha
 * participado.
 * Implementa Serializable para permitir la persistencia de los datos del
 * entrenador.
 * 
 * @author raullg97
 */
@Entity
@Table(name = "entrenador")
public class Entrenador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // ID único del entrenador

    @Column(name = "nombre")
    private String nombre; // Nombre del entrenador

    @Column(name = "nacionalidad")
    private String nacionalidad; // Nacionalidad del entrenador

    @Transient
    private Carnet carnet; // Carnet del entrenador
    @Transient
    private String contrasena; // Contraseña del entrenador
    @Transient
    private String fechaCreacion; // Fecha de creación del carnet

    /**
     * Constructor sin parámetros.
     */
    public Entrenador() {
    }

    /**
     * Constructor que inicializa los datos básicos del entrenador: nombre y
     * nacionalidad. Al crear un nuevo entrenador, también se genera un carnet
     * con la fecha de creación actual.
     *
     * @param nombre       Nombre del entrenador.
     * @param nacionalidad Nacionalidad del entrenador.
     */
    public Entrenador(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaCreacion = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Constructor que inicializa los datos básicos del entrenador: nombre,
     * contraseña y nacionalidad.
     *
     * @param nombre       Nombre del entrenador.
     * @param contrasena   Contraseña del entrenador.
     * @param nacionalidad Nacionalidad del entrenador.
     */
    public Entrenador(String nombre, String contrasena, String nacionalidad) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.nacionalidad = nacionalidad;
        this.fechaCreacion = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Devuelve el ID único del entrenador.
     *
     * @return ID del entrenador.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el ID del entrenador.
     *
     * @param id Nuevo ID del entrenador.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del entrenador.
     *
     * @return Nombre del entrenador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del entrenador.
     *
     * @param nombre Nuevo nombre del entrenador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la nacionalidad del entrenador.
     *
     * @return Nacionalidad del entrenador.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Establece la nacionalidad del entrenador.
     *
     * @param nacionalidad Nueva nacionalidad del entrenador.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Devuelve el carnet del entrenador.
     *
     * @return Carnet del entrenador.
     */
    public Carnet getCarnet() {
        return carnet;
    }

    /**
     * Establece el carnet del entrenador.
     *
     * @param carnet Nuevo carnet del entrenador.
     */
    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }

    /**
     * Devuelve la contraseña del entrenador.
     *
     * @return Contraseña del entrenador.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del entrenador.
     *
     * @param contrasena Nueva contraseña del entrenador.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Devuelve la fecha de creación del carnet.
     *
     * @return Fecha de creación del carnet.
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del carnet.
     *
     * @param fechaCreacion Nueva fecha de creación del carnet.
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
