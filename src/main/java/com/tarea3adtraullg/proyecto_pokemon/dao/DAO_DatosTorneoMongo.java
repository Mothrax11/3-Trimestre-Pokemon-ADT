package com.tarea3adtraullg.proyecto_pokemon.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Combate;
import com.tarea3adtraullg.proyecto_pokemon.entidades.CombateEntrenadores;
import com.tarea3adtraullg.proyecto_pokemon.entidades.Torneo;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class DAO_DatosTorneoMongo {
    private static final String DB_NOMBRE = "tempMongoDB";
    private static final String COLLECTION_NOMBRE = "datosTorneo";
    private MongoCollection<Document> collection;

    public DAO_DatosTorneoMongo(MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collection = database.getCollection(COLLECTION_NOMBRE);
    }

    public void insertarTorneoMongo(Torneo torneo, long idGanador, long idAdmin, List<CombateEntrenadores> combateEntrenadores){
        Document torneoMongo = new Document("_id", torneo.getIdTorneo());
        torneoMongo.append("nombreTorneo", torneo.getNombre());
        torneoMongo.append("regionTorneo", torneo.getCodRegion());
        torneoMongo.append("puntosVictoriaTorneo", torneo.getPuntosVictoria());
        torneoMongo.append("idAdminTorneo", idAdmin);
        torneoMongo.append("idGanadorTorneo", idGanador);

        List<Document> listaDeCombates = new ArrayList<>();
        for (int i = 0; i < combateEntrenadores.size(); i++) {
            Document combate = new Document("_id", combateEntrenadores.get(i).getCombate().getId());

            for (int k = 0; i < torneo.getCombates().size(); k++){
                if( torneo.getCombates().get(k).getId() == combateEntrenadores.get(i).getCombate().getId()){
                    combate.append("fechaCombate", torneo.getCombates().get(i).getFecha());
                    break;
                }
            }
            combate.append("idTorneo", combateEntrenadores.get(i).getIdTorneo());

            //Combate entrenador pasado a Document
            Document combateEntrenadoresDocument = new Document("_id", torneo.getIdTorneo());
            combateEntrenadoresDocument.append("idEntrenador1", combateEntrenadores.get(i).getIdEntrenador1());
            combateEntrenadoresDocument.append("idEntrenador2", combateEntrenadores.get(i).getIdEntrenador2());
            combateEntrenadoresDocument.append("idGanador", idGanador);
            combate.append("combateEntrenador", combateEntrenadoresDocument);
            listaDeCombates.add(combate);
        }
        torneoMongo.append("combatesTorneo", listaDeCombates);
        collection.insertOne(torneoMongo);
    }

    public void listarTodosLosTorneos() {
        for (Document torneo : collection.find()) {
            System.out.println(torneo.get("_id") + " - " + torneo.getString("nombreTorneo"));
        }
    }

    public void verDatosTorneo(int idTorneo) {
        for (Document torneo: collection.find(Filters.eq("_id", idTorneo))) {
            System.out.println(torneo.toJson());
        }
    }

    public void verGanadorTorneo(long idTorneo) {
        Bson filtro = Filters.eq("_id", idTorneo);
        Document torneo = collection.find(filtro).first();
        if (torneo != null) {
            System.out.println("ID del ganador del torneo: " + torneo.get("idGanadorTorneo"));
        } else {
            System.out.println("Torneo no encontrado.");
        }
    }

    public void verPuntosEntrenador(long idEntrenador) {
        int totalPuntos = 0;
        for (Document torneo : collection.find()) {
            if (torneo.get("idGanadorTorneo", Long.class) == idEntrenador) {
                totalPuntos += torneo.getInteger("puntosVictoriaTorneo", 0);
            }
        }
        System.out.println("Puntos totales del entrenador " + idEntrenador + ": " + totalPuntos);
    }

    public void listarTorneosPorRegion(String region) {
        for (Document torneo : collection.find(Filters.eq("regionTorneo", region))) {
            System.out.println("Torneo: " + torneo.getString("nombreTorneo") + " (ID: " + torneo.get("_id") + ")");
        }
    }
}
