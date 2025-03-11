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
    private static final String COLLECTION_NOMBRE_TORNEOS = "datosTorneo";
    private MongoCollection<Document> collectionDatosTorneo;
    private static final String COLLECTION_NOMBRE_USUARIOS = "datosUsuarios";
    private MongoCollection<Document> collectionUsuarios;

    public DAO_DatosTorneoMongo(MongoClient cliente) {
        MongoDatabase database = cliente.getDatabase(DB_NOMBRE);
        this.collectionDatosTorneo = database.getCollection(COLLECTION_NOMBRE_TORNEOS);
        this.collectionUsuarios = database.getCollection(COLLECTION_NOMBRE_USUARIOS);
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
        collectionDatosTorneo.insertOne(torneoMongo);
    }

    public void listarTodosLosTorneos() {
        for (Document torneo : collectionDatosTorneo.find()) {
            System.out.println(torneo.get("_id") + " - " + torneo.getString("nombreTorneo"));
        }
    }

    public void verDatosTorneo(int idTorneo) {
        for (Document torneo: collectionDatosTorneo.find(Filters.eq("_id", idTorneo))) {
            System.out.println(torneo.toJson());
        }
    }

    public void verGanadorTorneo(long idTorneo) {
        Bson filtro = Filters.eq("_id", idTorneo);
        Document torneo = collectionDatosTorneo.find(filtro).first();
        if (torneo != null) {
            System.out.println("ID del ganador del torneo: " + torneo.get("idGanadorTorneo"));
        } else {
            System.out.println("Torneo no encontrado.");
        }
    }

    public void verPuntosEntrenador(long idEntrenador) {
        double totalPuntos = 0;

        for (Document torneo : collectionDatosTorneo.find()) {
            Long idGanador = torneo.getLong("idGanadorTorneo");

            if (idGanador == idEntrenador) {
                Object puntosVictoriaObj = torneo.get("puntosVictoriaTorneo");
                totalPuntos += (Double) puntosVictoriaObj;
            }
        }
        System.out.println("Puntos totales del entrenador " + idEntrenador + ": " + totalPuntos);
    }

    public void listarTorneosPorRegion(String region) {
        for (Document torneo : collectionDatosTorneo.find(Filters.eq("regionTorneo", region))) {
            System.out.println("Torneo: " + torneo.getString("nombreTorneo") + " (ID: " + torneo.get("_id") + ")");
        }
    }

    public void obtenerTop2Entrenadores() {
        long entrenador1 = 0;
        long entrenador2 = 0;
        int victoriasEntrenador1 = 0;
        int victoriasEntrenador2 = 0;
        long entrenadorPotencial = 0;
        int victoriasEntrenadorPotencial = 0;

        for (Document torneo : collectionDatosTorneo.find()) {
            long idGanador = torneo.getLong("idGanadorTorneo");

            if (idGanador == entrenador1) {
                victoriasEntrenador1++;
            } else if (idGanador == entrenador2) {
                victoriasEntrenador2++;
            } else {
                // Si el ganador no está entre los dos primeros, lo comparamos con el potencial
                if (victoriasEntrenador1 < victoriasEntrenador2) {
                    if (victoriasEntrenadorPotencial > victoriasEntrenador2) {
                        // Si el entrenador potencial tiene más victorias que el segundo, lo adelanta
                        entrenador2 = entrenadorPotencial;
                        victoriasEntrenador2 = victoriasEntrenadorPotencial;
                    }
                    entrenador1 = idGanador;
                    victoriasEntrenador1 = 1;
                } else {
                    if (victoriasEntrenadorPotencial > victoriasEntrenador1) {
                        // Si el entrenador potencial tiene más victorias que el primero, lo adelanta
                        entrenador1 = entrenadorPotencial;
                        victoriasEntrenador1 = victoriasEntrenadorPotencial;
                    }
                    entrenador2 = idGanador;
                    victoriasEntrenador2 = 1;
                }
            }
            if (idGanador != entrenador1 && idGanador != entrenador2) {
                if (victoriasEntrenadorPotencial < victoriasEntrenador1 && victoriasEntrenadorPotencial < victoriasEntrenador2) {
                    entrenadorPotencial = idGanador;
                    victoriasEntrenadorPotencial = 1;
                }
            }
        }
        if (victoriasEntrenador1 >= victoriasEntrenador2) {
            System.out.println("Entrenador 1 ID: " + entrenador1 + " - Torneos ganados: " + victoriasEntrenador1);
            System.out.println("Entrenador 2 ID: " + entrenador2 + " - Torneos ganados: " + victoriasEntrenador2);
        } else {
            System.out.println("Entrenador 1 ID: " + entrenador2 + " - Torneos ganados: " + victoriasEntrenador2);
            System.out.println("Entrenador 2 ID: " + entrenador1 + " - Torneos ganados: " + victoriasEntrenador1);
        }
    }

    public void insertarOActualizarPuntosEntrenador(long idEntrenador, double puntos) {
        MongoCollection<Document> nuevaColeccion = collectionUsuarios;
        Document entrenadorExistente = nuevaColeccion.find(Filters.eq("_id", idEntrenador)).first();

        if (entrenadorExistente != null) {
            double puntosExistentes = entrenadorExistente.getDouble("puntosTotales");
            double puntosActualizados = puntosExistentes + puntos;

            Bson filtro = Filters.eq("_id", idEntrenador);
            Bson actualizacion = new Document("$set", new Document("puntosTotales", puntosActualizados));
            nuevaColeccion.updateOne(filtro, actualizacion);
        } else {
            Document nuevoEntrenador = new Document("_id", idEntrenador).append("puntosTotales", puntos);
            nuevaColeccion.insertOne(nuevoEntrenador);
        }
    }

    public void listarEntrenadoresYPuntos() {
        MongoCollection<Document> nuevaColeccion = collectionUsuarios;

        for (Document entrenador : nuevaColeccion.find()) {
            long idEntrenador = entrenador.getLong("_id");
            double puntosTotales = entrenador.getDouble("puntosTotales");

            System.out.println("Entrenador ID: " + idEntrenador + " - Puntos: " + puntosTotales);
        }
    }
}
