package modelo;
//Millaray Belen Zepeda Salfate| ICCI | 22.063.994-0
//Vicente Ignacio Rojas Malhue | ICCI| 21.894.251-2

public class HechizoPlanta extends Hechizo {
    private int duracionStun;
    private int cantPlantas;

    public HechizoPlanta(String nombre, String tipo, int danio, int duracionStun, int cantPlantas) {
        super(nombre, tipo, danio);
        this.duracionStun = duracionStun;
        this.cantPlantas = cantPlantas;
    }

    @Override
    public double calcularPuntaje() {
        return this.getDanio() + (this.duracionStun * this.cantPlantas);
    }

    public int getDuracionStun() { return duracionStun; }
    public void setDuracionStun(int ds) { this.duracionStun = ds; }
    public int getCantPlantas() { return cantPlantas; }
    public void setCantPlantas(int cp) { this.cantPlantas = cp; }
}