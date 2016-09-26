package vrp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;

public class VRP {

    static UnionFind UF;
    static Grafo g;

    public static void main(String[] args) {
        Kmeans kmeans = new Kmeans();
        boolean valid = true;
        ArrayList<int[]> shortP = new ArrayList();
        while (valid) {
            try {
                kmeans = new Kmeans();
                kmeans.init();
                kmeans.calculate();
                shortP = new ArrayList();
                for (int i = 0; i < kmeans.clusters.size(); i++) {
                    kmeans.eraseCentroide(i);
                    if (kmeans.clusters.get(i).points.size() < 2) {
                        throw new Exception();
                    }
                    //kmeans.clusters.get(i).plotCluster();
                    TSP instance = new TSP(kmeans.clusters.get(i).points);
                    int[] shortpath;
                    shortpath = instance.solveMe();
                    valid = false;
                    shortP.add(shortpath);
                }
            } catch (Exception e) {
                valid = true;
            }
        }
        try {
            
            System.out.println("********Paradas********");
            for (int i = 0; i < kmeans.points.size(); i++) {
                System.out.println("Punto " + i + ": " + kmeans.points.get(i));
            }
            System.out.println("***********************");
            System.out.println("\n");
            for (int i = 0; i < kmeans.clusters.size(); i++) {
                System.out.println("ola");
                kmeans.clusters.get(i).plotCluster();
                TSP instance = new TSP(kmeans.clusters.get(i).points);
                System.out.println();
                PrintWriter writer = new PrintWriter("Ruta "+i+".txt", "UTF-8");
                writer.println(instance.print(shortP.get(i)));
                writer.close();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al imprimir");
        }
    }

    /*ArrayList<Nodo> first = g.getCluster(0);
        int distance = 0;
        for (int i = 0; i < distancias.size(); i++) {
            boolean validA = false;
            boolean validB = false;
            for (int j = 0; j < first.size(); j++) {
                if (distancias.get(i).a == first.get(j).id) {
                    validA = true;
                }
                if (distancias.get(i).b == first.get(j).id) {
                    validB = true;
                }

            }
            if (validA && validB) {
                distance+=distancias.get(i).distancia;
                System.out.println(distancias.get(i));
            }
            
        }
        System.out.println(distance);
        //g.print();
    }


    static ArrayList<Distancia> TodosConTodos(Grafo g) {
        ArrayList<Distancia> distancias = new ArrayList();
        for (int i = 0; i < g.Paradas.size(); i++) {
            for (int j = i + 1; j < g.Paradas.size(); j++) {
                distancias.add(new Distancia(i, j, g.getDistancia(i, j)));
            }
        }
        Collections.sort(distancias, (Distancia d1, Distancia d2) -> {
            double a = d1.distancia;
            double b = d2.distancia;
            return a < b ? -1
                    : a > b ? 1
                            : 0;
        });
        return distancias;
    }
    
    static void Clustering(ArrayList<Distancia> distancias, int i){
        if(g.clusters>g.camiones){
            int indexA=distancias.get(i).a;
            int indexB=distancias.get(i).b;
            UF.union(indexA, indexB);
            g.actualizarClusters();
            i++;
            Clustering(distancias, i);  
        }
    }
    
    static ArrayList<Integer> TSP(ArrayList<Nodo> grafo){
        return null;
    }*/
}
