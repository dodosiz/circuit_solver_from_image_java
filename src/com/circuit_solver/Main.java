package com.circuit_solver;

import com.circuit_solver.classification.DatasetReader;
import com.circuit_solver.classification.DatasetWriter;
import com.circuit_solver.classification.Resource;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            recognise();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void recognise() throws IOException {
        writeTrainingFile();
        Instance inputInstance = getInputInstance();
        Dataset trainingDataset = readTainingDataset();
        KNearestNeighbors classifier = new KNearestNeighbors(5);
        classifier.buildClassifier(trainingDataset);
        System.out.println(classifier.classify(inputInstance));
    }

    private static Dataset readTainingDataset() throws IOException {
        File trainingFile = new File("out\\training.data");
        return FileHandler.loadDataset(trainingFile, 0, " ");
    }

    private static Instance getInputInstance() throws IOException {
        DatasetReader datasetReader = new DatasetReader();
        return datasetReader.getInputInstance();
    }

    private static void writeTrainingFile() throws IOException {
        Resource resistors = new Resource("resistor", 10);
        Resource capacitors = new Resource("capacitor", 10);
        Resource inductors = new Resource("inductor", 10);
        List<Resource> resources = List.of(resistors, capacitors, inductors);
        DatasetWriter datasetWriter = new DatasetWriter(resources);
        datasetWriter.writeFile();
    }

}
