package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.tarea3adtraullg.proyecto_pokemon.entidades.*;

public class ArchivadorTorneos {
    public static void Archivador (Torneo torneo){
        File file = new File("src\\main\\java\\com\\tarea3adtraullg\\proyecto_pokemon\\archviosComplementarios\\torneos.dat");

        try {
            ObjectOutputStream oou = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(file, true)));
            oou.writeObject(torneo);
            oou.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
