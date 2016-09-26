package vrp;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Point {
    int id;
    private int x = 0;
    private int y = 0;
    static private int cluster_number = 0;
    static File file;

    public Point(int x, int y, int id) {
        this.id=id;
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setCluster(int n) {
        this.cluster_number = n;
    }

    public int getCluster() {
        return this.cluster_number;
    }

    protected static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
    }
    
    protected static ArrayList<Point> createPoints(String path) {
        file = new File(path);
        ArrayList<Point> points = new ArrayList();
        try (Scanner sc = new Scanner(file)) {
            boolean valid = false;
            int id = 0;
            while (sc.hasNextLine()) {
                if (valid) {
                    String i = sc.next();
                    String[] coordenadas = i.split(",");
                    points.add(new Point(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1]),id));
                    id++;
                } else {
                    sc.nextInt();
                    valid = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;

    }

    public String toString() {
        return x + "\t" + y;
    }

    protected static int getTotal(String path) {
        file = new File(path);
        try (Scanner sc = new Scanner(file)) {
            return sc.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
