package modelo;

public class HechizoTierra extends Hechizo {
    private int mejoraDefensa;

    public HechizoTierra(String nombre, String tipo, int danio, int mejoraDefensa) {
        super(nombre, tipo, danio);
        this.mejoraDefensa = mejoraDefensa;
    }

    @Override
    public double calcularPuntaje() {
        return (this.getDanio() * this.mejoraDefensa) / 2.0;
    }

    public int getMejoraDefensa() { return mejoraDefensa; }
    public void setMejoraDefensa(int md) { this.mejoraDefensa = md; }
}