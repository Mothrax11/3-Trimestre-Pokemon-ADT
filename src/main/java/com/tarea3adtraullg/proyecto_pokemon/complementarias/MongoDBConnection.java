package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
    public static MongoClient conectar() {
        return MongoClients.create("mongodb://localhost:27017/");
    }
}
