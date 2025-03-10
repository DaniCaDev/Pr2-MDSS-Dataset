package io;

import model.Dataset;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación para la lectura y escritura de archivos CSV.
 */
public class CSVHandler implements ICSVHandler {

    /**
     * Lee un fichero CSV y retorna un objeto Dataset.
     * @param filename Nombre del fichero CSV.
     * @param hasHeader Indica si el CSV contiene cabecera.
     * @return Dataset con los datos leídos.
     * @throws IOException Si ocurre un error al leer el fichero.
     */
    @Override
    public Dataset readCSV(String filename, boolean hasHeader) throws IOException {
        Dataset ds = new Dataset();
        List<String> lines = Files.readAllLines(Paths.get(filename));
        if (hasHeader && !lines.isEmpty()) {
            ds.setHeader(lines.get(0).split(","));
            lines.remove(0);
        }
        for (String line : lines) {
            ds.addRow(line.split(","));
        }
        return ds;
    }

    /**
     * Escribe el contenido del Dataset en un fichero CSV.
     * @param filename Nombre del fichero de salida.
     * @param dataset Dataset a escribir.
     * @throws IOException Si ocurre un error al escribir el fichero.
     */
    @Override
    public void writeCSV(String filename, Dataset dataset) throws IOException {
        List<String> lines = new ArrayList<>();
        if (dataset.getHeader() != null) {
            lines.add(String.join(",", dataset.getHeader()));
        }
        for (String[] row : dataset.getRows()) {
            lines.add(String.join(",", row));
        }
        Files.write(Paths.get(filename), lines);
    }
}

