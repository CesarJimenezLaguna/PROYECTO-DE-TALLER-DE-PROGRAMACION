package es.upm.tp;

import java.util.Objects;
import java.util.Scanner;

/**
 * ListaVuelos es una clase que encapsula las variables enteras usadas para definir los vuelos,
 * así como también contiene funciones para buscar, seleccionar e insertar vuelos
 * También escribe un fichero.csv con los datos de cada vuelo
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class ListaVuelos {

    /**
     * Atributo que contiene la ocupación de un vuelo dentro de la lista
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde están los vuelos
     */
    private Vuelo [] ListaVuelos;

    /**
     * Constructor que crea un array con la cantidad de vuelos recibidos.
     * @param capacidad especifica la capacidad de la lista que contiene los aviones
     */
    public ListaVuelos(int capacidad) {
        this.ocupacion = 0;
        ListaVuelos = new Vuelo[capacidad];
    }

    /**
     * Getter del atributo ocupación
     * @return devuelve la cantidad de vuelos que hay en la listaVuelos como una variable ocupación
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la lista de vuelos está llena
     *  @return estaLlena
     */
    public boolean estaLlena() {
        boolean llena = false;
        if (ocupacion == ListaVuelos.length){
            llena = true;
        }
        return llena;
    }

    /**
     * Getter para conseguir un vuelo
     * @param i variable que toma la posición del vuelo dentro del array
     * @return Devuelve la posición (i) de un vuelo dentro del array ListaVuelos
     */
    //Devuelve el objeto vuelo que está en la posición i del array
    public Vuelo getVuelo(int i) {
        return ListaVuelos[i - 1];
    }

    /**
     * Inserta un vuelo en el array ListaVuelos
     * @param vuelo vuelo que se quiere insertar en la lista
     * @return devuelve true si se ha insertado el vuelo o false si no se ha añadido
     */
    //Devuelve true si puede insertar el vuelo
    public boolean insertarVuelo(Vuelo vuelo) {
        boolean insertado = false;
        if (!estaLlena()){
            ListaVuelos[ocupacion] = vuelo;
            ocupacion++;
            insertado = true;
        }
        return insertado;
    }

    /**
     * Busca un vuelo con su id
     * @param id código que identifica a un vuelo
     * @return devuelve el vuelo correspondiente al id introducido por parámetro
     */
    //Devuelve el objeto vuelo que tenga el identificador igual al parámetro id
    //Si no lo encuentra, devolverá null
    public Vuelo buscarVuelo(String id) {
        Vuelo resultado = null;
        for (int i = 0; i < ocupacion; i++){
            if (Objects.equals(ListaVuelos[i].getID(), id)){
                resultado = ListaVuelos[i];
            }
        }
        return resultado;
    }

    /**
     * Busca vuelos por medio del código de origen del aeropuerto, código de destino del aeropuerto y la fecha de salida de este
     * @param codigoOrigen código que depende del aeropuerto de salida de un vuelo
     * @param codigoDestino código que depende del aeropuerto de destino de un vuelo
     * @param fecha fecha que esta determinada por la salida de un vuelo específico
     * @return devuelve el vuelo buscado mediante los parámetros introducidos y si este tiene las caracteristicas introducidas lo toma
     */
    //Devuelve un nuevo objeto ListaVuelos conteniendo los vuelos que vayan de un aeropuerto a otro en una determinada fecha
    public ListaVuelos buscarVuelos(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaVuelos vuelosBuscados = new ListaVuelos(ocupacion);
        for (int i = 0; i < ocupacion; i++){
            if ((codigoOrigen.equals(ListaVuelos[i].getOrigen().getCodigo())) && (codigoDestino.equals(ListaVuelos[i].getDestino().getCodigo()) && (fecha.coincide(ListaVuelos[i].getSalida())))){
                vuelosBuscados.insertarVuelo(ListaVuelos[i]);
            }
        }
    }

    /**
     * Imprime la lista de los vuelos sigueindo el formato especificado del toString de la clase Vuelo
     */
    //Muestra por pantalla los vuelos siguiendo el formato de los ejemplos del enunciado
    public void listarVuelos() {
        for (int i = 0; i < ocupacion; i++){
            System.out.println(ListaVuelos[i].toString());
        }
    }

    /**
     * Selecciona el vuelo, si existe, del ID indicado por parámetro. Además, comprueba si este está cancelado o no existe
     * @param teclado el usuario introduce el ID del vuelo que desea
     * @param mensaje mensaje que se muestra por pantalla
     * @param cancelar cancela la búsqueda del vuelo
     * @return devuelve el vuelo seleccionado y si cumple los requisitos (que exista y que tenga ID correspondiente)
     */
    //Permite seleccionar un vuelo existente a partir de su ID, usando el mensaje pasado como argumento para la solicitud
    //y siguiendo el orden y los textos mostrados en el enunciado, y usando la cadena cancelar para salir devolviendo null
    //La función solicita repetidamente hasta que se introduzca un ID correcto
    public Vuelo seleccionarVuelo(Scanner teclado, String mensaje, String cancelar) {
        Vuelo vueloExistente = null;
        boolean vueloTerminado = false;

        do {
            System.out.print(mensaje);
            String pantalla = teclado.nextLine();
            if (pantalla.equals(cancelar)){
                vueloTerminado = true;
            }
            else {
                vueloExistente = buscarVuelo(pantalla);
                if (vueloExistente == null){
                    System.out.println("ID de vuelo no encontrado.");
                }
                else {
                    vueloTerminado = true;
                }
            }
        } while (!vueloTerminado);

        return vueloExistente;
    }

    /**
     * Crea o sobreescribe un fichero con los vuelos de una lista.
     * @param fichero nombre del fichero en el que guarda los datos.
     * @return true si se ha logrado.
     */
    //Ha de escribir la lista de vuelos en la ruta y nombre del fichero pasado como parámetro.
    //Si existe el fichero, se sobreescribe, si no existe se crea.
    public boolean escribirVuelosCsv(String fichero);

    /**
     * Lee y devuelve una lista de vuelos desde un fichero pasado.
     * @param fichero nombre del fichero de que leer los vuelos
     * @param capacidad capacidad máxima para la lista de vuelos devuelta
     * @param aeropuertos lista de aeropuertos del cual seleccionar aviones para el vuelo
     * @param aviones lista de aviones del cual seleccionar aviones para el vuelo
     * @return lista de vuelos leída
     */
    //Métodos estáticos
    //Genera una lista de vuelos a partir del fichero CSV, usando los límites especificados como argumentos para la capacidad
    //de la lista
    public static ListaVuelos leerVuelosCsv(String fichero, int capacidad, ListaAeropuertos aeropuertos, ListaAviones aviones);
}
