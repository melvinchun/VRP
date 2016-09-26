package vrp;

import java.util.ArrayList;

public class Cluster {

    public ArrayList<Point> points;
    public Point centroid;
    public int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("Cluster: " + id);
        System.out.println("Centroid: " + centroid);
        System.out.println("Points: \n");
        int conteo=1;
        for (Object p : points) {
            System.out.println("Parada "+conteo+": "+p);
            conteo++;
        }
        System.out.println();
    }
    
    public void eraseCentroide() {
        for (int i = 0; i < points.size(); i++) {
            if(centroid.equals(points.get(i))){
                points.remove(i);

            }
        }
        points.add(new Point(0,0,-1));
    }
}
