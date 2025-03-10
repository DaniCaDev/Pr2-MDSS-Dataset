package io;

import model.Dataset;
import java.io.IOException;

/**
 * Interfaz para la lectura y escritura de archivos CSV.
 */
public interface ICSVHandler {
    /**
     * Lee un fichero CSV y retorna un objeto Dataset.
     * @param filename Nombre del fichero CSV.
     * @param hasHeader Indica si el CSV contiene cabecera.
     * @return Dataset con los datos leídos.
     * @throws IOException Si ocurre un error al leer el fichero.
     */
    Dataset readCSV(String filename, boolean hasHeader) throws IOException;

    /**
     * Escribe el contenido del Dataset en un fichero CSV.
     * @param filename Nombre del fichero de salida.
     * @param dataset Dataset a escribir.
     * @throws IOException Si ocurre un error al escribir el fichero.
     */
    void writeCSV(String filename, Dataset dataset) throws IOException;
}

