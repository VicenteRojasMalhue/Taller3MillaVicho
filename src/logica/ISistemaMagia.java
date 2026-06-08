package logica;
//Millaray Belen Zepeda Salfate| ICCI | 22.063.994-0
//Vicente Ignacio Rojas Malhue | ICCI| 21.894.251-2


import java.util.ArrayList;

import modelo.Hechizo;
import modelo.Mago;

public interface ISistemaMagia {
    ArrayList<Hechizo> getCatalogoHechizos();
    ArrayList<Mago> getListaMagos();
    void leerArchivos();
    void guardarArchivos();
    
    boolean ingresarMago(String nombre);
    boolean modificarMago(String nombreAntiguo, String nombreNuevo);
    boolean eliminarMago(String nombre);
    
   
    boolean modificarMagoPorIndice(int indice, String nombreNuevo);
    boolean eliminarMagoPorIndice(int indice);
    
    boolean ingresarHechizo(Hechizo hechizo);
    boolean modificarHechizo(String nombre, int nuevoDanio, int p1, int p2);
    boolean modificarHechizoPorIndice(int indice, int nuevoDanio, int p1, int p2);
    boolean eliminarHechizo(String nombre);
    boolean eliminarHechizoPorIndice(int indice);
    
    ArrayList<Hechizo> obtenerTop10Hechizos();
    ArrayList<Mago> obtenerTop3Magos();
    
   
    boolean asociarHechizoAMago(int indiceMago, int indiceHechizo);
}