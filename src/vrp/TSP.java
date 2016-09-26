/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp;

import static java.lang.Math.toRadians;
import java.io.BufferedReader;
import java.io.FileReader;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Arrays;

public class TSP {

    static int size;
    static double[][] distances;
    static ArrayList<Point> paradas;

    public TSP(ArrayList<Point> paradas) {
        this.paradas = paradas;
        this.size = paradas.size();
        distances = new double[size][size];
    }

    public int[] solveMe() throws Exception {
        double[][] coords = new double[size][size];
        for (int i = 0; i < paradas.size(); i++) {
            coords[i][0] = paradas.get(i).getX();
            coords[i][1] = paradas.get(i).getY();
        }

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                distances[i][j] = haversin(coords[i][0], coords[j][0], coords[i][1], coords[j][1]);
                distances[j][i] = distances[i][j];
            }
        }
        int[] shortestPath = nearestNeighbour(distances);
        return shortestPath;
    }
    
    public String print(int[] shortestPath){
        System.out.println("Real distance: " + getRealDistance(shortestPath));
        String ss=printPath(shortestPath)+"Real distance: " + getRealDistance(shortestPath);
        return ss;
    }

    public int[] nearestNeighbour(double[][] distances) {
        boolean[] copy = new boolean[size];
        int[] shortestPath = new int[size];
        int current = 0;
        double bestDistance = Double.MAX_VALUE;

        // nearest neighbour thingy
        int town = current;
        for (int a = 0; a < size; a++) {
            // reset distance array
            Arrays.fill(copy, true);
            double shortest = 0, dist = 0;
            int[] temp = new int[size];
            int index = 0;
            temp[index++] = a + 1;
            current = a;

            for (int c = 0; c < size - 1; c++) {
                shortest = Double.MAX_VALUE; // reset closest

                for (int i = 0; i < size; i++) {
                    if (i == current) {
                        continue;
                    }
                    if (copy[i] && (distances[current][i] < shortest)) {
                        town = i;
                        shortest = distances[current][i];
                    }
                }

                copy[current] = false;
                temp[index++] = town + 1;
                current = town;
                dist += shortest;
            }

            dist += distances[temp[index - 1] - 1][temp[0] - 1];
            if (dist < bestDistance) {
                shortestPath = Arrays.copyOf(temp, temp.length);
                bestDistance = dist;
            }
        }
        return shortestPath;
    }

    public double haversin(double x1, double x2, double y1, double y2) {

        // difference between x and y co-ords
        double differenceInX = toRadians(x2 - x1);
        double differenceInY = toRadians(y2 - y1);

        // convert to radians
        // angle / 180.0 * PI
        x1 = toRadians(x1);
        x2 = toRadians(x2);

        // 2r is a constant, and presuming the radius is 6371lm
        //return 12742.0 * asin(sqrt(sin(differenceInX / 2) * sin(differenceInX / 2) + sin(differenceInY / 2) * sin(differenceInY / 2) * cos(x1) * cos(x2)));
        return getDistancia(x1, x2, y1, y2);
    }

    static public String printPath(int[] path) {
        String ss=paradas.size()+"\n";
        for (int i = 0; i < size - 1; ++i) {
            System.out.print(path[i] + " -> ");
            ss+=paradas.get(path[i]-1).id+"\n";
        }
        System.out.println(path[size - 1]);
        ss+=path[size - 1]+"\n";
        return ss;
    }

    public String[] load(String path) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(path));
        StringBuilder contents = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            contents.append(line).append(System.getProperty("line.separator"));
        }
        in.close();
        return contents.toString().split(System.getProperty("line.separator"));
    }

    static public double getRealDistance(int[] shortestPath) {
        double conteo = 0;
        for (int i = 0; i < shortestPath.length - 1; i++) {
            conteo += getDistancia(shortestPath[i] - 1, shortestPath[i + 1] - 1);
        }
        return conteo;
    }

    static public double getDistancia(int a, int b) {
        return sqrt(pow(paradas.get(a).getX() - paradas.get(b).getX(), 2) + pow(paradas.get(a).getY() - paradas.get(b).getY(), 2));
    }
    
    static public double getDistancia(double x1, double x2,double y1, double y2) {
        return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }

}
