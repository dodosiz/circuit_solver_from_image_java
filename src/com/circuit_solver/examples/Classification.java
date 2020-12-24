package com.circuit_solver.examples;

import com.circuit_solver.Constants;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.classification.evaluation.CrossValidation;
import net.sf.javaml.classification.evaluation.EvaluateDataset;
import net.sf.javaml.classification.evaluation.PerformanceMeasure;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClassifier;
import weka.classifiers.functions.SMO;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Classification {
    /**
     * How to create a K Nearest Neighbor classifier, train it from
     * a data set saved in a file and evaluate it.
     */
    public static void createAndEvaluateKnnClassifier() {
        try {
            // load a data set to train the classifier
            File datasetFile = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
            Dataset dataset = FileHandler.loadDataset(datasetFile, 4, ",");
            // create a K Nearest Neighbors classifier and train it with the data set
            Classifier knn = new KNearestNeighbors(5);
            knn.buildClassifier(dataset);
            // evaluate with the existing data set
            Map<Object, PerformanceMeasure> performanceMeasureMap = EvaluateDataset.testDataset(knn, dataset);
            System.out.println(performanceMeasureMap);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * How to cross validate a classifier.
     */
    public static void crossValidate() {
        try {
            // load the dataset to use during the cross validation
            File datasetFile = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
            Dataset dataset = FileHandler.loadDataset(datasetFile, 4, ",");
            // create a classifier
            Classifier knn = new KNearestNeighbors(5);
            // cross validate
            CrossValidation cv = new CrossValidation(knn);
            Map<Object, PerformanceMeasure> pm = cv.crossValidation(dataset);
            System.out.println(pm);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * How to use a classifier from the Weka libraries
     */
    public static void createAndValidateClassifierFromWeka() {
        try {
            File datasetFile = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
            Dataset dataset = FileHandler.loadDataset(datasetFile, 4, ",");
            SMO smo = new SMO();
            // wrapper class to use the classifier from java ml
            Classifier wekaClassifier = new WekaClassifier(smo);
            CrossValidation cv = new CrossValidation(wekaClassifier);
            Map<Object, PerformanceMeasure> pm = cv.crossValidation(dataset);
            System.out.println(pm);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
