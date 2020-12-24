package com.circuit_solver;

import com.circuit_solver.classification.DatasetWriter;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.CrossValidation;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        recognise();
    }

    private static void recognise() {
        try {
            // create training and input datasets
            DatasetWriter resistorWriter = new DatasetWriter(Constants.RESISTORS, "resistor", 10);
            resistorWriter.writeFile();
            DatasetWriter inputWriter = new DatasetWriter("out\\", "input", 1);
            inputWriter.writeFile();
            // create and train classifier
            File trainingFile = new File("out\\resistor.data");
            Dataset trainingDataset = FileHandler.loadDataset(trainingFile, 0, " ");
            Classifier knn = new KNearestNeighbors(500);
            knn.buildClassifier(trainingDataset);
            // classify input
            File inputFile = new File("out\\input.data");
            Dataset inputDataset = FileHandler.loadDataset(inputFile, 0, " ");
            for (Instance instance : inputDataset) {
                System.out.println("Classification: " + knn.classify(instance));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
