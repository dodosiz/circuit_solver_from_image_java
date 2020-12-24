package com.circuit_solver;

import com.circuit_solver.classification.DatasetWriter;

public class Main {

    public static void main(String[] args) {
        DatasetWriter resistorWriter = new DatasetWriter(Constants.RESISTORS, "resistor", 10);
        resistorWriter.writeFile();
    }

}
