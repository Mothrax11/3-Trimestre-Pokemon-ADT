package com.tarea3adtraullg.proyecto_pokemon.entidades;

public class UsuarioActivo {

    private static UsuarioActivo instancia;
    private long id;
    private String nombre;
    private String nacionalidad;
    private String contrasena;
    private String fechaCreacion;
    private String tipoUsr;
    private Carnet carnet;

    // Constructor privado
    private UsuarioActivo() {
    }

    // Constructor que toma un objeto Entrenador
    public UsuarioActivo(Entrenador entrenador) {
        this.id = entrenador.getId();
        this.nombre = entrenador.getNombre();
        this.nacionalidad = entrenador.getNacionalidad();
        this.contrasena = entrenador.getContrasena();
        this.fechaCreacion = entrenador.getFechaCreacion();
        this.tipoUsr = entrenador.getTipoUsr();
        this.carnet = entrenador.getCarnet();
    }

    public static UsuarioActivo getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioActivo();
        }
        return instancia;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipoUsr() {
        return tipoUsr;
    }

    public void setTipoUsr(String tipoUsr) {
        this.tipoUsr = tipoUsr;
    }

    public Carnet getCarnet() {
        return carnet;
    }

    public void setCarnet(Carnet carnet) {
        this.carnet = carnet;
    }
}
