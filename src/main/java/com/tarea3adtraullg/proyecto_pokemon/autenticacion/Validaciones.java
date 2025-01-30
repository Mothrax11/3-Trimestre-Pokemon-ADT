package com.tarea3adtraullg.proyecto_pokemon.autenticacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tarea3adtraullg.proyecto_pokemon.complementarias.ObtenerAllServices;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.ShowNations;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.entidades.UsuarioActivo;
import com.tarea3adtraullg.proyecto_pokemon.repositorios.RepoEntrenador;
import com.tarea3adtraullg.proyecto_pokemon.services.EntrenadorServices;

@Component
public class Validaciones {
    @Autowired

    private RepoEntrenador repoEntrenador;
    private final ObtenerAllServices obtenerAllServices;

    private boolean registerOK = false;
    private String nombre;
    private String nac;
    private String pass;
    private String tipoUsr;

    @Autowired
    public Validaciones(ObtenerAllServices obtenerAllServices) {
        this.obtenerAllServices = obtenerAllServices;
    }

    // Otros constructores y métodos

    // Método que usa el servicio sin static
    public boolean creadorUsuario(String nombre, String nac, String pass, String tipoUsr) {
        if (usuarioExistente(nombre, pass)) {
            // Usuario ya existe
        } else {
            File usrs = new File("src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios", "");
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

    public boolean usuarioExistente(String nombre, String pass) {
        String buscar;
        File rd = new File("/src/main/java/com/tarea3adtraullg/proyecto_pokemon/archviosComplementarios",
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
                    if (palabrasLinea[0].equals(nombre) && palabrasLinea[1].equals(pass)) {
                        Entrenador usuario = obtenerAllServices.getService(EntrenadorServices.class).
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

    public static boolean existeNacionalidad(String nac) {
        if(ShowNations.listaPaises.contains(nac)){
            return true;
        }  
        return false;
    }

    public boolean regOK() {
        return registerOK;
    }
}
