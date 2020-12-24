package com.tutorial;

import com.tutorial.classification.DatasetWriter;

public class Main {

    public static void main(String[] args) {
        DatasetWriter resistorWriter = new DatasetWriter(Constants.RESISTORS, "resistor", 10);
        resistorWriter.writeFile();
    }

}
