package modelo;

import java.util.ArrayList;

public class Mago {
    private String nombre;
    private ArrayList<Hechizo> hechizos;

    public Mago(String nombre) {
        this.nombre = nombre;
        this.hechizos = new ArrayList<>();
    }

    public double calcularPuntajeTotal() {
        double total = 0;
        for (Hechizo h : hechizos) {
            total += h.calcularPuntaje();
        }
        return total;
    }

    public String getNombre() { return nombre; }
    public ArrayList<Hechizo> getHechizos() { return hechizos; }
    
    public void agregarHechizo(Hechizo h) {
        if (h != null && !hechizos.contains(h)) {
            hechizos.add(h);
        }
    }
}