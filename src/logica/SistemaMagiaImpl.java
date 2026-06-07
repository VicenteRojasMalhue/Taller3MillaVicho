package logica;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Hechizo;
import modelo.HechizoAgua;
import modelo.HechizoFuego;
import modelo.HechizoPlanta;
import modelo.HechizoTierra;
import modelo.Mago;

public class SistemaMagiaImpl implements ISistemaMagia {
    private ArrayList<Mago> listaMagos;
    private ArrayList<Hechizo> catalogoHechizos;

    public SistemaMagiaImpl() {
        this.listaMagos = new ArrayList<>();
        this.catalogoHechizos = new ArrayList<>();
    }

    @Override
    public ArrayList<Hechizo> getCatalogoHechizos() { return catalogoHechizos; }

    @Override
    public ArrayList<Mago> getListaMagos() { return listaMagos; }

    @Override
    public void leerArchivos() {
       
        try (Scanner sc = new Scanner(new File("Hechizos.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(";");
                String nombre = partes[0];
                String tipo = partes[1];
                int danio = Integer.parseInt(partes[2]);

                switch (tipo.toLowerCase()) {
                    case "fuego":
                        int duracion = Integer.parseInt(partes[3]);
                        catalogoHechizos.add(new HechizoFuego(nombre, tipo, danio, duracion));
                        break;
                    case "tierra":
                        int defensa = Integer.parseInt(partes[3]);
                        catalogoHechizos.add(new HechizoTierra(nombre, tipo, danio, defensa));
                        break;
                    case "planta":
                        String[] subPlanta = partes[3].split(",");
                        int stun = Integer.parseInt(subPlanta[0]);
                        int cantP = Integer.parseInt(subPlanta[1]);
                        catalogoHechizos.add(new HechizoPlanta(nombre, tipo, danio, stun, cantP));
                        break;
                    case "agua":
                        String[] subAgua = partes[3].split(",");
                        int heal = Integer.parseInt(subAgua[0]);
                        int presion = Integer.parseInt(subAgua[1]);
                        catalogoHechizos.add(new HechizoAgua(nombre, tipo, danio, heal, presion));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Hechizos.txt no encontrado. Se creará uno nuevo.");
        }

        
        try (Scanner sc = new Scanner(new File("Magos.txt"))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;
                String[] partes = linea.split(";");
                Mago mago = new Mago(partes[0]);
                
                if (partes.length > 1) {
                    String[] nombresHechizos = partes[1].split("\\|");
                    for (String nh : nombresHechizos) {
                        Hechizo h = buscarHechizoGlobal(nh);
                        if (h != null) {
                            mago.agregarHechizo(h);
                        }
                    }
                }
                listaMagos.add(mago);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Magos.txt no encontrado. Se creará uno nuevo.");
        }
    }

    private Hechizo buscarHechizoGlobal(String nombre) {
        for (Hechizo h : catalogoHechizos) {
            if (h.getNombre().equalsIgnoreCase(nombre)) return h;
        }
        return null;
    }

    @Override
    public void guardarArchivos() {
      
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Hechizos.txt"))) {
            for (Hechizo h : catalogoHechizos) {
                StringBuilder sb = new StringBuilder();
                sb.append(h.getNombre()).append(";").append(h.getTipo()).append(";").append(h.getDanio()).append(";");
                
                if (h instanceof HechizoFuego) {
                    sb.append(((HechizoFuego) h).getDuracionQuemadura());
                } else if (h instanceof HechizoTierra) {
                    sb.append(((HechizoTierra) h).getMejoraDefensa());
                } else if (h instanceof HechizoPlanta) {
                    HechizoPlanta hp = (HechizoPlanta) h;
                    sb.append(hp.getDuracionStun()).append(",").append(hp.getCantPlantas());
                } else if (h instanceof HechizoAgua) {
                    HechizoAgua ha = (HechizoAgua) h;
                    sb.append(ha.getCantidadHeal()).append(",").append(ha.getPresionAgua());
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir Hechizos.txt");
        }

        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Magos.txt"))) {
            for (Mago m : listaMagos) {
                StringBuilder sb = new StringBuilder();
                sb.append(m.getNombre()).append(";");
                ArrayList<Hechizo> hMago = m.getHechizos();
                for (int i = 0; i < hMago.size(); i++) {
                    sb.append(hMago.get(i).getNombre());
                    if (i < hMago.size() - 1) sb.append("|");
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir Magos.txt");
        }
    }

    @Override
    public boolean ingresarMago(String nombre) {
        for (Mago m : listaMagos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) return false;
        }
        listaMagos.add(new Mago(nombre));
        guardarArchivos();
        return true;
    }

    @Override
    public boolean modificarMago(String nombreAntiguo, String nombreNuevo) {
        for (int i = 0; i < listaMagos.size(); i++) {
            Mago m = listaMagos.get(i);
            if (m.getNombre().equalsIgnoreCase(nombreAntiguo)) {
                return modificarMagoPorIndice(i, nombreNuevo);
            }
        }
        return false;
    }

    @Override
    public boolean modificarMagoPorIndice(int indice, String nombreNuevo) {
        if (indice < 0 || indice >= listaMagos.size()) return false;
        
        for (int i = 0; i < listaMagos.size(); i++) {
            if (i != indice && listaMagos.get(i).getNombre().equalsIgnoreCase(nombreNuevo)) {
                return false;
            }
        }
        
        Mago m = listaMagos.get(indice);
        Mago nuevo = new Mago(nombreNuevo);
        for (Hechizo h : m.getHechizos()) {
            nuevo.agregarHechizo(h);
        }
        
        listaMagos.set(indice, nuevo);
        guardarArchivos();
        return true;
    }

    @Override
    public boolean eliminarMago(String nombre) {
        for (Mago m : listaMagos) {
            if (m.getNombre().equalsIgnoreCase(nombre)) {
                listaMagos.remove(m);
                guardarArchivos();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarMagoPorIndice(int indice) {
        if (indice < 0 || indice >= listaMagos.size()) return false;
        listaMagos.remove(indice);
        guardarArchivos();
        return true;
    }

    @Override
    public boolean ingresarHechizo(Hechizo hechizo) {
        if (buscarHechizoGlobal(hechizo.getNombre()) != null) return false;
        catalogoHechizos.add(hechizo);
        guardarArchivos();
        return true;
    }

    @Override
    public boolean modificarHechizo(String nombre, int nuevoDanio, int p1, int p2) {
        for (int i = 0; i < catalogoHechizos.size(); i++) {
            if (catalogoHechizos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return modificarHechizoPorIndice(i, nuevoDanio, p1, p2);
            }
        }
        return false;
    }

    @Override
    public boolean modificarHechizoPorIndice(int indice, int nuevoDanio, int p1, int p2) {
        if (indice < 0 || indice >= catalogoHechizos.size()) return false;
        
        Hechizo h = catalogoHechizos.get(indice);
        h.setDanio(nuevoDanio);
        if (h instanceof HechizoFuego) {
            ((HechizoFuego) h).setDuracionQuemadura(p1);
        } else if (h instanceof HechizoTierra) {
            ((HechizoTierra) h).setMejoraDefensa(p1);
        } else if (h instanceof HechizoPlanta) {
            ((HechizoPlanta) h).setDuracionStun(p1);
            ((HechizoPlanta) h).setCantPlantas(p2);
        } else if (h instanceof HechizoAgua) {
            ((HechizoAgua) h).setCantidadHeal(p1);
            ((HechizoAgua) h).setPresionAgua(p2);
        }
        guardarArchivos();
        return true;
    }

    @Override
    public boolean eliminarHechizo(String nombre) {
        Hechizo h = buscarHechizoGlobal(nombre);
        if (h == null) return false;
        
        catalogoHechizos.remove(h);
        for (Mago m : listaMagos) {
            m.getHechizos().remove(h);
        }
        guardarArchivos();
        return true;
    }

    @Override
    public boolean eliminarHechizoPorIndice(int indice) {
        if (indice < 0 || indice >= catalogoHechizos.size()) return false;
        
        Hechizo h = catalogoHechizos.get(indice);
        catalogoHechizos.remove(indice);
        
        for (Mago m : listaMagos) {
            m.getHechizos().remove(h);
        }
        guardarArchivos();
        return true;
    }

    @Override
    public ArrayList<Hechizo> obtenerTop10Hechizos() {
        ArrayList<Hechizo> copia = new ArrayList<>(catalogoHechizos);
        copia.sort((h1, h2) -> Double.compare(h2.calcularPuntaje(), h1.calcularPuntaje()));
        if (copia.size() > 10) {
            return new ArrayList<>(copia.subList(0, 10));
        }
        return copia;
    }

    @Override
    public ArrayList<Mago> obtenerTop3Magos() {
        ArrayList<Mago> copia = new ArrayList<>(listaMagos);
        copia.sort((m1, m2) -> Double.compare(m2.calcularPuntajeTotal(), m1.calcularPuntajeTotal()));
        if (copia.size() > 3) {
            return new ArrayList<>(copia.subList(0, 3));
        }
        return copia;
    }

    
    @Override
    public boolean asociarHechizoAMago(int indiceMago, int indiceHechizo) {
        if (indiceMago < 0 || indiceMago >= listaMagos.size()) return false;
        if (indiceHechizo < 0 || indiceHechizo >= catalogoHechizos.size()) return false;
        
        Mago mago = listaMagos.get(indiceMago);
        Hechizo hechizo = catalogoHechizos.get(indiceHechizo);
        
      
        for (Hechizo h : mago.getHechizos()) {
            if (h.getNombre().equalsIgnoreCase(hechizo.getNombre())) {
                return false; 
            }
        }
        
        mago.agregarHechizo(hechizo);
        guardarArchivos();
        return true;
    }
}