package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para gestionar un dataset de datos.
 * Permite almacenar la cabecera y las filas, obtener y modificar datos, y mostrarlos.
 * También proporciona métodos para leer y escribir archivos CSV.
 */
public class Dataset {
    private String[] header;
    private final List<String[]> rows = new ArrayList<>();

    /**
     * Establece la cabecera del dataset.
     * @param header Array de nombres de columnas.
     */
    public void setHeader(String[] header) { this.header = header; }

    /**
     * Obtiene la cabecera del dataset.
     * @return Array de nombres de columnas.
     */
    public String [] getHeader() { return header; }

    /**
     * Agrega una nueva fila al dataset.
     * @param row Fila representada como array de Strings.
     */
    public void addRow(String[] row) { rows.add(row); }

    /**
     * Modifica la fila en el índice especificado.
     * @param index Índice de la fila a modificar.
     * @param newRow Nueva fila.
     */
    public void modifyRow(int index, String[] newRow) { rows.set(index, newRow); }

    /**
     * Elimina la fila en el índice especificado.
     * @param index Índice de la fila a eliminar.
     */
    public void removeRow(int index) { rows.remove(index); }

    /**
     * Obtiene la lista completa de filas.
     * @return Lista de filas.
     */
    public List<String[]> getRows() { return rows; }

    /**
     * Obtiene la fila en el índice especificado.
     * @param index Índice de la fila.
     * @return Fila como array de Strings.
     */
    public String[] getRow(int index) { return rows.get(index); }

    /**
     * Muestra el dataset en consola (cabecera y filas).
     */
    public void display() {
        if(header != null) System.out.println(String.join(",", header));
        rows.forEach(r -> System.out.println(String.join(",", r)));
    }

    /**
     * Lee un fichero CSV y carga sus datos en este dataset.
     * @param filename Nombre del fichero CSV.
     * @param hasHeader Indica si el CSV contiene cabecera.
     * @throws IOException Si ocurre un error al leer el fichero.
     */
    public void readCSV(String filename, boolean hasHeader) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        if (hasHeader && !lines.isEmpty()) {
            this.header = lines.get(0).split(",");
            lines.remove(0);
        }
        rows.clear(); // Limpiar filas existentes en caso de que el dataset ya contenga datos
        for (String line : lines) {
            addRow(line.split(","));
        }
    }

    /**
     * Escribe el contenido del Dataset en un fichero CSV.
     * @param filename Nombre del fichero de salida.
     * @throws IOException Si ocurre un error al escribir el fichero.
     */
    public void writeCSV(String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        if (header != null) {
            lines.add(String.join(",", header));
        }
        for (String[] row : rows) {
            lines.add(String.join(",", row));
        }
        Files.write(Paths.get(filename), lines);
    }
}