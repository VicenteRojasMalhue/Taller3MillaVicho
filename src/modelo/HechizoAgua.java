package modelo;

public class HechizoAgua extends Hechizo {
    private int cantidadHeal;
    private int presionAgua;

    public HechizoAgua(String nombre, String tipo, int danio, int cantidadHeal, int presionAgua) {
        super(nombre, tipo, danio);
        this.cantidadHeal = cantidadHeal;
        this.presionAgua = presionAgua;
    }

    @Override
    public double calcularPuntaje() {
        return (this.getDanio() + this.cantidadHeal + this.presionAgua) * 2.0;
    }

    public int getCantidadHeal() { 
    	return cantidadHeal; 
    	}
    public void setCantidadHeal(int ch) { 
    	this.cantidadHeal = ch; 
    	}
    public int getPresionAgua() { 
    	return presionAgua; 
    	}
    public void setPresionAgua(int pa) { 
    	this.presionAgua = pa;
    	}
}
