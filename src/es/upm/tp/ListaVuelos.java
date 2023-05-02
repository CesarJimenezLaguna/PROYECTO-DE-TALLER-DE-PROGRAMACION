package es.upm.tp;

import java.util.Objects;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaVuelos {

    private int capacidad;
    private int ocupacion;
    private Vuelo [] ListaVuelos;

    /**
     * Constructor of the class
     *
     * @param capacidad
     */
    public ListaVuelos(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        ListaVuelos = new Vuelo[capacidad];
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public boolean estaLlena() {
        boolean llena = false;
        if (ocupacion == ListaVuelos.length){
            llena = true;
        }
        return llena;
    }

    //Devuelve el objeto vuelo que está en la posición i del array
    public Vuelo getVuelo(int i) {
        return ListaVuelos[i];
    }

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

    //Devuelve un nuevo objeto ListaVuelos conteniendo los vuelos que vayan de un aeropuerto a otro en una determinada fecha
    public ListaVuelos buscarVuelos(String codigoOrigen, String codigoDestino, Fecha fecha) {
        ListaVuelos vuelosBuscados = new ListaVuelos(ocupacion);
        for (int i = 0; i < ocupacion; i++){
            if ((codigoOrigen.equals(ListaVuelos[i].getOrigen().getCodigo())) && (codigoDestino.equals(ListaVuelos[i].getDestino().getCodigo()) && (fecha.coincide(ListaVuelos[i].getSalida())))){
                vuelosBuscados.insertarVuelo(ListaVuelos[i]);
            }
        }
    }

    //Muestra por pantalla los vuelos siguiendo el formato de los ejemplos del enunciado
    public void listarVuelos() {
        for (int i = 0; i < ocupacion; i++){
            System.out.println(ListaVuelos[i].toString());
        }
    }

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
            }
        }while (!vueloTerminado);
        return vueloExistente;
    }

    //Ha de escribir la lista de vuelos en la ruta y nombre del fichero pasado como parámetro.
    //Si existe el fichero, se sobreescribe, si no existe se crea.
    public boolean escribirVuelosCsv(String fichero) {

    }

    //Métodos estáticos
    //Genera una lista de vuelos a partir del fichero CSV, usando los límites especificados como argumentos para la capacidad
    //de la lista
    public static ListaVuelos leerVuelosCsv(String fichero, int capacidad, ListaAeropuertos aeropuertos, ListaAviones aviones);
}
