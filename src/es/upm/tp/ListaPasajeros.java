package es.upm.tp;

import java.util.Objects;
import java.util.Scanner;

/**
 * ListaPasajeros es una clase que encapsula las variables enteras usadas para definir los pasajeros,
 * así como también contiene funciones bara buscar, seleccionar e insertar estos mismos pasajeros en el array
 * de nombre listaPasajeros
 * También escribe un fichero.csv con los datos de cada pasajero
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class ListaPasajeros {

    /**
     * Atributo que devuelve la capacidad de la ListaPasajeros
     */
    private int capacidad;

    /**
     * Atributo que contiene la ocupación de los pasajeros dentro de la lista
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde se guardan los pasajeros
     */
    private Pasajero[] listaPasajeros;

    /**
     * Constructor de la clase, crea un vector que contiene la cantidad de pasajeros
     *
     * @param capacidad especifica la capacidad de la lista de pasajeros
     */
    public ListaPasajeros(int capacidad);
    public int getOcupacion();
    public boolean estaLlena();
    public Pasajero getPasajero(int i);
    public boolean insertarPasajero(Pasajero pasajero);
    public Pasajero buscarPasajeroDNI(String dni);
    public Pasajero buscarPasajeroEmail(String email);
    // Permite seleccionar un pasajero existente a partir de su DNI, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente hasta que se introduzca un DNI correcto
    public Pasajero seleccionarPasajero(Scanner teclado, String mensaje);
    // Genera un fichero CSV con la lista de pasajeros, sobreescribiendolo
    public boolean escribirPasajerosCsv(String fichero);

    //Métodos estáticos
    // Genera una lista de pasajeros a partir del fichero CSV, usando los límites especificados como argumentos para la capacidad
    // de la lista y el número de billetes máximo por pasajero
    public static ListaPasajeros leerPasajerosCsv(String fichero, int capacidad, int maxBilletesPasajero);
}