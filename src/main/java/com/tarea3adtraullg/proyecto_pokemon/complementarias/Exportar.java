package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;

import com.tarea3adtraullg.proyecto_pokemon.SERVICES.CarnetServices;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.CombateEntrenadoresServices;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.EntrenadorServices;
import com.tarea3adtraullg.proyecto_pokemon.SERVICES.TorneoServices;
import com.tarea3adtraullg.proyecto_pokemon.entidades.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de exportar la información de un entrenador en un formato
 * XML.
 * La información exportada incluye el id del entrenador, fecha de creación,
 * puntos,
 * nacionalidad, torneos en los que ha participado, y otros datos relevantes.
 * 
 * @author raullg97
 */
@Component
public class Exportar {

    @Autowired
    CarnetServices carnetServices;

    @Autowired
    UsuarioActivo usuarioActivo;

    @Autowired
    EntrenadorServices entrenadorServices;

    @Autowired
    TorneoServices torneoServices;

    @Autowired
    CombateEntrenadoresServices combateEntrenadoresServices;
    

    /**
     * Constructor que inicializa los valores del objeto Exportar a partir de un
     * objeto Entrenador.
     * 
     * @param entrenador El objeto Entrenador del que se exportarán los datos.
     */

    public Exportar() {
        
    }

    /**
     * Método que ejecuta la creación del archivo XML. Llama a la función para crear
     * el XML
     * después de inicializar la configuración necesaria.
     */
    public void ejecutar() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = factory.newDocumentBuilder();
            DOMImplementation domImplementation = docBuilder.getDOMImplementation();
            Document document = domImplementation.createDocument(null, "carnet", null);
            Entrenador entrenadorAExportar = entrenadorServices.obtenerEntrenadorPorId(usuarioActivo.getId());
            crearXML(document, entrenadorAExportar);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crea el archivo XML con los datos del entrenador y los torneos en los que ha
     * participado.
     * 
     * @param doc El objeto Document que contiene el documento XML.
     */
    public void crearXML(Document doc, Entrenador entrenador) { 
        doc.setXmlVersion("1.0");

        LocalDate ld = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateString = ld.format(dateTimeFormatter);
        String dateStrinUsr = UsuarioActivo.getInstancia().getFechaCreacion();

        // Crear el elemento ID del entrenador en el documento XML
        crearElemento("id", Long.toString(UsuarioActivo.getInstancia().getId()), doc, doc.getDocumentElement());
        // Crear el elemento fecha de expiración
        crearElemento("fechaexp", dateString, doc, doc.getDocumentElement());

        // Crear el nodo del entrenador
        Element elementEntrenador = doc.createElement("entrenador");
        doc.getDocumentElement().appendChild(elementEntrenador);
        crearElemento("nombre", UsuarioActivo.getInstancia().getNombre(), doc, elementEntrenador);
        crearElemento("nacionalidad", UsuarioActivo.getInstancia().getNacionalidad(), doc, elementEntrenador);
        crearElemento("hoy", dateStrinUsr, doc, elementEntrenador);
        crearElemento("puntos", Float.toString(carnetServices.obtenerCarnetPorId(UsuarioActivo.getInstancia().getId()).getPuntos()), doc, elementEntrenador);

        // Crear el nodo de torneos
        Element elementoTorneos = doc.createElement("torneos");
        doc.getDocumentElement().appendChild(elementoTorneos);

        List<Torneo> torneos = torneoServices.buscarTorneosPorIdEntrenadorParticipante(UsuarioActivo.getInstancia().getId());
        System.out.println(torneos.size());
        for (int i = 0; i < torneos.size(); i++) {
            // Crear elemento para el torneo
            Element elementoTorneo = doc.createElement("torneo");
            elementoTorneos.appendChild(elementoTorneo);
            crearElemento("nombre", torneos.get(i).getNombre(), doc, elementoTorneo);
            String regionTorneo = Character.toString(torneos.get(i).getCodRegion());
            crearElemento("region", regionTorneo, doc, elementoTorneo);

            // Crear nodo contenedor para los combates del torneo
            Element elementoCombates = doc.createElement("combates");
            elementoTorneo.appendChild(elementoCombates);

            // Iterar sobre la lista de combates del torneo
            List<Combate> combates = torneos.get(i).getCombates();
            if (combates != null) {
                for (Combate combate : combates) {
                    Element elementoCombate = doc.createElement("combate");
                    elementoCombates.appendChild(elementoCombate);
                    crearElemento("id", String.valueOf(combate.getId()), doc, elementoCombate);
                    crearElemento("fecha", combate.getFecha().toString(), doc, elementoCombate);
                    List<CombateEntrenadores> combateEntrenadores = combateEntrenadoresServices.obtenerTodosLosCombateEntrenadores();
                    List<CombateEntrenadores> combatesDelTorneo = new ArrayList<>();

                    for(int k = 0; k < combateEntrenadores.size(); k++){
                        if(combateEntrenadores.get(k).getCombate().getId() == combate.getId()){
                            combatesDelTorneo.add(combateEntrenadores.get(k));
                        }
                    }

                    for(int k = 0; k < combatesDelTorneo.size(); k++){
                        if(combatesDelTorneo.get(i).getIdGanador() == UsuarioActivo.getInstancia().getId()){
                            crearElemento("resultado", "Victoria", doc, elementoCombate);
                        } else {
                            crearElemento("resultado", "Derrota/No participado", doc, elementoCombate);
                        }
                    }
                }
            }
        }

        // Guardar el archivo XML
        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File(UsuarioActivo.getInstancia().getNombre() + ".xml"));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método auxiliar que crea un elemento dentro del documento XML con un nombre
     * dado y su valor.
     * 
     * @param dato     El nombre del elemento XML.
     * @param valor    El valor que tendrá el elemento XML.
     * @param document El objeto Document donde se insertará el nuevo elemento.
     * @param raiz     El nodo raíz donde se insertará el nuevo elemento.
     */
    private void crearElemento(String dato, String valor, Document document, Element raiz) {
        Element element = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(element);
        element.appendChild(text);
    }
}
