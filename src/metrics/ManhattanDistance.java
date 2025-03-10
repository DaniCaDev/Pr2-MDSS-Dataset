package metrics;

/**
 * Implementación de la métrica de distancia Manhattan.
 */
public class ManhattanDistance implements DistanceMetric {

    /**
     * Calcula la distancia Manhattan entre dos vectores.
     * @param a Primer vector.
     * @param b Segundo vector.
     * @return Distancia Manhattan.
     */
    @Override
    public double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }
}

