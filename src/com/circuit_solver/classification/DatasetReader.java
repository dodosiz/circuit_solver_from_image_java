package com.circuit_solver.classification;

import com.circuit_solver.Constants;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Reads the data set from input black and white images
 */
public class DatasetReader extends DatasetBase {
    public Instance getInputInstance() throws IOException {
        File resourceFile = new File(Constants.INPUT_IMAGE);
        BufferedImage image = ImageIO.read(resourceFile);
        List<String> coordinates = getBlackCoordinates(image);
        double[] values = new double[coordinates.size()];
        int i = 0;
        for (String coordinate : coordinates) {
            values[i] = Double.parseDouble(coordinate);
            i++;
        }
        return new DenseInstance(values);
    }
}
