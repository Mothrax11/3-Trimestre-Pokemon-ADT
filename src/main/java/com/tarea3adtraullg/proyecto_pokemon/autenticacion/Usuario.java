package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

/**
 * Clase que representa a un usuario del sistema de autenticación.
 * Incluye la información básica de un usuario como su nombre, contraseña,
 * nacionalidad, un identificador único y el rol asignado en el sistema.
 * 
 * @author raullg97
 */
public class Usuario {
    private String nombre; // Nombre del usuario
    private String pass; // Contraseña del usuario
    private String nacionalidad; // Nacionalidad del usuario
    private long id; // Identificador único del usuario
    private String rol; // Rol del usuario en el sistema

    /**
     * Constructor para crear un nuevo usuario con todos sus datos básicos.
     *
     * @param nombre       Nombre del usuario.
     * @param pass         Contraseña del usuario.
     * @param nacionalidad Nacionalidad del usuario.
     * @param id           Identificador único del usuario.
     * @param rol          Rol del usuario en el sistema.
     */
    public Usuario(String nombre, String pass, String nacionalidad, long id, String rol) {
        this.id = id;
        this.pass = pass;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.rol = rol;
    }

    /**
     * Método que devuelve el nombre del usuario.
     * 
     * @return Devuelve el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para establecer el nombre del usuario.
     * 
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que devuelve la contraseña del usuario.
     * 
     * @return Devuelve la contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Método que devuelve la nacionalidad del usuario.
     * 
     * @return Devuelve la nacionalidad del usuario.
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Método para establecer la nacionalidad del usuario.
     * 
     * @param nacionalidad La nacionalidad del usuario.
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Método que devuelve el identificador único del usuario.
     * 
     * @return Devuelve el ID del usuario.
     */
    public long getId() {
        return id;
    }

    /**
     * Método para establecer el identificador único del usuario.
     * 
     * @param id El ID único del usuario.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Método que devuelve el rol del usuario.
     * 
     * @return Devuelve el rol asignado al usuario.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Método para establecer el rol del usuario.
     * 
     * @param rol El rol que se le asignará al usuario.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
}
