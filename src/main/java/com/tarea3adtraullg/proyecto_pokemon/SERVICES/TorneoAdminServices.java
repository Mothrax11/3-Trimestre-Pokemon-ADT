package com.proyectopokemonadt.SERVICES;


import com.proyectopokemonadt.DAO.TorneoAdminDAOImplementacion;
import com.proyectopokemonadt.DTO.TorneoAdminDTO;
import com.proyectopokemonadt.ENTIDADES.TorneoAdmin;
import com.proyectopokemonadt.complementarias.DBConnection;

import javax.sql.DataSource;
import java.util.List;

public class TorneoAdminServices {

    private static TorneoAdminServices instancia;
    private DataSource dataSource;
    private TorneoAdminDAOImplementacion torneoAdminDAOImplementacion = TorneoAdminDAOImplementacion.getInstancia(dataSource);

    private TorneoAdminServices(DataSource dataSource) {
        this.dataSource = DBConnection.getMySQLDataSource();
    }

    public static TorneoAdminServices getInstancia(DataSource dataSource) {
        if (instancia == null) {
            instancia = new TorneoAdminServices(dataSource);
        }
        return instancia;
    }

    public boolean crearTorneoAdmin(TorneoAdminDTO torneoAdmin) {
        return torneoAdminDAOImplementacion.crearTorneoAdmin(torneoAdmin);
    }

    public boolean eliminarTorneoAdminPorIdTorneo(int idTorneo) {
        return torneoAdminDAOImplementacion.eliminarTorneoAdminPorIdTorneo(idTorneo);
    }

    public List<TorneoAdmin> obtenerTodosLosTorneosAdmin() {
        return torneoAdminDAOImplementacion.obtenerTodosLosTorneosAdmin();
    }

    public int obtenerIdEntrenadorPorIdTorneo(int idTorneo) {
        return torneoAdminDAOImplementacion.obtenerIdEntrenadorPorIdTorneo(idTorneo);
    }

    public List<Integer> obtenerTorneosPorIdAdministrador(int idTorneoAdmin) {
        return torneoAdminDAOImplementacion.obtenerTorneosPorIdAdministrador(idTorneoAdmin);
    }
}
