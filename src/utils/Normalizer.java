package utils;

import model.Dataset;

/**
 * Clase para normalizar los atributos numéricos de un Dataset.
 * Calcula los mínimos y máximos de cada columna (exceptuando la etiqueta) y
 * normaliza los valores a un rango de 0 a 1.
 */
public class Normalizer {

    /**
     * Calcula los mínimos y máximos de cada columna del dataset.
     * Se asume que todas las filas tienen el mismo número de columnas y que la última columna es la etiqueta.
     * @param dataset Dataset del que se extraen los valores.
     * @return Matriz de dos filas: la primera fila son los mínimos y la segunda, los máximos.
     */
    public double[][] computeMinMax(Dataset dataset) {
        int cols = dataset.getRows().get(0).length;
        double[] min = new double[cols];
        double[] max = new double[cols];
        // Inicializar valores
        for (int i = 0; i < cols; i++) {
            min[i] = Double.MAX_VALUE;
            max[i] = -Double.MAX_VALUE;
        }
        // Calcular mínimos y máximos (se omite la última columna, que es la etiqueta)
        dataset.getRows().forEach(row -> {
            for (int i = 0; i < cols - 1; i++) {
                double val = Double.parseDouble(row[i].trim());
                min[i] = Math.min(min[i], val);
                max[i] = Math.max(max[i], val);
            }
        });
        return new double[][] { min, max };
    }

    /**
     * Normaliza los datos numéricos del dataset (excepto la columna de la etiqueta).
     * @param dataset Dataset a normalizar.
     */
    public void normalize(Dataset dataset) {
        double[][] mm = computeMinMax(dataset);
        double[] min = mm[0];
        double[] max = mm[1];
        int cols = dataset.getRows().get(0).length;
        dataset.getRows().forEach(row -> {
            for (int i = 0; i < cols - 1; i++) {
                double val = Double.parseDouble(row[i].trim());
                row[i] = String.valueOf((val - min[i]) / (max[i] - min[i]));
            }
        });
    }
}

