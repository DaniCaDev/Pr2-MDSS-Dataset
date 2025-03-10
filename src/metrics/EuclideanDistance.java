package metrics;

/**
 * Implementación de la métrica de distancia Euclidiana.
 */
public class EuclideanDistance implements DistanceMetric {

    /**
     * Calcula la distancia euclídea entre dos vectores.
     * @param a Primer vector.
     * @param b Segundo vector.
     * @return Distancia euclídea.
     */
    @Override
    public double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }
}

