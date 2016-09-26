package vrp;

import java.io.File;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Scanner;

public final class Grafo {
    public int camiones;
    public ArrayList <Nodo> Paradas;
    File file;
    public int clusters;
    public ArrayList<Integer> clusterss;

    public Grafo(String path) {
        Paradas= new ArrayList();
        file = new File(path);
        generarGrafo();
        clusters=Paradas.size();
    }

    public double getDistancia(int a, int b) {
        return sqrt(pow(Paradas.get(a).x-Paradas.get(b).x,2)+pow(Paradas.get(a).y-Paradas.get(b).y,2));
    }

    public void generarGrafo() {
        try (Scanner sc = new Scanner(file)) {
            boolean valid=false;
            int id=0;
            while (sc.hasNextLine()) {
                if (valid) {
                    String i = sc.next();
                    String [] coordenadas=i.split(",");
                    Paradas.add(new Nodo(Integer.parseInt(coordenadas[0]),Integer.parseInt(coordenadas[1]),id,id)); 
                    id++;
                }else{
                    camiones=sc.nextInt();
                    valid = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void print(){
        for (int i = 0; i < Paradas.size(); i++) {
            System.out.println(Paradas.get(i).toString());
        }
    }
    
    public void actualizarClusters(){
        clusterss= new ArrayList();
        for (int i = 0; i < Paradas.size(); i++) {
            if(!isExists(clusterss,Paradas.get(i).cluster ))
                clusterss.add(Paradas.get(i).cluster);
        }
        clusters=clusterss.size();
    }
    
    private static boolean isExists(ArrayList<Integer> clusterss, int value){
        for (int i = 0; i < clusterss.size(); i++) {
            if(clusterss.get(i)==value)
                return true;
        }
        return false;
    }
    
    public ArrayList <Nodo> getCluster(int clust){
        ArrayList <Nodo> Cluster=new ArrayList();
        for (int i = 0; i < Paradas.size(); i++) {
            if(Paradas.get(i).cluster==clust){
                Cluster.add(Paradas.get(i));
                
            }
        }
        return Cluster;
    }
}
