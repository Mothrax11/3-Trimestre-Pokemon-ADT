package com.proyectopokemonadt.SERVICES;


import com.proyectopokemonadt.DAO.CombateDAOImplementacion;
import com.proyectopokemonadt.DTO.CombateDTO;
import com.proyectopokemonadt.ENTIDADES.Combate;
import java.util.ArrayList;
import java.util.List;

public class CombateServices {
    
    private static CombateServices instancia;
    private CombateServices(){
       
    }
    
    private CombateDAOImplementacion combateDAOImplementacion = CombateDAOImplementacion.getInstance();

    public static CombateServices getInstancia(){
        if (instancia == null) {
            instancia = new CombateServices();
        }
        return instancia;
    }

    public CombateServices(CombateDAOImplementacion combateDAOImplementacion) {
        this.combateDAOImplementacion = combateDAOImplementacion;
    }

    public boolean crearCombate(CombateDTO combatedto) {
        return combateDAOImplementacion.crearCombate(combatedto);
    }

    public boolean eliminarCombatePorId(int id) {
        return combateDAOImplementacion.eliminarCombatePorId(id);
    }

    public boolean actualizarCombate(int idCombate, Combate combate) {
        return combateDAOImplementacion.actualizarCombate(idCombate, combate);
    }

    public Combate obtenerCombatePorId(int id) {
        return combateDAOImplementacion.obtenerCombatePorId(id);
    }

    public List<Combate> obtenerTodosLosCombates() {
        return combateDAOImplementacion.obtenerTodosLosCombates();
    }

    public List<CombateDTO> obtenerTodosLosCombatesPorIdTorneo(int idTorneo) {
        return mapearListaEntidadAListaCombateDTO(combateDAOImplementacion.obtenerTodosLosCombatesPorIdTorneo(idTorneo));
    }

     public static CombateDTO mapearEntidadACombateDTO(Combate combate) {
        return new CombateDTO(
                combate.getId(),
                combate.getFecha(),
                combate.getIdTorneo()
        );
    }

    public static Combate mapearCombateDTOAEntidad(CombateDTO combateDTO) {
        return new Combate(
                combateDTO.getId(),
                combateDTO.getFecha(),
                combateDTO.getIdTorneo()
        );
    }

    public static List<CombateDTO> mapearListaEntidadAListaCombateDTO(List<Combate> combates) {
        List<CombateDTO> combateDTOs = new ArrayList<>();
        for (Combate combate : combates) {
            combateDTOs.add(mapearEntidadACombateDTO(combate));
        }
        return combateDTOs;
    }

    public static List<Combate> mapearListaCombateDTOAListaEntidad(List<CombateDTO> combateDTOs) {
        List<Combate> combates = new ArrayList<>();
        for (CombateDTO combateDTO : combateDTOs) {
            combates.add(mapearCombateDTOAEntidad(combateDTO));
        }
        return combates;
    }
}
