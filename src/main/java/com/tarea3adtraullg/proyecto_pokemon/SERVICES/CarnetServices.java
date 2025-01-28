package com.proyectopokemonadt.SERVICES;

import com.proyectopokemonadt.DAO.CarnetDAO;
import com.proyectopokemonadt.DTO.CarnetDTO;
import com.proyectopokemonadt.ENTIDADES.Carnet;

import java.util.ArrayList;
import java.util.List;

public class CarnetServices {

    private CarnetDAO carnetDAO;
    private static CarnetServices instancia;

    public static CarnetServices getInstancia() {
        if (instancia == null) {
            instancia = new CarnetServices();
        }
        return instancia;
    }

    public CarnetServices() {
       
    }

    public CarnetServices(CarnetDAO carnetDAO) {
        this.carnetDAO = carnetDAO;
    }

    public boolean crearCarnet(CarnetDTO carnetDTO) {
        Carnet carnet = mapearDTOAEntidad(carnetDTO);
        return carnetDAO.crearCarnet(carnet);
    }

    public boolean eliminarCarnetPorId(int idEntrenador) {
        return carnetDAO.eliminarCarnetPorIdEntrenador(idEntrenador);
    }

    public CarnetDTO obtenerCarnetPorId(int idEntrenador) {
        Carnet carnet = carnetDAO.obtenerCarnetPorIdEntrenador(idEntrenador);
        return mapearEntidadADTO(carnet);
    }

    public List<CarnetDTO> obtenerTodosLosCarnets() {
        List<Carnet> carnets = carnetDAO.obtenerTodosLosCarnets();
        List<CarnetDTO> carnetDTOs = new ArrayList<>();
        for (Carnet carnet : carnets) {
            carnetDTOs.add(mapearEntidadADTO(carnet));
        }
        return carnetDTOs;
    }

    public boolean actualizarCarnet(int idEntrenador, CarnetDTO carnetDTO) {
        Carnet carnet = mapearDTOAEntidad(carnetDTO);
        return carnetDAO.actualizarCarnetPorIdEntrenador(idEntrenador, carnet);
    }

    private Carnet mapearDTOAEntidad(CarnetDTO carnetDTO) {
        Carnet carnet = new Carnet();
        carnet.setIdEntrenador(carnetDTO.getIdEntrenador());
        carnet.setFechaExpedicion(carnetDTO.getFechaExpedicion());
        carnet.setPuntos(carnetDTO.getPuntos());
        carnet.setNumVictorias(carnetDTO.getNumVictorias());
        return carnet;
    }

    private CarnetDTO mapearEntidadADTO(Carnet carnet) {
        return new CarnetDTO(carnet.getIdEntrenador(), carnet.getFechaExpedicion(), carnet.getPuntos(),
                carnet.getNumVictorias());
    }
}
