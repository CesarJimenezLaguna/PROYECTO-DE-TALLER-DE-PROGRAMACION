package es.upm.tp;

import java.util.Scanner;

/**
 * ListaAeropuertos es una clase que encapsula las variables enteras usadas para definir los aeropuertos,
 * así como también contiene funciones bara buscar, seleccionar e insertar aeropuertos en el array de nombre Listaaeropuertos
 * También escribe un fichero.csv con los datos de cada aeropuerto
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class ListaAeropuertos {

    /**
     * Atributo que devuelve la capacidad de la ListaAeropuerto
     */
    private int capacidad;

    /**
     * Atributo que contiene la ocupación del aeropuerto dentro de la lista
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde se guardan los aeropuertos
     */
    private Aeropuerto[] listaAeropuertos;

    /**
     * Constructor que crea un array con la cantidad de aeropuertos recibidos
     *
     * @param capacidad especifica la capacidad del aeropuerto
     */
    public ListaAeropuertos(int capacidad) {
        this.capacidad = capacidad;
        listaAeropuertos = new Aeropuerto[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return Devuelve la cantidad de aeropuertos que hay en la listaAeropuertos como una variable ocupación
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la listaAeropuertos esta llena, si no, devuelve falso
     *
     * @return estaLlena
     */
    public boolean estaLlena() {
        boolean llena = false;
        if (ocupacion == listaAeropuertos.length) {
            llena = true;
        }
        return llena;
    }

    /**
     * Getter para conseguir el aeropuerto
     *
     * @param i especifica la posición del aeropuerto dentro del array
     * @return Devuelve el aeropuerto que se encuentre en la posición recibida por el parámetro
     */
    public Aeropuerto getAeropuerto(int i) {
        return listaAeropuertos[i];
    }

    /**
     * Inserta un aeropuerto en el array ListaAeropuertos
     *
     * @param aeropuerto es el aeropuerto que se quiere insertar en la lista
     * @return devuelve true si se ha insertado el aeropuerto o false si no se ha añadido correctamente
     */
    public boolean insertarAeropuerto(Aeropuerto aeropuerto) {
        boolean insertado = false;
        if (!estaLlena()) {
            listaAeropuertos[ocupacion] = aeropuerto;
            ocupacion++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * Devuelve el aeropuerto que coincide con el código pasado por parémetro
     *
     * @param codigo especifica que aeropuerto se esta considerando y comparando para ver si existe
     * @return devuelve el aeropuerto que coincide con el código pedido por parámetro
     */
    public Aeropuerto buscarAeropuerto(String codigo) {
        Aeropuerto resul = null;
        for (int i = 0; i < listaAeropuertos.length; i++) {
            if (listaAeropuertos[i].getCodigo().equals(codigo)) {
                resul = listaAeropuertos[i];
            }
        }
        return resul;
    }

    /**
     * Selecciona el aeropuerto existente que coincide con el código solicitado
     *
     * @param teclado código IATA que introduce el usuario
     * @param mensaje mensaje que se muestra por pantalla
     * @return devuelve el aeropuerto, si este existe
     */
    // Permite seleccionar un aeropuerto existente a partir de su código, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente el código hasta que se introduzca uno correcto
    public Aeropuerto seleccionarAeropuerto(Scanner teclado, String mensaje) {
        String codigoIATA;
        do {
            System.out.println(mensaje);
            codigoIATA = teclado.nextLine();
            if (buscarAeropuerto(codigoIATA) == null) {
                System.out.println("Código de aeropuerto no encontrado.");
            }
        } while (buscarAeropuerto(codigoIATA) == null);
        return buscarAeropuerto(codigoIATA);
    }

    // Genera un fichero CSV con la lista de aeropuertos, sobreescribiendolo
    public boolean escribirAeropuertosCsv(String nombre);

    //Métodos estáticos
    //Genera una lista de aeropuertos a partir del fichero CSV, usando el argumento como   
    //capacidad máxima de la lista
    public static ListaAeropuertos leerAeropuertosCsv(String fichero, int capacidad);
}
