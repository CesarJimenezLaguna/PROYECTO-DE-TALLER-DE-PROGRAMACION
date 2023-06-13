package es.upm.tp;

import java.awt.*;
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
    public ListaPasajeros(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        listaPasajeros = new Pasajero[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return Devuelve la cantidad de pasajeros que hay en la listaPasajeros como una variable ocupación
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la listaPasajeros esta llena, si no, devuelve falso
     *
     * @return estaLlena
     */
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (capacidad == ocupacion) {
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir al pasajero que se encuentra en la posición i del array listaPasajeros
     *
     * @param i especifica la posición del pasajero dentro del array
     * @return Devuelve el pasajero que se encuentra en la posición recibida por el parámetro
     */
    public Pasajero getPasajero(int i) {
        return listaPasajeros[i - 1];
    }

    /**
     * Inserta un pasajero en el array ListaPasajeros
     *
     * @param pasajero Es el pasajero que se quiere insertar en la lista
     * @return devuelve true si se ha insertado el pasajero o false si no se ha añadido
     */
    public boolean insertarPasajero(Pasajero pasajero) {
        boolean insertar = false;
        if (!estaLlena()) {
            listaPasajeros[ocupacion] = pasajero;
            ocupacion++;
            insertar = true;
        }
        return insertar;
    }

    /**
     * Función que busca a un pasajero por medio de su DNI pasado por parámetro
     *
     * @param dni DNI que esta asociado a un pasajero
     * @return Devuelve el pasajero que coincide con el DNI
     */
    public Pasajero buscarPasajeroDNI(String dni) {
        Pasajero resultado = null;

        boolean encontrado = false;
        int k = 0;
        while (k < ocupacion && !encontrado){
            if (listaPasajeros[k].getDNI().equalsIgnoreCase(dni)){
                resultado = listaPasajeros[k];
                encontrado = true;
            }
            else
                k++;
        }

        /*
        for (int i = 0; i < ocupacion; i++) {
            if (listaPasajeros[i].getDNI().equalsIgnoreCase(dni)) {
                resultado = listaPasajeros[i];
            }
        }

         */

        return resultado;

    }

    /**
     * Función que busca a un pasajero por medio de su email pasado por parámetro
     *
     * @param email email del pasajero
     * @return Devuelve el pasajero que coincide con el email pasado por parámetro
     */
    public Pasajero buscarPasajeroEmail(String email) {
        Pasajero resultado = null;
        for (int i = 0; i < ocupacion; i++) {
            if (Objects.equals(listaPasajeros[i].getEmail(), email)) {
                resultado = listaPasajeros[i];
            }
        }
        return resultado;
    }

    /**
     * Selecciona el pasajero, si existe, del dni que pasa el usuario
     *
     * @param teclado el usuario introduce el dni del pasajero que desea
     * @param mensaje mensaje que se muestra por pantalla
     * @return devuelve el pasajero seeleccionado que coincide con el DNI
     */
    // Permite seleccionar un pasajero existente a partir de su DNI, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente hasta que se introduzca un DNI correcto
    public Pasajero seleccionarPasajero(Scanner teclado, String mensaje) {
        String DNI;
        do {
            System.out.print(mensaje);
            DNI = teclado.nextLine();
            if (buscarPasajeroDNI(DNI) == null) {
                System.out.print("DNI no encontrado.");
            }
        } while (buscarPasajeroDNI(DNI) == null);
        return buscarPasajeroDNI(DNI);
    }


    /**
     * Genera un fichero CSV con la lista de pasajeros
     * @param fichero Fichero sobre el que se sobreescriben los datos de los pasajeros
     * @return Devuelve True si se ha sobreescrito el fichero y false si no se ha podido
     */
    // Genera un fichero CSV con la lista de pasajeros, sobreescribiendolo
    public boolean escribirPasajerosCsv(String fichero);

    /**
     * Genera una lista con los pasajeros, tomando los datos del fichero Csv
     * @param fichero fichero donde lee los datos de los vuelos
     * @param capacidad capacidad del array ListaPasajeros
     * @param maxBilletesPasajero cantidad máxima de billetes posibles por pasajeros en un vuelo
     * @return devuelve la lista que ha creado
     */
    //Métodos estáticos
    // Genera una lista de pasajeros a partir del fichero CSV, usando los límites especificados como argumentos para la capacidad
    // de la lista y el número de billetes máximo por pasajero
    public static ListaPasajeros leerPasajerosCsv(String fichero, int capacidad, int maxBilletesPasajero);
}