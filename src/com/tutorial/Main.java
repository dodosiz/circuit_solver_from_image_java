package com.tutorial;

import com.tutorial.examples.Classification;
import net.sf.javaml.classification.Classifier;

public class Main {

    public static void main(String[] args) {
        Classification.createAndEvaluateKnnClassifier();
        Classification.crossValidate();
        Classification.createAndValidateClassifierFromWeka();
    }

}
