package metrics;

/**
 * Interfaz para definir una métrica de distancia entre dos vectores numéricos.
 */
public interface DistanceMetric {
    /**
     * Calcula la distancia entre dos vectores numéricos.
     * @param a Primer vector.
     * @param b Segundo vector.
     * @return Distancia calculada.
     */
    double distance(double[] a, double[] b);
}
