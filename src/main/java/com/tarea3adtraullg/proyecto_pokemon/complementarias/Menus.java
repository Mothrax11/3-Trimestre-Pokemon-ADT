package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import com.mongodb.client.MongoClient;
import com.tarea3adtraullg.proyecto_pokemon.dao.DAO_DatosTorneoMongo;
import com.tarea3adtraullg.proyecto_pokemon.services.*;
import com.tarea3adtraullg.proyecto_pokemon.autenticacion.*;
import com.tarea3adtraullg.proyecto_pokemon.entidades.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

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

    @Autowired
    TorneoAdministradorServices torneoAdministradorServices;

    @Autowired
    CombateEntrenadoresServices combateEntrenadoresServices;

    @Autowired
    CombateServices combateServices;

    @Autowired
    CarnetServices carnetServices;

    @Autowired
    Exportar exportar;

    @Autowired
    Db4oServices db4oServices;
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
        

            if(UsuarioActivo.getInstancia().getNombre().equals("a") && UsuarioActivo.getInstancia().getContrasena().equals("a")){
                mostrarMenuAdminGeneral(torneos, torneoDefault);
            } else if(UsuarioActivo.getInstancia().getTipoUsr().equals("ENT")){
               mostrarMenuEntrenador(torneos);
            } else if (UsuarioActivo.getInstancia().getTipoUsr().equals("AT")) {
                mostrarMenuAdministradorTorneos();
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

            while (!autenticator.existeNacionalidad(nac.toUpperCase())) {
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

                while (!autenticator.existeNacionalidad(nac.toUpperCase())) {
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

            
            autenticator.registrarEntrenador(nombre, contraseña, nac, "ENT");

            mostrarMenuEntrenador(torneos); // Muestra el menú del entrenador
        }
    }
    public void registrarAT(ArrayList<Torneo> torneos) {
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

            while (!autenticator.existeNacionalidad(nac.toUpperCase())) {
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

                while (!autenticator.existeNacionalidad(nac.toUpperCase())) {
                    System.out.println("Eliga una nacionalidad de la lista");
                    ShowNations.show();
                    nac = sc.next();
                }
                System.out.println("¿Desea continuar con los siguientes datos? S/N");
                respuesta = sc.next().toUpperCase();
            }
            vb = true;
            autenticator.registrarEntrenador(nombre, contraseña, nac, "AT");
        }
    }

    public boolean  iniciarSesion(ArrayList<Torneo> torneos, Torneo torneoDefault) {
        

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Cuál es tu nombre?");
        String nombre = sc.next();
        System.out.println("¿Cuál es tu contraseña?");
        String contraseña = sc.next();
        if (nombre.equals("a") && contraseña.equals("a")) {
            UsuarioActivo.getInstancia().setNombre("a");
            UsuarioActivo.getInstancia().setContrasena("a");
            mostrarMenuAdminGeneral(torneos, torneoDefault);
            return true;
        } else {
            if (db4oServices.comprobarUsuario(nombre, contraseña, "ENT") || db4oServices.comprobarUsuario(nombre, contraseña, "AT")) {
                UsuarioActivo act = new UsuarioActivo(entrenadorServices.buscarPorNombreYContrasena(nombre, contraseña));
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
    public void mostrarMenuAdminGeneral(ArrayList<Torneo> torneos, Torneo torneoDefault) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\033[H\033[2J");
        System.out.println("Bienvenido Administrador General, ¿que desea hacer?");
        System.out.println("1 -> Crear torneo.");
        System.out.println("2 -> Iniciar sesion.");
        System.out.println("3 -> Crear nuevo AT.");
        System.out.println("4 -> Gestionar usuarios.");
        System.out.println("5 -> Cerrar sesion.");
        System.out.println("6 -> Consultar datos de torneo.");

        UsuarioActivo.getInstancia().setNombre("a");
        UsuarioActivo.getInstancia().setContrasena("a");
        int choice2 = sc.nextInt();
        boolean booleanTorVal = false;

        if (choice2 == 1) {
            while (booleanTorVal == false) {
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

                List<Torneo> todosLosTorneos = torneoServices.obtenerTodosLosTorneos();
                boolean torneoExiste = false;
                for (int i = 0; i < todosLosTorneos.size(); i++) {
                    if (todosLosTorneos.get(i).getNombre().equals(nombreTorneo) && (todosLosTorneos.get(i).getCodRegion() == codRegion)) {
                        System.out.println( "Nombre del torneo con esa región en uso, escriba otro nombre o región.");
                        torneoExiste = true;
                    }  
                }

                if(torneoExiste == false){
                    booleanTorVal = true;
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
                        } else if (adminDelTorneo.getTipoUsr() == "ENT") {
                            System.out.println("Error en la seleccion del administrador del torneo.");
                            System.out.println("Por favor, seleccione otro en su lugar o cree uno nuevo.");
                        } else {
                            adminTvalido = true;
                            torneoServices.crearTorneo(torneo);
                            TorneoAdministrador torneoAdmin = new TorneoAdministrador();
                            torneoAdmin.setEntrenador(adminDelTorneo);
                            torneoAdmin.setTorneo(torneo);
                            torneoAdministradorServices.crearTorneoAdministrador(torneoAdmin);
                            System.out.println("El administrador del torneo " + torneo.getNombre() + " es "
                                    + adminDelTorneo.getNombre());
                            for (int k = 0; k < 3; k++) {
                                Combate combate = new Combate();
                                combate.setFecha(LocalDate.now());
                                combate.setTorneo(torneo);
                                CombateEntrenadores combateEntrenadores = new CombateEntrenadores();
                                combateEntrenadores.setCombate(combate);
                                combateEntrenadores.setIdTorneo(torneo.getIdTorneo());
                                combateServices.crearCombate(combate);
                                combateEntrenadoresServices.crearCombateEntrenadores(combateEntrenadores);
                            }
                        }
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                        torneos.add(torneo);
                    }
                }
            }
            
        } else if(choice2 == 3){
            registrarAT(torneos);

        } else if(choice2 == 4){
            System.out.println("¿Que te gusataría hacer?");
            System.out.println("1 - Listar entrenadores.");
            System.out.println("2 - Eliminar un entrenador.");
            System.out.println("3 - Modificar la contraseña de un entrenador.");
            int choice3 = sc.nextInt();

                if(choice3 == 1){
                    db4oServices.listarEntrenadoresDB4O();
                } else if(choice3 == 2){
                    System.out.println("Introduzca las credenciales del usuario a eliminar:");
                    System.out.println("Nombre:");
                    String nombreBorrar = sc.next();
                    System.out.println("Contraseña:");
                    String passwordBorrar = sc.next();
                    db4oServices.eliminarUsuarioDB4O(nombreBorrar, passwordBorrar);
                } else if(choice3 == 3){
                    System.out.println("Introduzca las credenciales del usuario al que le quiere cambiar la contraseña:");
                    System.out.println("Nombre:");
                    String nombreActualizar = sc.next();
                    System.out.println("Contraseña:");
                    String passwordActualizar = sc.next();
                    System.out.println("Nueva contraseña:");
                    String newPasswordActualizar = sc.next();
                    db4oServices.cambiarPassword(nombreActualizar, passwordActualizar, newPasswordActualizar);
                }
            mostrarMenuAdminGeneral(torneos, torneoDefault);
        } else if(choice2 == 5){
            menuPrincipal();
        } else if(choice2 == 6){
            System.out.println("¿Que quieres hacer?");
            System.out.println("1 - Ver datos de un torneo.");
            System.out.println("2 - Ver ganador de un torneo.");
            System.out.println("3 - Listar 2 entrenadores con mas victorias y cuantas cada uno.");
            System.out.println("4 - Listar todos los entrenadores y sus puntos.");
            System.out.println("5 - Ver puntos de un entrenador.");
            System.out.println("6 - Listar torneos de una region.");
            int choiceDatosTorneo = sc.nextInt();
            switch (choiceDatosTorneo){
                case 1:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.listarTodosLosTorneos();
                        int idEleccion = sc.nextInt();
                        dao_datosTorneoMongo.verDatosTorneo(idEleccion);
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }

                    break;

                case 2:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.listarTodosLosTorneos();
                        int idEleccion = sc.nextInt();
                        dao_datosTorneoMongo.verGanadorTorneo(idEleccion);
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    break;

                case 3:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.obtenerTop2Entrenadores();
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    break;

                case 4:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.listarEntrenadoresYPuntos();
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    break;

                case 5:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        System.out.println("Introduce el id del entrenador:");
                        int idEleccion = sc.nextInt();
                        dao_datosTorneoMongo.verPuntosEntrenador((long)idEleccion);
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    break;

                case 6:
                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.listarTodosLosTorneos();
                        System.out.println("Introduce la letra de la region:");
                        System.out.println("A - ASIA | E - EMEA | C - CHINA | P - PACIFICO");
                        String region = sc.next();
                        dao_datosTorneoMongo.listarTorneosPorRegion(region.toUpperCase());
                        mostrarMenuAdminGeneral(torneos, torneoDefault);
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    break;
            }
        }
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
        System.out.println("2 -> Volver al menu principal");
        System.out.println("3 -> Salir");
        System.out.println("---------------------------------------------------------------");
        int eleccion = sc.nextInt();

        if (eleccion == 1) {
            exportar.ejecutar();
            System.out.println("Carnet exportado con éxito!");
            mostrarMenuEntrenador(torneos);
        }

        if (eleccion == 2) {
            menuPrincipal();
        }

        if (eleccion == 3) {
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
        System.out.println("1 -> Ver que torneos administro.");
        System.out.println("2 -> Añadir a un entrenador a uno de mis torneos.");
        System.out.println("3 -> Pelear.");
        System.out.println("4 -> Exportar los datos de uno de mis torneos.");
        System.out.println("5 -> Cerrar sesion.");
        System.out.println("6 -> Salir.");
        System.out.println("---------------------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        int eleccion = sc.nextInt();
        if(eleccion == 1){
            List<TorneoAdministrador> allTorneos = torneoAdministradorServices.obtenerTodosLosTorneoAdministradores();
            List<Torneo> misTorneos = new ArrayList<>();
            for (int i = 0; i < allTorneos.size(); i++) {
                if (allTorneos.get(i).getEntrenador().getId() == UsuarioActivo.getInstancia().getId()) {
                    misTorneos.add(allTorneos.get(i).getTorneo());
                }
            }

            for (int i = 0 ; i < misTorneos.size(); i++) {
                System.out.println((i + 1) + " " + misTorneos.get(i).getNombre());
            }
            mostrarMenuAdministradorTorneos();
        } else if(eleccion == 2){
            System.out.println("Cual es el nombre del entrenador que desea añadir?");
            String nombre = sc.next();
            System.out.println("Cual es su region?");
            String reg = sc.next();
            System.out.println("A que torneo lo quieres apuntar?");
            Entrenador entrenadorAInscribir = entrenadorServices.findByNombreAndReg(nombre, reg);
 
            List<TorneoAdministrador> allTorneos = torneoAdministradorServices.obtenerTodosLosTorneoAdministradores();
            List<Torneo> misTorneos = new ArrayList<>();
            for (int i = 0; i < allTorneos.size(); i++) {
                if (allTorneos.get(i).getEntrenador().getId() == UsuarioActivo.getInstancia().getId()) {
                    misTorneos.add(allTorneos.get(i).getTorneo());
                }
            }

            for (int i = 0; i < misTorneos.size(); i++) {
                System.out.println((i + 1) + " " + misTorneos.get(i).getNombre());
            }

            int eleccionTorneo = sc.nextInt();
            Torneo torneoAApuntar = misTorneos.get(eleccionTorneo - 1);

            List<Combate> allCombates = combateServices.obtenerTodosLosCombates();
            List<Combate> allCombatesTorneoEspecifico = new ArrayList<>();

            for (int i = 0; i < allCombates.size(); i++){
                if(allCombates.get(i).getTorneo().getId() == torneoAApuntar.getId()){
                    allCombatesTorneoEspecifico.add(allCombates.get(i));
                }
            }

            List<CombateEntrenadores> allCombateEntrenadores = combateEntrenadoresServices.obtenerTodosLosCombateEntrenadores();
            List<CombateEntrenadores> combateEntrenadoresEspecifico = new ArrayList<>();
            int sizeCombates = 0;
            for (int i = 0; i < allCombateEntrenadores.size(); i++){
                if(allCombateEntrenadores.get(i).getCombate().getId() == allCombatesTorneoEspecifico.get(sizeCombates).getId()){
                    sizeCombates++;
                    combateEntrenadoresEspecifico.add(allCombateEntrenadores.get(i));
                }
            }
        

            if(combateEntrenadoresEspecifico.get(0).getIdEntrenador1() == 0 && combateEntrenadoresEspecifico.get(1).getIdEntrenador1() == 0){
                CombateEntrenadores c1 = combateEntrenadoresEspecifico.get(0);
                c1.setIdEntrenador1(entrenadorAInscribir.getId());

                CombateEntrenadores c2 = combateEntrenadoresEspecifico.get(1);
                c2.setIdEntrenador1(entrenadorAInscribir.getId());

                List<Torneo> torneosEntrenador = entrenadorAInscribir.getTorneos();
                torneosEntrenador.add(torneoAApuntar);
                entrenadorAInscribir.setTorneos(torneosEntrenador);
                entrenadorServices.actualizarEntrenador(entrenadorAInscribir.getId(), entrenadorAInscribir);
                
                combateEntrenadoresServices.actualizarCombateEntrenadores(c1);
                combateEntrenadoresServices.actualizarCombateEntrenadores(c2);
                System.out.println("El usuario " + entrenadorAInscribir.getNombre() + " ha sido inscrito con exito al torneo " + torneoAApuntar.getNombre() + " con ID " + torneoAApuntar.getId());
                mostrarMenuAdministradorTorneos();
            } else if(combateEntrenadoresEspecifico.get(0).getIdEntrenador2() == 0 && combateEntrenadoresEspecifico.get(2).getIdEntrenador1() == 0){
                CombateEntrenadores c1 = combateEntrenadoresEspecifico.get(0);
                c1.setIdEntrenador2(entrenadorAInscribir.getId());

                CombateEntrenadores c2 = combateEntrenadoresEspecifico.get(2);
                c2.setIdEntrenador1(entrenadorAInscribir.getId());

                combateEntrenadoresServices.actualizarCombateEntrenadores(c1);
                combateEntrenadoresServices.actualizarCombateEntrenadores(c2);
                System.out.println(
                        "El usuario " + entrenadorAInscribir.getNombre() + " ha sido inscrito con exito al torneo "
                                + torneoAApuntar.getNombre() + " con ID " + torneoAApuntar.getId());
                mostrarMenuAdministradorTorneos();
            } else if (combateEntrenadoresEspecifico.get(1).getIdEntrenador2() == 0 && combateEntrenadoresEspecifico.get(2).getIdEntrenador2() == 0) {
                CombateEntrenadores c1 = combateEntrenadoresEspecifico.get(1);
                c1.setIdEntrenador2(entrenadorAInscribir.getId());

                CombateEntrenadores c2 = combateEntrenadoresEspecifico.get(2);
                c2.setIdEntrenador2(entrenadorAInscribir.getId());

                combateEntrenadoresServices.actualizarCombateEntrenadores(c1);
                combateEntrenadoresServices.actualizarCombateEntrenadores(c2);
                System.out.println(
                        "El usuario " + entrenadorAInscribir.getNombre() + " ha sido inscrito con exito al torneo "
                                + torneoAApuntar.getNombre() + " con ID " + torneoAApuntar.getId());
                mostrarMenuAdministradorTorneos();
            } else {
                System.out.println("El torneo al que intenta apuntar está lleno");
                mostrarMenuAdministradorTorneos();
            }
        } else if(eleccion == 3){
            pelear();
        } else if (eleccion == 4){
            System.out.println("Que torneo quieres exportar?:");
            List<TorneoAdministrador> allTorneos = torneoAdministradorServices.obtenerTodosLosTorneoAdministradores();
            List<Torneo> misTorneos = new ArrayList<>();
            for (int i = 0; i < allTorneos.size(); i++) {
                if (allTorneos.get(i).getEntrenador().getId() == UsuarioActivo.getInstancia().getId()) {
                    misTorneos.add(allTorneos.get(i).getTorneo());
                }
            }

            for (int i = 0; i < misTorneos.size(); i++) {
                System.out.println((i + 1) + " " + misTorneos.get(i).getNombre());
            }

            int eleccionTorneoExportar = sc.nextInt();
            ArchivadorTorneos.Archivador(misTorneos.get(eleccionTorneoExportar - 1));
            System.out.println("El torneo " + misTorneos.get(eleccionTorneoExportar - 1).getNombre()  + "exportado con exito!");
        } else if(eleccion == 5){
            menuPrincipal();
        } else if(eleccion == 6){
            cerrarPrograma();
        }
    }

    public void cerrarPrograma() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Hasta la próxima!");
        System.exit(0);
    }

    public void pelear(){
        System.out.println("En que torneo quieres que se pelee?");
        List<TorneoAdministrador> allTorneos = torneoAdministradorServices.obtenerTodosLosTorneoAdministradores();
        List<Torneo> misTorneos = new ArrayList<>();
        for (int i = 0; i < allTorneos.size(); i++) {
            if (allTorneos.get(i).getEntrenador().getId() == UsuarioActivo.getInstancia().getId()) {
                misTorneos.add(allTorneos.get(i).getTorneo());
            }
        }

        for (int i = 0; i < misTorneos.size(); i++) {
            System.out.println((i + 1) + " " + misTorneos.get(i).getNombre());
        }

        Scanner sc = new Scanner(System.in);
        int eleccionPelea = sc.nextInt();
        Torneo torneoAPelear = misTorneos.get( eleccionPelea - 1);
        int contadorCombatesPosibles = 0;
        List<Combate> allCombates = combateServices.obtenerTodosLosCombates();
            List<Combate> allCombatesTorneoEspecifico = new ArrayList<>();

            for (int i = 0; i < allCombates.size(); i++){
                if(allCombates.get(i).getTorneo().getId() == torneoAPelear.getId()){
                    allCombatesTorneoEspecifico.add(allCombates.get(i));
                }
            }

            List<CombateEntrenadores> allCombateEntrenadores = combateEntrenadoresServices.obtenerTodosLosCombateEntrenadores();
            List<CombateEntrenadores> combateEntrenadoresEspecifico = new ArrayList<>();
            int sizeCombates = 0;
            for (int i = 0; i < allCombateEntrenadores.size(); i++){
                if(allCombateEntrenadores.get(i).getCombate().getId() == allCombatesTorneoEspecifico.get(sizeCombates).getId()){
                    combateEntrenadoresEspecifico.add(allCombateEntrenadores.get(i));
                    sizeCombates++;
                }
            }

            if(combateEntrenadoresEspecifico.get(0).getIdGanador() == 0 &&
               combateEntrenadoresEspecifico.get(1).getIdGanador() == 0 &&
               combateEntrenadoresEspecifico.get(2).getIdGanador() == 0) {
                    
                if (combateEntrenadoresEspecifico.get(0).getIdEntrenador1() != 0
                        && combateEntrenadoresEspecifico.get(1).getIdEntrenador1() != 0) {
                    contadorCombatesPosibles++;
                }

                if (combateEntrenadoresEspecifico.get(0).getIdEntrenador2() != 0
                        && combateEntrenadoresEspecifico.get(2).getIdEntrenador1() != 0) {
                    contadorCombatesPosibles++;
                }

                if (combateEntrenadoresEspecifico.get(1).getIdEntrenador2() != 0
                        && combateEntrenadoresEspecifico.get(2).getIdEntrenador2() != 0) {
                    contadorCombatesPosibles++;
                }

                if (contadorCombatesPosibles != 3) {
                    System.out.println("El torneo que intenta que se pelee no tiene los participantes suficientes");
                    mostrarMenuAdministradorTorneos();
                } else {

                    Random rm = new Random();

                    Map<Long, Integer> entrenadoresVictorias = new HashMap<>();
                    entrenadoresVictorias.put(combateEntrenadoresEspecifico.get(0).getIdEntrenador1(), 0);
                    entrenadoresVictorias.put(combateEntrenadoresEspecifico.get(1).getIdEntrenador2(), 0);
                    entrenadoresVictorias.put(combateEntrenadoresEspecifico.get(2).getIdEntrenador1(), 0);
                    

                    for(int i = 0; i < combateEntrenadoresEspecifico.size(); i++){
                        int ganador = rm.nextInt(2);
                        if (ganador == 0) {
                            CombateEntrenadores actualizar = combateEntrenadoresEspecifico.get(i);
                            actualizar.setIdGanador(actualizar.getIdEntrenador1());
                            for (Entry<Long, Integer> e : entrenadoresVictorias.entrySet()) {
                                if(e.getKey() == actualizar.getIdEntrenador1()){
                                    e.setValue(e.getValue() + 1);
                                }
                            }
                            Carnet carnetEntrenadorGanador = carnetServices.obtenerCarnetPorId(actualizar.getIdEntrenador1());
                            carnetEntrenadorGanador.setNumVictorias(carnetEntrenadorGanador.getNumVictorias() + 1);
                            combateEntrenadoresServices.actualizarCombateEntrenadores(actualizar);

                        } else {
                            CombateEntrenadores actualizar = combateEntrenadoresEspecifico.get(i);
                            actualizar.setIdGanador(actualizar.getIdEntrenador2());
                            for (Entry<Long, Integer> e : entrenadoresVictorias.entrySet()) {
                                if (e.getKey() == actualizar.getIdEntrenador2()) {
                                    e.setValue(e.getValue() + 1);
                                }
                            }
                            Carnet carnetEntrenadorGanador = carnetServices.obtenerCarnetPorId(actualizar.getIdEntrenador2());
                            carnetEntrenadorGanador.setNumVictorias(carnetEntrenadorGanador.getNumVictorias() + 1);
                            combateEntrenadoresServices.actualizarCombateEntrenadores(actualizar);
                        }
                    }

                    for (Entry<Long, Integer> e : entrenadoresVictorias.entrySet()) {
                        Carnet act = carnetServices.obtenerCarnetPorId(e.getKey());
                        act.setNumVictorias(act.getNumVictorias() + e.getValue());
                        carnetServices.actualizarCarnet(act);
                    }
                    
                    
                    for (Entry<Long, Integer> e : entrenadoresVictorias.entrySet()) {
                        if(e.getValue() >= 2){
                            Carnet cGanador = carnetServices.obtenerCarnetPorId(e.getKey());
                            cGanador.setPuntos(cGanador.getPuntos() + torneoAPelear.getPuntosVictoria());
                           System.out.println("El ganador del torneo es " + entrenadorServices.obtenerEntrenadorPorId(e.getKey())
                                   .getNombre() + "!");
                            carnetServices.actualizarCarnet(cGanador);

                            try (MongoClient client = MongoDBConnection.conectar()) {
                                DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                                dao_datosTorneoMongo.insertarTorneoMongo(torneoAPelear, entrenadorServices.obtenerEntrenadorPorId(e.getKey()).getId() , UsuarioActivo.getInstancia().getId(), combateEntrenadoresEspecifico);
                                dao_datosTorneoMongo.insertarOActualizarPuntosEntrenador(entrenadorServices.obtenerEntrenadorPorId(e.getKey()).getId(), torneoAPelear.getPuntosVictoria());
                            } catch (Exception er) {
                                System.out.println(er.getMessage());
                            }
                            mostrarMenuAdministradorTorneos();
                        }
                    }

                    int gambleo = rm.nextInt(3);
                    long idGanador = -1;
                    if(gambleo == 0){
                        Carnet cGanador = carnetServices.obtenerCarnetPorId(combateEntrenadoresEspecifico.get(0).getIdEntrenador1());
                        cGanador.setPuntos(cGanador.getPuntos() + torneoAPelear.getPuntosVictoria());
                        System.out.println();
                        carnetServices.actualizarCarnet(cGanador);
                        idGanador = combateEntrenadoresEspecifico.get(0).getIdEntrenador1();
                        System.out.println("El ganador del torneo es " + entrenadorServices.obtenerEntrenadorPorId(combateEntrenadoresEspecifico.get(0).getIdEntrenador1()).getNombre() + "!");


                    } else if( gambleo == 1){
                        Carnet cGanador = carnetServices.obtenerCarnetPorId(combateEntrenadoresEspecifico.get(1).getIdEntrenador2());
                        cGanador.setPuntos(cGanador.getPuntos() + torneoAPelear.getPuntosVictoria());
                        System.out.println();
                        carnetServices.actualizarCarnet(cGanador);
                        idGanador = combateEntrenadoresEspecifico.get(1).getIdEntrenador2();
                        System.out.println("El ganador del torneo es " + entrenadorServices.obtenerEntrenadorPorId(combateEntrenadoresEspecifico.get(1).getIdEntrenador2())
                                .getNombre() + "!");

                    } else {
                        Carnet cGanador = carnetServices.obtenerCarnetPorId(combateEntrenadoresEspecifico.get(2).getIdEntrenador1());
                        cGanador.setPuntos(cGanador.getPuntos() + torneoAPelear.getPuntosVictoria());
                        System.out.println();
                        carnetServices.actualizarCarnet(cGanador);
                        idGanador = combateEntrenadoresEspecifico.get(2).getIdEntrenador1();
                        System.out.println("El ganador del torneo es " + entrenadorServices.obtenerEntrenadorPorId(combateEntrenadoresEspecifico.get(2).getIdEntrenador1())
                                .getNombre() + "!");

                    }

                    try (MongoClient client = MongoDBConnection.conectar()) {
                        DAO_DatosTorneoMongo dao_datosTorneoMongo = new DAO_DatosTorneoMongo(client);
                        dao_datosTorneoMongo.insertarTorneoMongo(torneoAPelear, idGanador, UsuarioActivo.getInstancia().getId(), combateEntrenadoresEspecifico);
                        dao_datosTorneoMongo.insertarOActualizarPuntosEntrenador(idGanador, (double)torneoAPelear.getPuntosVictoria());
                    } catch (Exception er) {
                        System.out.println("MongoDB ha fallado");
                    }
                    mostrarMenuAdministradorTorneos();
                }

            } else {
                System.out.println("Este torneo ya ha terminado");
                mostrarMenuAdministradorTorneos();
            }
    }

}
