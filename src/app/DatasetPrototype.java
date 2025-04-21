package app;

import model.Dataset;
import metrics.EuclideanDistance;
import metrics.DistanceMetric;
import classifier.KNNClassifier;
import utils.Normalizer;
import java.io.IOException;
import java.util.Scanner;

/**
 * Prototipo de aplicación para gestionar y clasificar un dataset.
 * Orquesta la lectura, normalización, modificación, guardado y clasificación.
 */
public class DatasetPrototype {

    /**
     * Método principal que ejecuta el flujo completo de la aplicación.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dataset dataset = new Dataset();

        // Lectura del dataset desde un archivo CSV
        try {
            System.out.print("Introduce el nombre del fichero CSV a leer: ");
            String filename = sc.nextLine();
            dataset.readCSV(filename, true);
            System.out.println("\nDataset leído:");
            dataset.display();
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
            System.exit(1);
        }

        // Normalización del dataset
        Normalizer normalizer = new Normalizer();
        normalizer.normalize(dataset);
        System.out.println("\nDataset normalizado:");
        dataset.display();

        // Ejemplo de modificación: modificar la primera fila (si existe)
        if (!dataset.getRows().isEmpty()) {
            String[] firstRow = dataset.getRow(0);
            firstRow[0] = "5.1";
            dataset.modifyRow(0, firstRow);
            System.out.println("\nDataset modificado:");
            dataset.display();
        }

        // Guardar el dataset modificado en un nuevo archivo CSV
        try {
            System.out.print("\nIntroduce el nombre del fichero CSV para guardar los cambios: ");
            String outputFile = sc.nextLine();
            dataset.writeCSV(outputFile);
            System.out.println("Dataset guardado en " + outputFile);
        } catch (IOException e) {
            System.err.println("Error al guardar el fichero: " + e.getMessage());
        }

        // Clasificación opcional de una nueva instancia con k-NN
        System.out.print("\n¿Quieres clasificar una nueva instancia? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            System.out.print("Introduce los atributos (separados por comas): ");
            String[] parts = sc.nextLine().split(",");
            double[] instanceAttributes = new double[parts.length];
            for (int i = 0; i < parts.length; i++) {
                instanceAttributes[i] = Double.parseDouble(parts[i].trim());
            }
            System.out.print("Introduce el valor de k: ");
            int k = Integer.parseInt(sc.nextLine());
            DistanceMetric metric = new EuclideanDistance(); // También se podría usar ManhattanDistance
            KNNClassifier classifier = new KNNClassifier(dataset, metric);
            String predictedLabel = classifier.classify(instanceAttributes, k);
            System.out.println("La clase asignada es: " + predictedLabel);
        }
        sc.close();
    }
}