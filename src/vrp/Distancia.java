/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vrp;

/**
 *
 * @author alvarez
 */
public class Distancia {
    public boolean mark;
    public int a;
    public int b;
    public double distancia;

    public Distancia(int a, int b, double distancia) {
        this.a = a;
        this.b = b;
        this.distancia = distancia;
        mark=false;
    }

    @Override
    public String toString() {
        return "From: " + a + ", to: " + b + ", distancia: " + distancia;
    }
     
    
}
