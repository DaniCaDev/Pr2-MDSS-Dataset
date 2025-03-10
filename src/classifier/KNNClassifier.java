package classifier;

import model.Dataset;
import metrics.DistanceMetric;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clasificador basado en el algoritmo k-NN.
 * Utiliza un dataset de entrenamiento y una métrica de distancia para clasificar nuevas instancias.
 */
public class KNNClassifier {
    private final Dataset trainingSet;
    private final DistanceMetric metric;

    /**
     * Constructor del clasificador.
     * @param trainingSet Conjunto de entrenamiento.
     * @param metric Métrica de distancia a utilizar.
     */
    public KNNClassifier(Dataset trainingSet, DistanceMetric metric) {
        this.trainingSet = trainingSet;
        this.metric = metric;
    }

    /**
     * Clasifica una nueva instancia utilizando el algoritmo k-NN.
     * Se asume que en cada fila del dataset, los primeros n-1 elementos son atributos numéricos
     * y el último es la etiqueta.
     * @param newInstance Array con los atributos numéricos de la instancia a clasificar.
     * @param k Número de vecinos a considerar.
     * @return Etiqueta asignada tras la clasificación.
     */
    public String classify(double[] newInstance, int k) {
        List<Neighbor> neighbors = new ArrayList<>();
        trainingSet.getRows().forEach(row -> {
            int n = row.length;
            double[] features = new double[n - 1];
            for (int i = 0; i < n - 1; i++) {
                features[i] = Double.parseDouble(row[i].trim());
            }
            double dist = metric.distance(newInstance, features);
            neighbors.add(new Neighbor(dist, row[n - 1]));
        });
        // Ordenar vecinos según distancia
        neighbors.sort(Comparator.comparingDouble(neighbor -> neighbor.distance));
        // Tomar los k vecinos más cercanos
        List<Neighbor> kNeighbors = neighbors.subList(0, Math.min(k, neighbors.size()));
        // Determinar la etiqueta mayoritaria
        Map<String, Long> frequency = kNeighbors.stream()
                .collect(Collectors.groupingBy(neighbor -> neighbor.label, Collectors.counting()));
        return frequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }

    /**
     * Clase interna para representar un vecino con su distancia y etiqueta.
     */
    private static class Neighbor {
        double distance;
        String label;

        Neighbor(double distance, String label) {
            this.distance = distance;
            this.label = label;
        }
    }
}

