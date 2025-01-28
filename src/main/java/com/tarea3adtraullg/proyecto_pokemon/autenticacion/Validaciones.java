package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.tarea3adtraullg.proyecto_pokemon.SERVICES.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.ObtenerAllServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.ShowNations;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;

public class Validaciones {
    @Autowired
    private final EntrenadorServices entrenadorServices = ObtenerAllServices.getService(EntrenadorServices.class);
    private boolean registerOK = false;
    private String nombre;
    private String nac;
    private String pass;
    private String tipoUsr;    

    public Validaciones(String nombre, String nac, String pass, String tipoUsr){
        this.nombre = nombre;
        this.nac = nac;
        this.pass = pass;
        this.tipoUsr = tipoUsr;
    }

    public Validaciones(String nombre,String pass){
        this.nombre = nombre;
        this.pass = pass;
    }

    public Validaciones(){

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
    public boolean creadorUsuario(String nombre, String nac, String pass, String tipoUsr) {
        if (usuarioExistente(nombre, pass)) {
            
        } else {
            File usrs = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios", "" );
            try {
                FileWriter fw = new FileWriter(usrs, true);
                BufferedWriter bf = new BufferedWriter(fw);
                bf.write(nombre);
                bf.write(" ");
                bf.write(pass);
                bf.write(" ");
                bf.write(tipoUsr);
                bf.write(" ");
                bf.newLine();
                bf.close();
                Entrenador nuevoEntrenador = new Entrenador(nombre, nac, pass, tipoUsr);
                
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
    public boolean usuarioExistente(String nombre, String pass) {
        String buscar;
        File rd = new File("/src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios","credenciales.txt");
        try {
            FileReader fr = new FileReader(rd);
            BufferedReader br = new BufferedReader(fr);
            if (rd.length() == 0) {
                br.close();
                return false;
            } else {
                while ((buscar = br.readLine()) != null) {
                    String[] palabrasLinea = buscar.split(" ");
                    if (palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)) {
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
    public boolean regOK() {
        return registerOK;
    }
}