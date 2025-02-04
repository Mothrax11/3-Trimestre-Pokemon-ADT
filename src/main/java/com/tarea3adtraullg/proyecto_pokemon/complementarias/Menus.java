package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import com.tarea3adtraullg.proyecto_pokemon.SERVICES.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.TorneoServices;
import com.tarea3adtraullg.proyecto_pokemon.autenticacion.*;
import com.tarea3adtraullg.proyecto_pokemon.entidades.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Clase que contiene los métodos para mostrar los menús del sistema.
 * Estos menús permiten al usuario interactuar con diferentes opciones según su
 * rol
 * (invitado, administrador general, administrador de torneos o entrenador).
 * 
 * @author raullg97
 */
@Component
public class Menus {

    @Autowired
    Autenticador autenticator;

    @Autowired
    EntrenadorServices entrenadorServices;

    @Autowired
    TorneoServices torneoServices;

    static Entrenador temp = null;

    /**
     * Muestra el menú principal del sistema, donde el usuario puede elegir entre
     * registrarse, iniciar sesión o iniciar sesión como invitado.
     */
    public void menuPrincipal() {
        System.out.print("\033[H\033[2J");
        ArrayList<Torneo> torneos = new ArrayList<>();
        Torneo torneoDefault = new Torneo("Primer Torneo Default", 'A', 0l);
        torneos.add(torneoDefault);

        System.out.println("Bienvenido Invitado al Pokemon Torunament 2025!");
        System.out.println("¿Qué deseas hacer?");
        System.out.println("1 -> Registrarme.");
        System.out.println("2 -> Iniciar sesión.");
        System.out.println("3 -> Cerrar");

        Scanner sc = new Scanner(System.in);
        int eleccion = sc.nextInt();

        if (eleccion == 1) {
            registrar(torneos);

        }

        if (eleccion == 2) {
            boolean login = false;
            while (login == false) {
                if(iniciarSesion(torneos, torneoDefault)){

                    login = true;
                }
            }
            

        }

        if (eleccion == 3) {
            System.out.print("\033[H\033[2J");
            System.out.println("Hasta la próxima!");
            System.exit(0);
        }
    }

    public void registrar(ArrayList<Torneo> torneos) {
        Scanner sc = new Scanner(System.in);
        boolean vb = false;
        while (!vb) {
            System.out.println("¿Cuál es tu nombre?");
            String nombre = sc.next();

            System.out.println("¿Cuál es tu contraseña?");
            String contraseña = sc.next();

            System.out.println("¿Cuál es tu nacionalidad?");
            ShowNations.show();
            String nac = sc.next();

            while (!autenticator.existeNacionalidad(nac)) {
                System.out.println("Eliga una nacionalidad de la lista");
                ShowNations.show();
                nac = sc.next();
            }

            System.out.println("¿Desea continuar con los siguientes datos? S/N");
            System.out.println("Usuario: " + nombre);
            System.out.println("Contraseña: " + contraseña);
            System.out.println("Nacionalidad: " + nac);
            String respuesta = sc.next().toUpperCase();

            while (respuesta.equals("N")) {
                System.out.print("Usuario: ");
                nombre = sc.next();
                System.out.print("Contraseña: ");
                contraseña = sc.next();
                System.out.println("Nacionalidad: ");
                nac = sc.next();

                while (!autenticator.existeNacionalidad(nac)) {
                    System.out.println("Eliga una nacionalidad de la lista");
                    ShowNations.show();
                    nac = sc.next();
                }
                System.out.println("¿Desea continuar con los siguientes datos? S/N");
                respuesta = sc.next().toUpperCase();
            }
            vb = true;

            // Asigna los datos al usuario activo
            UsuarioActivo.getInstancia().setNombre(nombre);
            UsuarioActivo.getInstancia().setContrasena(contraseña);
            UsuarioActivo.getInstancia().setNacionalidad(nac);

            // Llama a RegistroENT para registrar el usuario
            autenticator.registrarEntrenador(nombre, contraseña, nac, "ENT");

            mostrarMenuEntrenador(torneos); // Muestra el menú del entrenador
        }
    }

    public boolean  iniciarSesion(ArrayList<Torneo> torneos, Torneo torneoDefault) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuál es tu nombre?");
        String nombre = sc.next();
        System.out.println("¿Cuál es tu contraseña?");
        String contraseña = sc.next();

        if (nombre.equals("admintorneos") && contraseña.equals("Passw0rd")) {
            mostrarMenuAdministradorTorneos();
        } else if (nombre.equals("admingeneral") && contraseña.equals("Passw0rd")) {
            mostrarMenuAdminGeneral();
            int choice = sc.nextInt();
            boolean booleanTorVal = false;

            if (choice == 1) {
                while (!booleanTorVal) {
                    Scanner topSc = new Scanner(System.in);
                    System.out.print("Nombre del torneo: ");
                    String nombreTorneo = topSc.next();
                    System.out.println("Cual es la region del torneo: ");
                    System.out.println("1 -> AMERICAS | 2 -> EMEA | 3 -> CHINA | 4 -> PACIFICO");
                    int region = topSc.nextInt();
                    char codRegion = '*';
                    switch (region) {
                        case 1:
                            codRegion = 'A';
                            break;
                        case 2:
                            codRegion = 'E';
                            break;

                        case 3:
                            codRegion = 'C';
                            break;

                        case 4:
                            codRegion = 'P';
                            break;

                        default:
                            break;
                    }

                    // Revisa que el nuevo torneo no tiene el nombre de alguno ya creado
                    List<Torneo> todosLosTorneos = torneoServices.obtenerTodosLosTorneos();
                    for (int i = 0; i < todosLosTorneos.size(); i++) {
                        if (todosLosTorneos.get(i).getNombre().equals(nombreTorneo)
                                && (todosLosTorneos.get(i).getCodRegion() == codRegion)) {
                            System.out.println(
                                    "Nombre del torneo con esa región en uso, escriba otro nombre o región.");
                        } else {
                            booleanTorVal = true;
                        }
                    }

                    System.out.print("Cuantos puntos se llevara el ganador: ");
                    float puntosVictoria = topSc.nextInt();
                    System.out.println("El torneo " + nombreTorneo + " ha sido creado con éxito");
                    Torneo torneo = new Torneo(nombreTorneo, codRegion, puntosVictoria);
                    ArchivadorTorneos.Archivador(torneoDefault);

                    boolean adminTvalido = false;

                    while (adminTvalido == false) {
                        System.out.print("¿Cual es el nombre del administrador del torneo?: ");
                        String nombreAT = topSc.next();
                        System.out.print("¿Cual es la contraseña del administrador del torneo?: ");
                        String passAT = topSc.next();
                        Entrenador adminDelTorneo = entrenadorServices.buscarPorNombreYContrasena(nombreAT, passAT);
                        if (adminDelTorneo == null) {
                            System.out.println("Error en la seleccion del administrador del torneo.");
                            System.out.println("Por favor, seleccione otro en su lugar o cree uno nuevo.");
                        } else if (adminDelTorneo.getTipoUsr() == "ENT"){
                            System.out.println("Error en la seleccion del administrador del torneo.");
                            System.out.println("Por favor, seleccione otro en su lugar o cree uno nuevo.");
                        } else {
                            adminTvalido = true;
                        }
                    }
                    PONER EL ID DEL ADMIN TORNEO EN EL TORNEO
                    torneoServices.crearTorneo(torneo);
                    torneos.add(torneo);
                    booleanTorVal = true;
                    topSc.close();
                    return true;
                }
            }

        } else {
            if (autenticator.comprobarUsuario(nombre, contraseña, "ENT")) {
                UsuarioActivo act = new UsuarioActivo(entrenadorServices.buscarPorNombreYContrasena(nombre, contraseña));
    
                mostrarMenuEntrenador(torneos);
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra el menú para el administrador general.
     * El administrador general puede crear un torneo, iniciar sesión o cerrar
     * sesión.
     */
    public void mostrarMenuAdminGeneral() {
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Administrador General, ¿que desea hacer?");
        System.out.println("1 -> Crear torneo.");
        System.out.println("2 -> Iniciar sesion.");
        System.out.println("3 -> Cerrar sesion.");

    }

    /**
     * Muestra el menú para el entrenador.
     * El entrenador puede exportar su carnet.
     */
    public void mostrarMenuEntrenador(ArrayList<Torneo> torneos) {
        Scanner sc = new Scanner(System.in);
        // Limpia la consola (dependiendo de la terminal)
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Entrenador, ¿que desea hacer?");
        System.out.println("1 -> Exportar mi carnet");
        System.out.println("2 -> Ver torneos activos");
        System.out.println("3 -> Volver al menu principal");
        System.out.println("4 -> Salir");
        System.out.println("---------------------------------------------------------------");
        int eleccion = sc.nextInt();

        if (eleccion == 1) {
            Exportar ex = new Exportar(UsuarioActivo.getInstancia());
            ex.ejecutar();
            System.out.println("Carnet exportado con éxito!");
            mostrarMenuEntrenador(torneos);
        }

        if (eleccion == 2) {
            System.out.println(
                    "Escriba el numero del torneo en el que se quiere inscribir o escriba '-1' para salir: ");
            int numTorneo = 1;
            for (int n = 0; n < torneos.size(); n++) {
                System.out.println(numTorneo + " - " + torneos.get(n).getNombre());
                numTorneo++;
            }
            int eleccionTorneo = sc.nextInt();

            if (eleccionTorneo == -1) {
                menuPrincipal();
            } else {
                // torneos.get(eleccionTorneo - 1).inscribir(temp);
                System.out.println("El usuario " + temp.getNombre()
                        + " se ha inscrito correctamente en el torneo "
                        + torneos.get(eleccionTorneo - 1).getNombre());
            }
            mostrarMenuEntrenador(torneos);
        }

        if (eleccion == 3) {
            menuPrincipal();
        }

        if (eleccion == 4) {
            cerrarPrograma();
        }

    }

    /**
     * Muestra el menú para el administrador de torneos.
     * El administrador de torneos puede salir del sistema.
     */
    public void mostrarMenuAdministradorTorneos() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Bienvenido Administrador de Torneos, ¿que desea hacer?");
        System.out.println("1 -> Salir.");
        System.out.println("---------------------------------------------------------------");
    }

    public void cerrarPrograma() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Hasta la próxima!");
        System.exit(0);
    }
}
