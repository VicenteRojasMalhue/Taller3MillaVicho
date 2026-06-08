package modelo;
//Millaray Belen Zepeda Salfate| ICCI | 22.063.994-0
//Vicente Ignacio Rojas Malhue | ICCI| 21.894.251-2

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
