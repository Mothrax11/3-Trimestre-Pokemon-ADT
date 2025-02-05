package com.tarea3adtraullg.proyecto_pokemon.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Entity(name = "entrenador")
@Table(name = "entrenador")
public class Entrenador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntrenador; // ID único del entrenador

    @ManyToMany
    @JoinTable(
        name = "torneo_administrador", // Nombre de la tabla de unión
        joinColumns = @JoinColumn(name = "id_admin_torneo"), // Columna que referencia a Entrenador
        inverseJoinColumns = @JoinColumn(name = "id_torneo") // Columna que referencia a Torneo
    )
    private List<Torneo> torneos = new ArrayList<>();

    @Column(name = "nombre")
    private String nombre; // Nombre del entrenador

    @Column(name = "nacionalidad")
    private String nacionalidad; // Nacionalidad del entrenador

    @Column(name = "password")
    private String password; // Contraseña del entrenador

    @Column(name = "fechaCreacion")
    private String fechaCreacion; // Fecha de creación del carnet
    
    @Column(name = "tipo_usuario")
    private String tipoUsr;

    @Transient
    private List<Combate> combatesEntrenador;
    @Transient
    private Carnet carnet; // Carnet del entrenador

    @OneToMany(mappedBy = "entrenador")
    private List<TorneoAdministrador> torneoAdministradores;
    
    
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

        public Entrenador(String nombre, String pass, String nacionalidad) {
            this.nombre = nombre;
            this.nacionalidad = nacionalidad;
            this.password = pass;
            this.fechaCreacion = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
    
        public Entrenador(String nombre, String pass, String nacionalidad, String tipoUsr) {
            this.nombre = nombre;
            this.password = pass;
            this.nacionalidad = nacionalidad;
            this.tipoUsr = tipoUsr;
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
   

    /**
     * Devuelve el ID único del entrenador.
     *
     * @return ID del entrenador.
     */
    public long getId() {
        return idEntrenador;
    }

    /**
     * Establece el ID del entrenador.
     *
     * @param id Nuevo ID del entrenador.
     */
    public void setId(long id) {
        this.idEntrenador = id;
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
        return password;
    }

    /**
     * Establece la contraseña del entrenador.
     *
     * @param contrasena Nueva contraseña del entrenador.
     */
    public void setContrasena(String contrasena) {
        this.password = contrasena;
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

    public String getTipoUsr() {
        return tipoUsr;
    }

    public void setTipoUsr(String tipoUsr) {
        this.tipoUsr = tipoUsr;
    }

    public List<Combate> getCombatesEntrenador() {
        return combatesEntrenador;
    }

    public void setCombatesEntrenador(List<Combate> combatesEntrenador) {
        this.combatesEntrenador = combatesEntrenador;
    }

    public List<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(List<Torneo> torneos) {
        this.torneos = torneos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    

    
}
