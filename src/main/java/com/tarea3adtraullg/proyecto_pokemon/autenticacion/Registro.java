package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.tarea3adtraullg.proyecto_pokemon.entidades.*;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.*;

/**
 * Clase que gestiona el registro de usuarios en el sistema, incluyendo
 * la creación de nuevos entrenadores y administradores de torneo.
 * Proporciona métodos para registrar, validar y almacenar datos de usuarios.
 * 
 * @author raullg97
 */
public class Registro {

    private static boolean registerOK = false; // Indica si el registro se realizó correctamente

    /**
     * Método para registrar un nuevo usuario con el rol de entrenador (ENT).
     * Solicita datos al usuario como nombre y contraseña, y llama a
     * creadorUsuario() para completar el registro.
     * Solo se registrarán usuarios con rol "ENT".
     */
    public static void registroData(Entrenador temp) {
            String rol = "ENT";
            /*
            if (creadorUsuario(temp.getNombre(), temp.getContraseña(), rol,temp.getNacionalidad())) {
                System.out.println("Se ha registrado correctamente! Sus datos de registro: ");
                System.out.println("Usuario: " + temp.getNombre());
                System.out.println("Contraseña: " + temp.getContraseña());
                System.out.println("Nacionalidad: " + temp.getNacionalidad());
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
            */
        }

    /**
     * Método similar a registroData(), pero se utiliza exclusivamente para
     * crear administradores de torneo (AT). Se llama cuando se crea un torneo
     * y asigna automáticamente el rol de "AT" al usuario.
     *
     * @param nombreAT Nombre del administrador de torneo.
     * @param passAT   Contraseña del administrador de torneo.
     * @param rol      Rol que se asignará, que será "AT" por defecto.
     */
    public static void registroDataAT(String nombreAT, String passAT, String rol, String nac) {
        Scanner sc = new Scanner(System.in);
        boolean vb = false;
        while (!vb) {
            System.out.println("¿Desea continuar con los siguientes datos? S/N");
            String respuesta = sc.next().toUpperCase();
            while (respuesta.equals("N")) {

                System.out.print("Usuario: ");
                nombreAT = sc.next();
                System.out.print("Contraseña: ");
                passAT = sc.next();
                System.out.println("Nacionalidad: ");
                nac = sc.next();
                while (!Registro.existeNacionalidad(nac)) {
                    System.out.println("Eliga una nacionalidad de la lista");
                    ShowNations.show();
                    nac = sc.next();
                }
                System.out.println("¿Desea continuar con los siguientes datos? S/N");
                respuesta = sc.next().toUpperCase();
            }

            //if (creadorUsuario(nombreAT, passAT, rol, idGenerator.generador(), nac)) {
                System.out.println("Se ha registrado correctamente con el usuario " + nombreAT);
                vb = true;
            //} else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            //}
  
        }
        sc.close();
    }
    
            
    public static boolean existeNacionalidad(String nac) {
        if(ShowNations.listaPaises.contains(nac)){
            return true;
        }  
        return false;
    }
    

    /**
     * Método que escribe los datos del usuario en un archivo llamado
     * "credenciales.txt".
     * Cada línea en el archivo sigue el formato "Nombre Contraseña Rol Id",
     * lo que facilita la gestión de credenciales en el sistema.
     *
     * @param nombre      Nombre del usuario a registrar.
     * @param contraseña  Contraseña del usuario.
     * @param tipoUsuario Rol del usuario (como "ENT" o "AT").
     * @param id          ID único del usuario.
     * @return Devuelve true si el usuario fue registrado exitosamente.
     */
    public static boolean creadorUsuario(String nombre, String contraseña, String tipoUsuario, String nac) {
        if (usuarioExistente(nombre, contraseña)) {
            
        } else {
            File usrs = new File(
                    "src\\main\\java\\com\\tarea3adtraullg\\proyecto_pokemon\\archviosComplementarios",
                    "credenciales.txt");
            try {
                FileWriter fw = new FileWriter(usrs, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(nombre);
                bf.write(" ");
                bf.write(contraseña);
                bf.write(" ");
                bf.write(tipoUsuario);
                bf.write(" ");
                bf.newLine();
                bf.close();
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Método que verifica si un usuario con un nombre y contraseña específicos ya
     * existe.
     * Lee el archivo "credenciales.txt" y compara las credenciales introducidas
     * con las existentes en el archivo. Si encuentra coincidencia, devuelve true.
     *
     * @param nombre     Nombre del usuario a verificar.
     * @param contraseña Contraseña del usuario a verificar.
     * @return Devuelve true si el usuario existe, de lo contrario, false.
     */
    public static boolean usuarioExistente(String nombre, String contraseña) {
        String buscar;
        File rd = new File("src\\main\\java\\com\\tarea3adtraullg\\proyecto_pokemon\\archviosComplementarios",
                "credenciales.txt");

        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);

            if (rd.length() == 0) {
                br.close();
                return false;
            } else {
                while ((buscar = br.readLine()) != null) {
                    String[] palabrasLinea = buscar.split(" ");
                    if (palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(contraseña)) {
                        br.close();
                        return true;
                    }
                }
            }

            br.close();
            return false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Método para verificar el estado del registro.
     * 
     * @return Devuelve true si el registro fue exitoso.
     */
    public static boolean regOK() {
        return registerOK;
    }

}
