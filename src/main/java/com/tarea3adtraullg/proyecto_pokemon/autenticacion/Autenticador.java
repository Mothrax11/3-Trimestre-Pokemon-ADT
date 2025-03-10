package com.tarea3adtraullg.proyecto_pokemon.autenticacion;


import java.time.LocalDate;

import com.tarea3adtraullg.proyecto_pokemon.services.Db4oServices;
import org.springframework.beans.factory.annotation.Autowired;

import com.tarea3adtraullg.proyecto_pokemon.services.CarnetServices;
import com.tarea3adtraullg.proyecto_pokemon.services.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.ShowNations;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Carnet;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;
import org.springframework.stereotype.Component;



@Component
public class Autenticador {

    @Autowired
    private RepoEntrenador repoEntrenador;
    @Autowired
    private EntrenadorServices entrenadorServices;
    private boolean registroExitoso = false;

    @Autowired
    CarnetServices carnetServices;

    @Autowired
    Db4oServices db4oServices;





    // Método para registrar un nuevo usuario en el sistema
    public boolean registrarEntrenador(String nombre, String pass, String nac, String tipoUsr) {
        if (db4oServices.usuarioExistente(nombre, pass)) {
            return false; // El usuario ya existe
        } else {
            try {
                Entrenador nuevoEntrenador = new Entrenador(nombre, pass, nac, tipoUsr);
                db4oServices.crearEntrenadorDB4O(nuevoEntrenador);
                // Creación del carnet
                LocalDate fechaC = LocalDate.now();
                Carnet c = new Carnet();
                c.setIdEntrenador((long)entrenadorServices.obtenerTodosLosEntrenadores().size() + 1); // Usar el ID generado por DB4O
                c.setNumVictorias(0);
                c.setPuntos(0);
                c.setFechaExpedicion(fechaC);

                if(entrenadorServices.buscarPorNombreYContrasena(nombre, pass) == null){
                    entrenadorServices.crearEntrenador(nuevoEntrenador);
                    carnetServices.crearCarnet(c);
                }
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public boolean registrarAdministradorTorneo(String nombre, String pass, String nac, String tipoUsr) {
        if (db4oServices.usuarioExistente(nombre, pass)) {
            return false;
        } else {
            try {
                Entrenador nuevoEntrenador = new Entrenador(nombre, pass, nac, tipoUsr);


                // Creación del carnet
                LocalDate fechaC = LocalDate.now();
                Carnet c = new Carnet();
                c.setIdEntrenador(nuevoEntrenador.getId()); // Usar el ID generado por DB4O
                c.setNumVictorias(0);
                c.setPuntos(0);
                c.setFechaExpedicion(fechaC);
                if(entrenadorServices.buscarPorNombreYContrasena(nombre, pass) == null){
                    entrenadorServices.crearEntrenador(nuevoEntrenador);
                    carnetServices.crearCarnet(c);
                }
                db4oServices.crearEntrenadorDB4O(nuevoEntrenador);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    //// Método para escribir el log del inicio de sesión
    //    public static void writeLog(String usuario) {
    //        LocalDate fecha = LocalDate.now();
    //        LocalTime lt = LocalTime.now();
    //        File log = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios", "log.txt");
    //        try {
    //            FileWriter fw = new FileWriter(log, true);
    //            BufferedWriter bw = new BufferedWriter(fw);
    //            bw.write("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
    //                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "-> " + usuario);
    //            System.out.println("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
    //                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "-> " + " El usuario "
    //                    + usuario
    //                    + " ha sido logueado correctamente.");
    //            bw.newLine();
    //            bw.close();
    //        } catch (IOException e) {
    //            System.out.println(e.getMessage());
    //        }




    public boolean existeNacionalidad(String nac) {
        return ShowNations.listaPaises.contains(nac);
    }
}

