package logica;

import java.util.Scanner;

import modelo.Hechizo;
import modelo.Mago;


public class Menu {
    private ISistemaMagia sistema;
    private Scanner sc;

    public Menu() {
        this.sistema = new SistemaMagiaImpl();
        this.sc = new Scanner(System.in); 
    }

    public void iniciar() {
        sistema.leerArchivos();
        
        int opcion = -1;
        while (opcion != 3) {
            System.out.println("=== ACADEMIA DE MAGIA - MENÚ PRINCIPAL ===");
            System.out.println("1. Panel Administrator");
            System.out.println("2. Panel Analista");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1: menuAdministrador(); break;
                    case 2: menuAnalista(); break;
                    case 3: 
                        System.out.println("Datos guardados. ¡Hasta luego!"); 
                        break;
                    default: System.out.println("Opción inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            }
        }
    }

    private void menuAdministrador() {
        int opcion = -1;
        while (opcion != 7) {
            System.out.println();
            System.out.println("--- PANEL ADMINISTRADOR ---");
            System.out.println("1. Agregar Mago");
            System.out.println("2. Modificar Mago");
            System.out.println("3. Eliminar Mago");
            System.out.println("4. Agregar Hechizo");
            System.out.println("5. Modificar Hechizo");
            System.out.println("6. Eliminar Hechizo");
            System.out.println("7. Volver");
            System.out.print("Seleccione: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del nuevo mago: ");
                        String nom = sc.nextLine();
                        if(sistema.ingresarMago(nom)) System.out.println("Mago agregado.");
                        else System.out.println("El mago ya existe.");
                        break;
                        
                    case 2:
                        System.out.println();
                        System.out.println("Magos registrados en el sistema");
                        if (sistema.getListaMagos().isEmpty()) {
                            System.out.println("  (No hay magos registrados actualmente)");
                            break;
                        } else {
                            for (int i = 0; i < sistema.getListaMagos().size(); i++) {
                                System.out.println(" " + (i + 1) + ") " + sistema.getListaMagos().get(i).getNombre());
                            }
                        }
                        System.out.println();
                        
                        System.out.print("Ingrese el número del mago que desea modificar: ");
                        int numMagoMod = Integer.parseInt(sc.nextLine()) - 1;
                        
                        System.out.print("Ingrese el nuevo nombre para este mago: ");
                        String nue = sc.nextLine();
                        if(sistema.modificarMagoPorIndice(numMagoMod, nue)) System.out.println("Mago modificado.");
                        else System.out.println("No se pudo modificar (Número inválido o nombre duplicado).");
                        break;
                        
                    case 3:
                        System.out.println();
                        System.out.println("Magos registrados en el sistema");
                        if (sistema.getListaMagos().isEmpty()) {
                            System.out.println("  (No hay magos registrados actualmente)");
                            break;
                        } else {
                            for (int i = 0; i < sistema.getListaMagos().size(); i++) {
                                System.out.println(" " + (i + 1) + ") " + sistema.getListaMagos().get(i).getNombre());
                            }
                        }
                        System.out.println();
                        
                        System.out.print("Ingrese el número del mago que desea eliminar: ");
                        int numMagoElim = Integer.parseInt(sc.nextLine()) - 1;
                        if(sistema.eliminarMagoPorIndice(numMagoElim)) System.out.println("Mago eliminado.");
                        else System.out.println("Número de mago no encontrado.");
                        break;
                        
                    case 4:
                        System.out.println();
                        System.out.println("Seleccione el Mago al que desea darle un hechizo");
                        if (sistema.getListaMagos().isEmpty()) {
                            System.out.println("  (No hay magos registrados actualmente)");
                            break;
                        } else {
                            for (int i = 0; i < sistema.getListaMagos().size(); i++) {
                                System.out.println(" " + (i + 1) + ") " + sistema.getListaMagos().get(i).getNombre());
                            }
                        }
                        System.out.println(); 
                        System.out.print("Ingrese el número del mago: ");
                        int idxMago = Integer.parseInt(sc.nextLine()) - 1;
                        
                        if (idxMago < 0 || idxMago >= sistema.getListaMagos().size()) {
                            System.out.println("Número de mago inválido.");
                            break;
                        }

                        if (sistema.getCatalogoHechizos().isEmpty()) {
                            System.out.println();
                            System.out.println("  (No hay hechizos en el catálogo global)");
                            break;
                        }

                        boolean hechizoAsociado = false;
                        while (!hechizoAsociado) {
                            System.out.println();
                            System.out.println("Seleccione el Hechizo que desea enseñarle");
                            for (int i = 0; i < sistema.getCatalogoHechizos().size(); i++) {
                                Hechizo h = sistema.getCatalogoHechizos().get(i);
                                System.out.println(" " + (i + 1) + ") " + h.getNombre() + " [" + h.getTipo() + "]");
                            }
                            System.out.println(" 0) Cancelar operación");
                            System.out.println(); 
                            System.out.print("Ingrese el número del hechizo: ");
                            int idxHechizo = Integer.parseInt(sc.nextLine()) - 1;
                            
                            if (idxHechizo == -1) {
                                System.out.println("Operación cancelada.");
                                break; 
                            }
                            
                            if (sistema.asociarHechizoAMago(idxMago, idxHechizo)) {
                                System.out.println();
                                System.out.println("¡Hechizo enseñado con éxito al mago!");
                                hechizoAsociado = true; 
                            } else {
                                System.out.println();
                                System.out.println("El mago ya conoce ese hechizo o el número es inválido. Intente con otro.");
                            }
                        }
                        break;
                        
                    case 5:
                        System.out.println();
                        System.out.println("Catálogo de Hechizos existentes");
                        if (sistema.getCatalogoHechizos().isEmpty()) {
                            System.out.println("  (No hay hechizos en el catálogo actualmente)");
                            break;
                        } else {
                            for (int i = 0; i < sistema.getCatalogoHechizos().size(); i++) {
                                Hechizo h = sistema.getCatalogoHechizos().get(i);
                                System.out.println(" " + (i + 1) + ") " + h.getNombre() + " [" + h.getTipo() + "]");
                            }
                        }
                        System.out.println();
                        
                        System.out.print("Ingrese el número del hechizo que desea modificar: ");
                        int numHechMod = Integer.parseInt(sc.nextLine()) - 1;
                        
                        if (numHechMod < 0 || numHechMod >= sistema.getCatalogoHechizos().size()) {
                            System.out.println("Número no válido. Operación cancelada.");
                            break;
                        }
                        
                        Hechizo hechizoSeleccionado = sistema.getCatalogoHechizos().get(numHechMod);
                        System.out.println();
                        System.out.println("Modificando: " + hechizoSeleccionado.getNombre() + " [" + hechizoSeleccionado.getTipo() + "]");
                        
                        System.out.print("Ingrese el nuevo número de daño base: ");
                        int nDanio = Integer.parseInt(sc.nextLine());
                        
                        System.out.println();
                        System.out.println("Modificador:");
                        System.out.println("1) Quemadura");
                        System.out.println("2) Defensa");
                        System.out.println("3) Stun");
                        System.out.println("4) Heal");
                        System.out.print("ingrese opcion: ");
                        int opcionMod = Integer.parseInt(sc.nextLine());
                        
                        int mod1 = 0;
                        int mod2 = 0;
                        
                        switch (opcionMod) {
                            case 1: 
                                System.out.print("Ingrese el número de turnos para la quemadura: ");
                                mod1 = Integer.parseInt(sc.nextLine());
                                break;
                            case 2: 
                                System.out.print("Ingrese el número de puntos para la defensa: ");
                                mod1 = Integer.parseInt(sc.nextLine());
                                break;
                            case 3: 
                                System.out.print("Ingrese el número de turnos para el Stun: ");
                                mod1 = Integer.parseInt(sc.nextLine());
                                System.out.print("Ingrese el número de plantas a invocar: ");
                                mod2 = Integer.parseInt(sc.nextLine());
                                break;
                            case 4: 
                                System.out.print("Ingrese el número de puntos de curación (Heal): ");
                                mod1 = Integer.parseInt(sc.nextLine());
                                System.out.print("Ingrese el número de nivel de presión de agua: ");
                                mod2 = Integer.parseInt(sc.nextLine());
                                break;
                            default:
                                System.out.println("Opción de modificador no válida. Se asignarán valores en 0.");
                                break;
                        }
                        
                        if(sistema.modificarHechizoPorIndice(numHechMod, nDanio, mod1, mod2)) {
                            System.out.println("Hechizo modificado con éxito.");
                        } else {
                            System.out.println("No se pudo modificar el hechizo.");
                        }
                        break;
                        
                    case 6:
                        System.out.println();
                        System.out.println("Catálogo de Hechizos existentes");
                        if (sistema.getCatalogoHechizos().isEmpty()) {
                            System.out.println("  (No hay hechizos en el catálogo actualmente)");
                            break;
                        } else {
                            for (int i = 0; i < sistema.getCatalogoHechizos().size(); i++) {
                                Hechizo h = sistema.getCatalogoHechizos().get(i);
                                System.out.println(" " + (i + 1) + ") " + h.getNombre() + " [" + h.getTipo() + "]");
                            }
                        }
                        System.out.println();
                        
                        System.out.print("Ingrese el número del hechizo que desea eliminar: ");
                        int numHechElim = Integer.parseInt(sc.nextLine()) - 1;
                        if(sistema.eliminarHechizoPorIndice(numHechElim)) System.out.println("Hechizo eliminado.");
                        else System.out.println("Número de hechizo no encontrado.");
                        break;
                        
                    case 7:
                        System.out.println(); 
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en el formato de selección. Operación cancelada.");
            }
        }
    }

    private void menuAnalista() {
        int opcion = -1;
        while (opcion != 7) {
            System.out.println();
            System.out.println("--- PANEL ANALISTA ---");
            System.out.println("1. Top 10 Mejores Hechizos");
            System.out.println("2. Top 3 Mejores Magos");
            System.out.println("3. Mostrar todos los Hechizos");
            System.out.println("4. Mostrar todos los Magos");
            System.out.println("5. Mostrar Hechizos con Puntuación");
            System.out.println("6. Mostrar Magos con Puntuación");
            System.out.println("7. Volver");
            System.out.print("Seleccione: ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println();
                        System.out.println("-- TOP 10 HECHIZOS --");
                        for(Hechizo h : sistema.obtenerTop10Hechizos()) {
                            System.out.println(h.getNombre() + " (" + h.getTipo() + ") -> Puntaje: " + h.calcularPuntaje());
                        }
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("-- TOP 3 MAGOS --");
                        for(Mago m : sistema.obtenerTop3Magos()) {
                            System.out.println(m.getNombre() + " -> Total: " + m.calcularPuntajeTotal());
                        }
                        break;
                    case 3:
                        System.out.println();
                        System.out.println("-- CATÁLOGO GLOBAL --");
                        for(Hechizo h : sistema.getCatalogoHechizos()) System.out.println(h.getNombre() + " [" + h.getTipo() + "]");
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("-- REGISTRO DE MAGOS --");
                        for(Mago m : sistema.getListaMagos()) System.out.println(m.getNombre() + " posee " + m.getHechizos().size() + " hechizos.");
                        break;
                    case 5:
                        System.out.println();
                        System.out.println("-- HECHIZOS Y PUNTUACIONES --");
                        for(Hechizo h : sistema.getCatalogoHechizos()) {
                            System.out.println("Hechizo: " + h.getNombre() + " | Puntaje: " + h.calcularPuntaje());
                        }
                        break;
                    case 6:
                        System.out.println();
                        System.out.println("-- MAGOS Y PUNTUACIONES TOTALES --");
                        for(Mago m : sistema.getListaMagos()) {
                            System.out.println("Mago: " + m.getNombre() + " | Puntaje Total: " + m.calcularPuntajeTotal());
                        }
                        break;
                        
                    case 7:
                        System.out.println(); 
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error al procesar el reporte.");
            }
        }
    }
}
