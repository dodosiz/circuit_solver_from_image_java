package com.circuit_solver.examples;

import com.circuit_solver.Constants;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import net.sf.javaml.tools.InstanceTools;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;

public class GettingStarted {
    /**
     * How to create a dense instance
     */
    public static void createDenseInstance() {
        // create an array of doubles
        double[] numbers = new double[] {1, 2, 3, 4, 5};
        // the dense instance can have the array and an optional label
        Instance denseInstance = new DenseInstance(numbers);
        Instance denseInstanceWithLabel = new DenseInstance(numbers, "positive");
        System.out.println("Dense instance: " + denseInstance);
        System.out.println("Dense instance with label: " + denseInstanceWithLabel);
    }

    /**
     * How to create a sparse instance
     */
    public static void createSparseInstance() {
        // allocate a sparse instance with how many places you need
        Instance sparseInstance = new SparseInstance(10);
        // give values to some of those places
        sparseInstance.put(3, 2.0);
        sparseInstance.put(5, 4.9);
        sparseInstance.put(7, 8.3);
        System.out.println("Sparse instance: " + sparseInstance);
    }

    /**
     * How to create a data set
     */
    public static void createDataSet() {
        // we will create 10 instances with random numbers and add them to the data set
        Dataset dataset = new DefaultDataset();
        for(int i=0; i<10; i++) {
            Instance randomInstance = InstanceTools.randomInstance(3);
            randomInstance.setClassValue("random instance " + i);
            System.out.println("Random instance created: " + randomInstance);
            dataset.add(randomInstance);
        }
        System.out.println("Final data set: " + dataset);
    }

    /**
     * How to load a data set from an external file
     */
    public static void loadDataSetFromFile() {
        File datasetFile = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
        try {
            // to load a file we need the file, the index of the label and the separator character
            Dataset dataset = FileHandler.loadDataset(datasetFile, 4, ",");
            System.out.println("Iris data set:");
            System.out.println(dataset);
        } catch (IOException e) {
            System.out.println("Error opening the data set file.");
        }
    }

    /**
     * How to load a sparse data set from an external file
     */
    public static void loadSparseDataSet() {
        File datasetFile = new File(Constants.EXAMPLE_RESOURCES + "sparse.tsv");
        try {
            // to load a file we need the file, the index of the label, the attribute separator
            // and the separator between index and value
            Dataset dataset = FileHandler.loadSparseDataset(datasetFile, 0, " ", ":");
            System.out.println("Sparse data set:");
            System.out.println(dataset);
        } catch (IOException e) {
            System.out.println("Error opening the data set file.");
        }
    }

    /**
     * How to write a data set to a file
     */
    public static void writeDataToFile() {
        // first we create or read a data set from a file
        double[] evenNumbers = new double[] {2, 4, 6, 8};
        double[] oddNumbers = new double[] {1, 3, 5, 7, 9};
        Instance evenNumbersInstance = new DenseInstance(evenNumbers, "even");
        Instance oddNumbersInstance = new DenseInstance(oddNumbers, "odd");
        Dataset dataset = new DefaultDataset();
        dataset.add(evenNumbersInstance);
        dataset.add(oddNumbersInstance);
        try {
            // finally we write the data set to a file
            File outputFile = new File("out.data");
            FileHandler.exportDataset(dataset, outputFile);
        } catch (IOException e) {
            System.out.println("failed to write file");
        }
    }
}
