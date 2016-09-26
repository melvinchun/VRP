package vrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kmeans {
    private int NUM_CLUSTERS = 3;
    private int NUM_POINTS = 15;
    public ArrayList<Point> points;
    public ArrayList<Cluster> clusters;

    public Kmeans() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

    public void init() {
        points = Point.createPoints("entrada3.txt");
        NUM_CLUSTERS = Point.getTotal("entrada3.txt");
        Random randomizer = new Random();
        NUM_POINTS = points.size();
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = points.get(randomizer.nextInt(points.size()));
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
    }

    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = clusters.get(i);
            c.plotCluster();
        }
    }

    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        while (!finish) {
            clearClusters();
            ArrayList<Point> lastCentroids = getCentroids();
            assignCluster();
            calculateCentroids();
            iteration++;
            ArrayList<Point> currentCentroids = getCentroids();
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += (Point.distance(lastCentroids.get(i), currentCentroids.get(i)));
            }
            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private ArrayList<Point> getCentroids() {
        ArrayList<Point> centroids = new ArrayList(NUM_CLUSTERS);
        for (Cluster cluster : clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY(),aux.id);
            centroids.add(point);
        }
        return centroids;
    }

    private void assignCluster() {
        int max = Integer.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for (Point point : points) {
            min = max;
            for (int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for (Cluster cluster : clusters) {
            int sumX = 0;
            int sumY = 0;
            ArrayList<Point> list = cluster.getPoints();
            int n_points = list.size();

            for (Point point : list) {
                sumX += point.getX();
                sumY += point.getY();
            }

            Point centroid = cluster.getCentroid();
            if (n_points > 0) {
                int newX = sumX / n_points;
                int newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
    
    public void eraseCentroide(int i) {
        clusters.get(i).eraseCentroide();
    }
}
