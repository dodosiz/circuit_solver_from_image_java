package com.circuit_solver.classification;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClassifier;
import weka.classifiers.functions.SMO;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ClassificationExcecutor {
    /**
     * Runs the classification for the circuit component depicted in input.png
     * @throws IOException
     */
    public static void run() throws IOException {
        SMO smo = new SMO();
        Classifier weka = new WekaClassifier(smo);
        withClassifier(weka);
    }

    private static void withClassifier(Classifier classifier) throws IOException {
        writeTrainingFile();
        Instance inputInstance = getInputInstance();
        Dataset trainingDataset = readTainingDataset();
        classifier.buildClassifier(trainingDataset);
        Map<Object, PerformanceMeasure> performanceMeasureMap = EvaluateDataset.testDataset(classifier, trainingDataset);
        System.out.println("CLASSIFICATION RESULT");
        System.out.println("---------------------");
        System.out.println(classifier.classify(inputInstance) + "\n");
        System.out.println("PERFORMANCE ANALYSIS");
        System.out.println("---------------------");
        System.out.println(performanceMeasureMap);
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
        Resource voltageSources = new Resource("voltage_source", 5);
        List<Resource> resources = List.of(resistors, voltageSources);
        DatasetWriter datasetWriter = new DatasetWriter(resources);
        datasetWriter.writeFile();
    }
}
