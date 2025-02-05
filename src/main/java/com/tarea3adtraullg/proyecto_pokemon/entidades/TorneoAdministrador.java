package com.tarea3adtraullg.proyecto_pokemon.entidades;

import jakarta.persistence.*;

@Entity(name ="torneo_administrador")
@Table(name = "torneo_administrador")
public class TorneoAdministrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTorneoAdministrador; // ID único del entrenador

    @ManyToOne
    @JoinColumn(name = "id_admin_torneo", referencedColumnName = "idEntrenador")
    private Entrenador entrenador; // Relación con Entrenador

    @ManyToOne
    @JoinColumn(name = "id_torneo", referencedColumnName = "idTorneo")
    private Torneo torneo; // Relación con Torneo


    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    
}
