package com.circuit_solver;

import com.circuit_solver.classification.ClassificationExcecutor;
import com.circuit_solver.docs.Clustering;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            ClassificationExcecutor.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
