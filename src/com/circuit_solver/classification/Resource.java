package com.circuit_solver.classification;

/**
 * A class that contains the data for a resource type
 */
public class Resource {
    private String label;
    private int numberOfImages;

    public Resource(String label, int numberOfImages) {
        this.label = label;
        this.numberOfImages = numberOfImages;
    }

    public String getLabel() {
        return label;
    }

    public int getNumberOfImages() {
        return numberOfImages;
    }
}
