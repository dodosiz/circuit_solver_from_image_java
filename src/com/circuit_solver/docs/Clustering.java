package com.circuit_solver.docs;

import com.circuit_solver.utils.Constants;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.tools.weka.WekaClusterer;
import weka.clusterers.XMeans;

import java.io.File;
import java.io.IOException;

public class Clustering {
    /**
     * How to cluster a data set
     */
    public static void clusterDataset() throws IOException {
        File file = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
        Dataset dataset = FileHandler.loadDataset(file, 4, ",");
        Clusterer kmeans = new KMeans();
        Dataset[] clusters = kmeans.cluster(dataset);
        System.out.println("Clusters:");
        for (Dataset cluster : clusters) {
            System.out.println(cluster);
        }
        ClusterEvaluation clusterEvaluation = new SumOfSquaredErrors();
        double score = clusterEvaluation.score(clusters);
        System.out.println("Evaluation: " + score);
    }

    /**
     * How to use the weka clusterer
     * @throws IOException
     */
    public static void wekaClusterer() throws IOException {
        File file = new File(Constants.EXAMPLE_RESOURCES + "iris.data");
        Dataset dataset = FileHandler.loadDataset(file, 4, ",");
        XMeans xMeans = new XMeans();
        Clusterer clusterer = new WekaClusterer(xMeans);
        Dataset[] clusters = clusterer.cluster(dataset);
        System.out.println("Clusters:");
        for (Dataset cluster : clusters) {
            System.out.println(cluster);
        }
        ClusterEvaluation clusterEvaluation = new SumOfSquaredErrors();
        double score = clusterEvaluation.score(clusters);
        System.out.println("Evaluation: " + score);
    }
}
