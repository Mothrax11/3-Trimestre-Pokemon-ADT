package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.*;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.entidades.UsuarioActivo;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;

/**
 * Clase encargada de gestionar el inicio de sesión en el sistema.
 * Implementa un patrón Singleton para asegurar que solo exista una instancia de
 * la clase Login.
 * También registra los intentos de inicio de sesión y guarda un log con fecha y
 * hora.
 * 
 * @author raullg97
 */
public class Login {
    private static Login instance; // Instancia única de la clase Login
    private final RepoEntrenador repoEntrenador;
    private Login(RepoEntrenador repoEntrenador) {
        this.repoEntrenador = repoEntrenador;
    }

    /**
     * Singleton para obtener la única instancia de Login.
     * Si no está creada, se crea una nueva instancia; si ya existe, devuelve la
     * actual.
     *
     * @return La instancia única de Login.
     */
    public static Login getInstance(RepoEntrenador repoEntrenador) {
        if (instance == null) {
            instance = new Login(repoEntrenador);
        }
        return instance;
    }

    /**
     * Método encargado de registrar en el archivo log.txt el nombre de usuario que
     * ha iniciado sesión,
     * junto con la fecha y hora exactas del evento.
     *
     * @param usuario Nombre del usuario que se ha logueado.
     */
    public static void writeLog(String usuario) {
        LocalDate fecha = LocalDate.now();
        LocalTime lt = LocalTime.now();

        File log = new File("src\\main\\java\\com\\tarea3adtraullg\\proyecto_pokemon\\archviosComplementarios",
                "log.txt");
        try {
            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "-> " + usuario);
            System.out.println("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "->" + " El usuario " + usuario
                    + " ha sido logueado correctamente.");
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método que comprueba si el usuario y la contraseña introducidos existen.
     * Si el inicio de sesión es correcto, devuelve true.
     * 
     * Dentro del while, la variable "buscar" se convertirá en un string con el
     * contenido de la línea actual del archivo.
     * Cada línea sigue el formato "Nombre Contraseña Rol Id", y se utiliza
     * String.split()
     * con el delimitador " " (espacio), lo cual divide la línea en un array de
     * cuatro strings.
     * Esto permite localizar los distintos componentes de cada entrada, como el
     * nombre y la contraseña.
     *
     * @param nombre Nombre del usuario a verificar.
     * @param pass   Contraseña del usuario.
     * @param id     ID único del usuario.
     * @param rol    Rol asignado al usuario.
     * @return true si el usuario existe y la contraseña es correcta, false en caso
     *         contrario.
     */
    public boolean comprobarUsuario(String nombre, String pass, String rol) {
        String buscar;
        File rd = new File("src\\main\\java\\com\\tarea3adtraullg\\proyecto_pokemon\\archviosComplementarios",
                "credenciales.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            while ((buscar = br.readLine()) != null) {
                String[] palabrasLinea = buscar.split(" ");
                if (palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)) {
                    if (palabrasLinea[2].equals("AT")) {
                        Menus.mostrarMenuAdministradorTorneos();
                    } else if(palabrasLinea[2].equals("ENT")){
                        Entrenador usuario = repoEntrenador.findByNombreAndContrasena(nombre, pass);
                            UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                            usuarioActivo.setId(usuario.getId());
                            usuarioActivo.setNombre(usuario.getNombre());
                            usuarioActivo.setNacionalidad(usuario.getNacionalidad());
                            usuarioActivo.setContrasena(usuario.getContrasena());
                            usuarioActivo.setFechaCreacion(usuario.getFechaCreacion());
                            usuarioActivo.setTipoUsr(usuario.getTipoUsr());
                            usuarioActivo.setCarnet(usuario.getCarnet());
                            System.out.println(usuarioActivo.getNombre());
                    }
                    writeLog(nombre);
                    
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
