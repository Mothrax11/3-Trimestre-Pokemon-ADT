package com.tarea3adtraullg.proyecto_pokemon.complementarias;

import org.w3c.dom.*;
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

/**
 * Clase encargada de exportar la información de un entrenador en un formato
 * XML.
 * La información exportada incluye el id del entrenador, fecha de creación,
 * puntos,
 * nacionalidad, torneos en los que ha participado, y otros datos relevantes.
 * 
 * @author raullg97
 */
public class Exportar {

    private String nombre;
    private String nacionalidad;
    private long puntos;
    private String fechaCreacion;
    private ArrayList<Torneo> torneos = new ArrayList<>();
    private long idEntrenador;
    private Entrenador entrenador;

    /**
     * Constructor que inicializa los valores del objeto Exportar a partir de un
     * objeto Entrenador.
     * 
     * @param entrenador El objeto Entrenador del que se exportarán los datos.
     */
    public Exportar(Entrenador entrenador) {
        this.entrenador = entrenador;
        this.nombre = entrenador.getNombre();
        this.idEntrenador = entrenador.getId();
        this.nacionalidad = entrenador.getNacionalidad();
        //this.puntos = entrenador.getPuntos();
        this.fechaCreacion = entrenador.getFechaCreacion();
        //this.torneos = entrenador.getTorneos();
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
            crearXML(document);
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
    public void crearXML(Document doc) {
        doc.setXmlVersion("1.0");

        LocalDate ld = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        String dateString = ld.format(dateTimeFormatter);
        String dateStrinUsr = fechaCreacion;

        // Crear el elemento ID del entrenador en el documento XML
        crearElemento("id", Long.toString(getIdEntrenador()), doc, doc.getDocumentElement());
        // Crear el elemento fecha de expiración
        crearElemento("fechaexp", dateString, doc, doc.getDocumentElement());

        // Crear el nodo del entrenador
        Element elementEntrenador = doc.createElement("entrenador");
        doc.getDocumentElement().appendChild(elementEntrenador);
        crearElemento("nombre", nombre, doc, elementEntrenador);
        crearElemento("nacionalidad", nacionalidad, doc, elementEntrenador);
        crearElemento("hoy", dateStrinUsr, doc, elementEntrenador);
        crearElemento("puntos", Long.toString(puntos), doc, elementEntrenador);

        // Crear el nodo de torneos
        Element elementoTorneos = doc.createElement("torneos");
        doc.getDocumentElement().appendChild(elementoTorneos);

        /*
        for (int i = 0; i < entrenador.getTorneos().size(); i++) {
            Element elementoTorneo = doc.createElement("torneo");
            elementoTorneos.appendChild(elementoTorneo);
            crearElemento("nombre", torneos.get(i).getNombre(), doc, elementoTorneo);
            String regionTorneo = Character.toString(torneos.get(i).getCodRegion());
            crearElemento("region", regionTorneo, doc, elementoTorneo);
        }
         */

        // Guardar el archivo XML
        Source source = new DOMSource(doc);
        Result result = new StreamResult(new File(nombre + ".xml"));

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
    private static void crearElemento(String dato, String valor, Document document, Element raiz) {
        Element element = document.createElement(dato);
        Text text = document.createTextNode(valor);
        raiz.appendChild(element);
        element.appendChild(text);
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public long getPuntos() {
        return puntos;
    }

    public void setPuntos(long puntos) {
        this.puntos = puntos;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public ArrayList<Torneo> getTorneos() {
        return torneos;
    }

    public void setTorneos(ArrayList<Torneo> torneos) {
        this.torneos = torneos;
    }

    public long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }
}
