package modelo;

public abstract class Hechizo {
    private String nombre;
    private String tipo;
    private int danio;

    public Hechizo(String nombre, String tipo, int danio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.danio = danio;
    }

    public abstract double calcularPuntaje();

    public String getNombre() { 
    	return nombre; 
    	}
    public String getTipo() {
    	return tipo;
    }
    public int getDanio() { 
    	return danio;
    	}
    public void setDanio(int danio) { 
    	this.danio = danio; 
    	}
}
