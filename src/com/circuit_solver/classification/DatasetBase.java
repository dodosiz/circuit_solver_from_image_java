package com.circuit_solver.classification;

import com.circuit_solver.utils.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DatasetBase {
    /**
     * Get a list of the coordinates with black color.
     * The coordinates are in the format "x.y".
     * @param image the input buffered image
     * @return the coordinates list
     */
    protected List<String> getBlackCoordinates(BufferedImage image) {
        List<String> list = new ArrayList<>();
        for (int x = image.getMinX(); x < image.getWidth(); x++) {
            for (int y = image.getMinY(); y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                if (isBlack(rgb)) {
                    list.add(String.format("%d.%d", x, y));
                }
            }
        }
        return reduceSamples(list);
    }

    /**
     * Reduce the amount of samples in a list based on how many times
     * bigger than the max instance size the list is.
     * @param fullList the full list to reduce
     * @return the reduced list
     */
    private List<String> reduceSamples(List<String> fullList) {
        int reductionRate = fullList.size() / Constants.MAX_INSTANCE_SIZE;
        List<String> reducedList = new ArrayList<>();
        int counter = 1;
        for (String sample : fullList) {
            if (reductionRate == 0 || counter % reductionRate == 0) {
                reducedList.add(sample);
            }
            counter++;
        }
        return reducedList;
    }

    /**
     * Check if an rgb value is black
     * @param rgb the rgb value
     * @return is the value black or not
     */
    private boolean isBlack(int rgb) {
        Color color = new Color(rgb, true);
        return color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0;
    }
}
