package vrp;

import java.util.*;

public class UnionFind {

    private Grafo g;

    public Nodo find(int i) {
        return g.Paradas.get(i);
    }

    public void union(int i, int j) {
        Nodo segundo = find(j);
        if (i != j && !segundo.mark) {
            Nodo primero = find(i);
            segundo.cluster=primero.cluster;
            
            for (int k = 0; k < g.Paradas.size(); k++) {
                if (g.Paradas.get(k).cluster == segundo.cluster) {
                    g.Paradas.get(k).cluster = primero.cluster;
                }
            }
        }
    }

    public UnionFind(Grafo g) {
        this.g = g;
    }
}
