package com.circuit_solver.classification;

import com.circuit_solver.utils.Constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes data set files from black and white images.
 */
public class DatasetWriter extends DatasetBase {
    List<Resource> resources;

    public DatasetWriter(List<Resource> resouces) {
        this.resources = resouces;
    }

    public void writeFile() throws IOException {
        String outputFileName = String.format("out\\training.data");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName));
        for (Resource resource : resources) {
            for (int i = 1; i <= resource.getNumberOfImages(); i++) {
                String resourceFilePath = String.format(
                        "%s%s\\%d.png", Constants.CIRCUIT_COMPONENTS, resource.getLabel(), i);
                File resourceFile = new File(resourceFilePath);
                BufferedImage image = ImageIO.read(resourceFile);
                List<String> coordinates = getBlackCoordinates(image);
                String instance = printInstance(coordinates, resource.getLabel());
                bufferedWriter.write(instance);
            }
        }
        bufferedWriter.close();
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
        for (String coordinate : coordinates) {
            stringBuilder.append(coordinate + " ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
