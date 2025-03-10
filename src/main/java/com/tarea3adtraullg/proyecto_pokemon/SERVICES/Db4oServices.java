package com.tarea3adtraullg.proyecto_pokemon.services;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import com.tarea3adtraullg.proyecto_pokemon.complementarias.Db4oConfig;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Carnet;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Entrenador;
import com.tarea3adtraullg.proyecto_pokemon.entidades.UsuarioActivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Db4oServices {
    ObjectContainer db;

    @Autowired
    EntrenadorServices entrenadorServices;

    @Autowired
    CarnetServices carnetServices;

    public Db4oServices (){
        db = Db4oConfig.getInstance();
    }

    public void crearEntrenadorDB4O(Entrenador entrenador) {

        try {
            Query query = db.query();
            query.constrain(Entrenador.class);
            query.descend("nombre").constrain(entrenador.getNombre());

            if(query.execute().isEmpty()){
                db.store(entrenador);

                if (entrenador != null) {
                    UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                    usuarioActivo.setId(entrenador.getId());
                    usuarioActivo.setNombre(entrenador.getNombre());
                    usuarioActivo.setNacionalidad(entrenador.getNacionalidad());
                    usuarioActivo.setContrasena(entrenador.getContrasena());
                    usuarioActivo.setFechaCreacion(entrenador.getFechaCreacion());
                    usuarioActivo.setTipoUsr(entrenador.getTipoUsr());
                    usuarioActivo.setCarnet(entrenador.getCarnet());
                    System.out.println("Usuario registrado con éxito");
                }

            } else {
                System.out.println("Error en la creación del usuario: Ya existe");
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void listarEntrenadoresDB4O(){
        try {
            Query query = db.query();
            query.constrain(Entrenador.class);
            List<Entrenador> listaEntrenadores = query.execute();

            for (int i = 0; i < listaEntrenadores.size(); i++){
                System.out.println((i + 1) + " - " + listaEntrenadores.get(i).getNombre());
            }
        } catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean usuarioExistente(String nombre, String pass) {
        boolean encontradoEnArchivo = false;
        boolean encontradoEnBD = false;

        Query query = db.query();
        query.constrain(Entrenador.class);
        query.descend("nombre").constrain(nombre);
        query.descend("password").constrain(pass);
        encontradoEnBD = !query.execute().isEmpty();


        if (encontradoEnBD) {
            Entrenador entrenador = entrenadorServices.buscarPorNombreYContrasena(nombre, pass);
            if (entrenador != null) {
                UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                usuarioActivo.setId(entrenador.getId());
                usuarioActivo.setNombre(entrenador.getNombre());
                usuarioActivo.setNacionalidad(entrenador.getNacionalidad());
                usuarioActivo.setContrasena(entrenador.getContrasena());
                usuarioActivo.setFechaCreacion(entrenador.getFechaCreacion());
                usuarioActivo.setTipoUsr(entrenador.getTipoUsr());
                usuarioActivo.setCarnet(entrenador.getCarnet());

                System.out.println("Usuario validado en base de datos: " + usuarioActivo.getNombre());
                System.out.println("Se iniciará sesión.");
            }
        }

        return encontradoEnBD;
    }

    public boolean comprobarUsuario(String nombre, String pass, String rol) {
        try {
            Query query = db.query();
            query.constrain(Entrenador.class);
            query.descend("nombre").constrain(nombre);
            query.descend("password").constrain(pass);
            if (!query.execute().isEmpty()) {
                Entrenador usuario = entrenadorServices.buscarPorNombreYContrasena(nombre, pass);
                if (usuario != null) {
                    UsuarioActivo usuarioActivo = UsuarioActivo.getInstancia();
                    usuarioActivo.setId(usuario.getId());
                    usuarioActivo.setNombre(usuario.getNombre());
                    usuarioActivo.setContrasena(usuario.getContrasena());
                    usuarioActivo.setFechaCreacion(usuario.getFechaCreacion());
                    usuarioActivo.setTipoUsr(usuario.getTipoUsr());
                    usuarioActivo.setCarnet(usuario.getCarnet());
                    //writeLog(nombre);
                    return true;
                } else {
                    System.out.println("Usuario no encontrado.");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public void eliminarUsuarioDB4O(String nombre, String password){
        Query query = db.query();
        query.constrain(Entrenador.class);
        query.descend("nombre").constrain(nombre);
        query.descend("password").constrain(password);
        ObjectSet<Entrenador> resultados = query.execute();

        if (!resultados.isEmpty()) {
            Entrenador usuario = resultados.next();
            db.delete(usuario);
            System.out.println("Usuario " + nombre + " eliminado con exito.");
        } else {
            System.out.println("Usuario no encontrado en la bd.");
        }
    }

    public void cambiarPassword(String nombre, String password, String newPassword){
        Query query = db.query();
        query.constrain(Entrenador.class);
        query.descend("nombre").constrain(nombre);
        query.descend("password").constrain(password);
        ObjectSet<Entrenador> resultados = query.execute();

        if (!resultados.isEmpty()) {
            Entrenador usuario = resultados.next();
            usuario.setPassword(newPassword);
            entrenadorServices.actualizarEntrenador(usuario.getId(), usuario);
            db.store(usuario);
            System.out.println("La contraseña de " + nombre + " ha sido actualizada con exito.");
        } else {
            System.out.println("Error al actualizar la contraseña.");
        }
    }
}


