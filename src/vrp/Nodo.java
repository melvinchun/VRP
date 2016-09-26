/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp;

public class Nodo {
    public boolean mark;
    int cluster;
    int id;
    public int x;
    public int y;

    public Nodo(int x, int y, int id, int cluster) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.cluster = cluster;
        this.mark=false;
        
    }

    @Override
    public String toString() {
        return "Nodo " + id + ": " + x + ", " + y+" Cluster: "+cluster;
    }
}
