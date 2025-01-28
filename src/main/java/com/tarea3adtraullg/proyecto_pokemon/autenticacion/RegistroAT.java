package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.util.Scanner;

import com.tarea3adtraullg.proyecto_pokemon.complementarias.ShowNations;

public class RegistroAT {

    private Validaciones validaciones;
    private static RegistroAT instance;

    public RegistroAT(){

    }

    public static RegistroAT getInstance(){
        if(instance == null){
            instance = new RegistroAT();
            return instance;
        }
        return instance;
    }

    
 public void registroDataAT(String nombreAT, String passAT, String rol, String nacAT) {
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
                nacAT = sc.next();
                while (!Validaciones.existeNacionalidad(nacAT)) {
                    System.out.println("Eliga una nacionalidad de la lista");
                    ShowNations.show();
                    nacAT = sc.next();
                }
                System.out.println("¿Desea continuar con los siguientes datos? S/N");
                respuesta = sc.next().toUpperCase();
            }

            if (validaciones.creadorUsuario(nombreAT, nacAT, passAT, nacAT)) {
                System.out.println("Se ha registrado correctamente con el usuario " + nombreAT);
                vb = true;
            } else {
                System.out.println("Ha ocurrido un error en el registro, intentelo de nuevo.");
            }
        }
        sc.close();
    }
}
