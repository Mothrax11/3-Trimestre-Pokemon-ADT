package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.tarea3adtraullg.proyecto_pokemon.entidades.*;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.*;

/**
 * Clase que gestiona el registro de usuarios en el sistema, incluyendo
 * la creación de nuevos entrenadores y administradores de torneo.
 * Proporciona métodos para registrar, validar y almacenar datos de usuarios.
 * 
 * @author raullg97
 */
public class RegistroENT {
@Autowired
    private final EntrenadorServices entrenadorServices;
    private static boolean registerOK = false;
    private static Entrenador temp;
    private static RegistroENT instancia;

    public static RegistroENT getInstancia(){
        if (instancia == null) {
            instancia = new RegistroENT(temp);
            return instancia;
        }
        return instancia;
    }
    
    public RegistroENT(Entrenador temp){
        this.temp = temp;
    }

    public static void registroData(Entrenador temp) {
            String rol = "ENT";
            
            if (creadorUsuario(temp.getNombre(), temp.getContrasena(), rol,temp.getNacionalidad())) {
                System.out.println("Se ha registrado correctamente! Sus datos de registro: ");
                System.out.println("Usuario: " + temp.getNombre());
                System.out.println("Contraseña: " + temp.getContrasena());
                System.out.println("Nacionalidad: " + temp.getNacionalidad());
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
        }
}
            
    