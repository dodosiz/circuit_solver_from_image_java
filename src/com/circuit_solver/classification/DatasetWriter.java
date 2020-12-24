package com.circuit_solver.classification;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Writes data set files from black and white images.
 */
public class DatasetWriter {
    private String resourcePath;
    private String label;
    private int numberOfImages;

    public DatasetWriter(String resourcePath, String label, int numberOfImages) {
        this.resourcePath = resourcePath;
        this.label = label;
        this.numberOfImages = numberOfImages;
    }

    public void writeFile() {
        try {
            String outputFileName = String.format("out\\%s.data", label);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));
            for (int i = 1; i <= numberOfImages; i++) {
                File resistorFile = new File(resourcePath + i + ".png");
                BufferedImage image = ImageIO.read(resistorFile);
                List<String> coordinates = getBlackCoordinates(image);
                String instance = printInstance(coordinates, label);
                bufferedWriter.write(instance);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the line with the instance data to be written in a file.
     * @param coordinates the coordinates with data to be written
     * @param label the label to describe the instance
     * @return a line representing the instance
     */
    private String printInstance(List<String> coordinates, String label) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(label + " ");
        int counter = 0;
        for (String coordinate : coordinates) {
            stringBuilder.append(coordinate + " ");
            counter++;
            if (counter > 300) {
                break;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    /**
     * Get a list of the coordinates with black color.
     * The coordinates are in the format "x.y".
     * @param image the input buffered image
     * @return the coordinates list
     */
    private List<String> getBlackCoordinates(BufferedImage image) {
        List<String> list = new ArrayList<>();
        for (int x = image.getMinX(); x < image.getWidth(); x++) {
            for (int y = image.getMinY(); y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                if (isBlack(rgb)) {
                    list.add(String.format("%d.%d", x, y));
                }
            }
        }
        return list;
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
