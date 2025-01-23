package com.tarea3adtraullg.proyecto_pokemon.complementarias;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase encargada de gestionar el contenido de un archivo XML.
 * Extiende de {@link DefaultHandler} y sobrecarga el método
 * {@link #characters(char[], int, int)}
 * para procesar los caracteres del archivo XML mientras se analiza su
 * contenido.
 * Los caracteres leídos se agregan a la lista de países y se
 * imprimen en consola.
 * 
 * @author raullg97
 */
public class GestorDeContenidos extends DefaultHandler {

    /**
     * Método que maneja los caracteres leídos del archivo XML.
     * Este método es llamado durante el proceso de análisis del XML.
     * Los caracteres leídos se agregan a la lista de países
     * {@link ShowNations#listaPaises}
     * y se imprimen en consola.
     *
     * @param ch     el array de caracteres leídos
     * @param start  la posición inicial en el array de caracteres
     * @param length la longitud de los caracteres a procesar
     * @throws SAXException en caso de errores durante el procesamiento de los
     * caracteres
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // Convierte el array de caracteres en una cadena de texto
        String caracteres = new String(ch, start, length);

        // Agrega los caracteres leídos a la lista de países
        ShowNations.listaPaises.add(caracteres);

        // Imprime los caracteres en la consola
        System.out.print(caracteres);
    }
}
