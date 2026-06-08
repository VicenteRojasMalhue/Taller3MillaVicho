Este programa es un simulador de consola basado en un universo de magia y hechizos, desarrollado para el Taller 3 de Programación Avanzada

El objetivo principal es aplicar conceptos avanzados de Programación Orientada a Objetos, como herencia, polimorfismo e interfaces, en un sistema de gestión y análisis de personajes


	Integrantes: Millaray Zepeda - RUT: 220639940 - Usuario GitHub: millazs12
	Vicente Rojas - RUT: 21.895.251-2 - Usuario GitHub: VicenteRojasMalhue

## Paquete modelo:
Este paquete contiene las clases que representan las entidades del mundo de la magia:


* **Mago:** Gestiona la información de cada personaje, incluyendo su nombre y su repertorio dinámico de uno a muchos hechizos
  Implementa la lógica para calcular la puntuación total del mago sumando el poder de los hechizos que domina


* **Hechizo:** Clase base y abstracta que define las propiedades generales de cada conjuro, como su nombre, tipo y daño base
  Define el comportamiento abstracto para el cálculo de su puntaje


* **HechizoFuego:** Subclase que extiende de Hechizo
  Añade el atributo específico de duración de quemadura y calcula su puntaje multiplicando el daño por dicha duración


* **HechizoTierra:** Subclase que extiende de Hechizo
  Añade la propiedad de mejora de defensa y calcula su puntaje mediante la fórmula: `(Daño * MejoraDefensa) / 2`


* **HechizoPlanta:** Subclase que extiende de Hechizo
  Incorpora la duración de stun y la cantidad de plantas, calculando su puntaje como: `Daño + (DuraciónStun * CantPlantas)`


* **HechizoAgua:** Subclase que extiende de Hechizo
  Contiene los atributos de cantidad de heal y presión del agua, con un puntaje equivalente a: `(Daño + CantidadHeal + PresiónDeAgua) * 2`



## Paquete logica:
Contiene las clases e interfaces encargadas de procesar la ejecución del programa, la persistencia de datos y las reglas del sistema:

* **ISistemaMagia:** Interfaz que define el contrato y las operaciones obligatorias del sistema, incluyendo los métodos para la
  lectura/escritura de archivos y las operaciones CRUD de magos y hechizos.

	
* **SistemaMagiaImpl:** Es el motor principal y la implementación de la interfaz de la lógica
  Se encarga de la persistencia de datos (carga y actualización estricta de los archivos `Magos.txt` y `Hechizos.txt`)
  y procesa la lógica de negocio detrás del Panel de Administrador y del Panel de Analista.

* **Menu:** es donde esta toda la logica al compilar el programa.

## Instruciones de ejecucion:

Para que el programa inicie correctamente, los siguientes archivos deben estar 

en la raíz del proyecto(fuera de la carpeta src):

* Hechizos.txt

* Magos.txt

