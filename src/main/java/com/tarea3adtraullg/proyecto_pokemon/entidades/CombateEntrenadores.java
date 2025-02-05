package com.tarea3adtraullg.proyecto_pokemon.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "combate_entrenadores")
public class CombateEntrenadores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCombateEntrenadores;

    @OneToOne
    @JoinColumn(name = "id_combate")
    private Combate combate;
    @Column(name = "id_torneo")
    private long idTorneo;
    @Column(name = "id_entrenador_1")
    private long idEntrenador1;
    @Column(name = "id_entrenador_2")
    private long idEntrenador2;
    @Column(name = "id_ganador")
    private long idGanador;

    public CombateEntrenadores() {
    }
    

    public long getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(long idTorneo) {
        this.idTorneo = idTorneo;
    }

    public long getIdEntrenador1() {
        return idEntrenador1;
    }

    public void setIdEntrenador1(long idEntrenador1) {
        this.idEntrenador1 = idEntrenador1;
    }

    public long getIdEntrenador2() {
        return idEntrenador2;
    }

    public void setIdEntrenador2(long idEntrenador2) {
        this.idEntrenador2 = idEntrenador2;
    }

    public long getIdGanador() {
        return idGanador;
    }

    public void setIdGanador(long idGanador) {
        this.idGanador = idGanador;
    }


    public long getIdCombateEntrenadores() {
        return idCombateEntrenadores;
    }


    public void setIdCombateEntrenadores(long idCombateEntrenadores) {
        this.idCombateEntrenadores = idCombateEntrenadores;
    }


    public Combate getCombate() {
        return combate;
    }


    public void setCombate(Combate combate) {
        this.combate = combate;
    }

    
}
