package modelo;

public class HechizoFuego extends Hechizo {
    private int duracionQuemadura;

    public HechizoFuego(String nombre, String tipo, int danio, int duracionQuemadura) {
        super(nombre, tipo, danio);
        this.duracionQuemadura = duracionQuemadura;
    }

    @Override
    public double calcularPuntaje() {
        return this.getDanio() * this.duracionQuemadura;
    }

    public int getDuracionQuemadura() { 
    	return duracionQuemadura; 
    	}
    public void setDuracionQuemadura(int dq) { 
    	this.duracionQuemadura = dq; 
    	}
}
