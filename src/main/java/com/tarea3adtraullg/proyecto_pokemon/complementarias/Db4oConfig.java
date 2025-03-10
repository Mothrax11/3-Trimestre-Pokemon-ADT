package com.tarea3adtraullg.proyecto_pokemon.complementarias;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class Db4oConfig {

    private static ObjectContainer instance;
    private static final String DB_FILE = "usuario.db4o";

    private Db4oConfig() {

    }

    public static ObjectContainer getInstance() {
        if (instance == null) {
            instance = Db4oEmbedded.openFile(DB_FILE);
        }

        return instance;
    }
}
