package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tarea3adtraullg.proyecto_pokemon.SERVICES.CarnetServices;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.ShowNations;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Carnet;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.entidades.UsuarioActivo;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;

@Component
public class Autenticador {
    @Autowired
    private final RepoEntrenador repoEntrenador;
    @Autowired
    private final EntrenadorServices entrenadorServices;
    private boolean registroExitoso = false;

    @Autowired
    CarnetServices carnetServices;

    @Autowired
    public Autenticador(RepoEntrenador repoEntrenador, EntrenadorServices entrenadorServices) {
        this.repoEntrenador = repoEntrenador;
        this.entrenadorServices = entrenadorServices;
    }

    // Método para registrar un nuevo usuario en el sistema
    public boolean registrarEntrenador(String nombre, String pass, String nac, String tipoUsr) {
        if (usuarioExistente(nombre, pass)) {
            return false; // El usuario ya existe
        } else {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios",
                "credenciales.txt"),true));
                bf.write(nombre);
                bf.write(" ");
                bf.write(pass);
                bf.write(" ");
                bf.write(tipoUsr);
                bf.write(" ");
                bf.newLine();
                bf.close();
                Entrenador nuevoEntrenador = new Entrenador(nombre, pass, nac,  tipoUsr);
                entrenadorServices.crearEntrenador(nuevoEntrenador);
                LocalDate fechaC = LocalDate.now();
                Carnet c = new Carnet();
                c.setIdEntrenador(entrenadorServices.buscarPorNombreYContrasena(nombre, pass).getId());
                c.setNumVictorias(0);
                c.setPuntos(0);
                c.setFechaExpedicion(fechaC);
                carnetServices.crearCarnet(c);

                return true; 
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false; 
            }
        }
    }

    public boolean registrarAdministradorTorneo(String nombre, String pass, String nac, String tipoUsr) {
        if (usuarioExistente(nombre, pass)) {
            return false;
        } else {
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(
                        new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios",
                                "credenciales.txt"),
                        true));
                bf.write(nombre);
                bf.write(" ");
                bf.write(pass);
                bf.write(" ");
                bf.write(tipoUsr);
                bf.write(" ");
                bf.newLine();
                bf.close();
                Entrenador nuevoEntrenador = new Entrenador(nombre, pass, nac, tipoUsr);
                entrenadorServices.crearEntrenador(nuevoEntrenador);
                LocalDate fechaC = LocalDate.now();
                Carnet c = new Carnet();
                c.setIdEntrenador(entrenadorServices.buscarPorNombreYContrasena(nombre, pass).getId());
                c.setNumVictorias(0);
                c.setPuntos(0);
                c.setFechaExpedicion(fechaC);
                carnetServices.crearCarnet(c);
                return true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

    public boolean usuarioExistente(String nombre, String pass) {
        boolean encontradoEnArchivo = false;
        boolean encontradoEnBD = false;

        File rd = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios", "credenciales.txt");

        // Verificación en el archivo de credenciales
        try {
            BufferedReader br = new BufferedReader(new FileReader(rd));
            String buscar;
            while ((buscar = br.readLine()) != null) {
                String[] palabrasLinea = buscar.split(" ");
                if (palabrasLinea.length >= 3 && palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)) {
                    encontradoEnArchivo = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            return false;
        }

        Entrenador entrenador = entrenadorServices.buscarPorNombreYContrasena(nombre, pass);
        if (entrenador != null) {
            encontradoEnBD = true;

            UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
            usuarioActivo.setId(entrenador.getId());
            usuarioActivo.setNombre(entrenador.getNombre());
            usuarioActivo.setNacionalidad(entrenador.getNacionalidad());
            usuarioActivo.setContrasena(entrenador.getContrasena());
            usuarioActivo.setFechaCreacion(entrenador.getFechaCreacion());
            usuarioActivo.setTipoUsr(entrenador.getTipoUsr());
            usuarioActivo.setCarnet(entrenador.getCarnet());

            System.out.println("Usuario validado en archivo y base de datos: " + usuarioActivo.getNombre());
        }



        return encontradoEnArchivo && encontradoEnBD;
    }

    // Método para escribir el log del inicio de sesión
    public static void writeLog(String usuario) {
        LocalDate fecha = LocalDate.now();
        LocalTime lt = LocalTime.now();
        File log = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios", "log.txt");
        try {
            FileWriter fw = new FileWriter(log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "-> " + usuario);
            System.out.println("[" + fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear() + "|"
                    + lt.getHour() + ":" + lt.getMinute() + ":" + lt.getSecond() + "]" + "-> " + " El usuario "
                    + usuario
                    + " ha sido logueado correctamente.");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para comprobar si el usuario y la contraseña son correctos
    public boolean comprobarUsuario(String nombre, String pass, String rol) {
        // Leemos las credenciales desde un archivo
        File rd = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios",
                "credenciales.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(rd));
            String buscar;
            while ((buscar = br.readLine()) != null) {
                String[] palabrasLinea = buscar.split(" ");
                if (palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)) {
                    if (palabrasLinea[2].equals("AT")) {
                        Entrenador usuario = entrenadorServices.buscarPorNombreYContrasena(nombre, pass);
                        if (usuario != null) { // Asegurarse de que el usuario no sea null
                            UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                            usuarioActivo.setId(usuario.getId());
                            usuarioActivo.setNombre(usuario.getNombre());
                            usuarioActivo.setNacionalidad(usuario.getNacionalidad());
                            usuarioActivo.setContrasena(usuario.getContrasena());
                            usuarioActivo.setFechaCreacion(usuario.getFechaCreacion());
                            usuarioActivo.setTipoUsr(usuario.getTipoUsr());
                            usuarioActivo.setCarnet(usuario.getCarnet());
                            System.out.println(usuarioActivo.getNombre());
                            br.close();
                            return true;
                        } else {
                            System.out.println("Usuario no encontrado.");
                            br.close();
                            return false;
                        }
                        
                    } else if (palabrasLinea[2].equals("ENT")) {
                        Entrenador usuario = entrenadorServices.buscarPorNombreYContrasena(nombre, pass);
                        if(usuario != null) { // Asegurarse de que el usuario no sea null
                            UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                            usuarioActivo.setId(usuario.getId());
                            usuarioActivo.setNombre(usuario.getNombre());
                            usuarioActivo.setNacionalidad(usuario.getNacionalidad());
                            usuarioActivo.setContrasena(usuario.getContrasena());
                            usuarioActivo.setFechaCreacion(usuario.getFechaCreacion());
                            usuarioActivo.setTipoUsr(usuario.getTipoUsr());
                            usuarioActivo.setCarnet(usuario.getCarnet());
                            System.out.println(usuarioActivo.getNombre());
                            br.close();
                            writeLog(nombre);
                            return true;
                        } else{
                            System.out.println("Usuario no encontrado.");
                            br.close();
                            return false;
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                        br.close();
                        return false;
                    }
                }
            }
            br.close();
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

     public boolean existeNacionalidad(String nac) {
        return ShowNations.listaPaises.contains(nac);
    }
}
