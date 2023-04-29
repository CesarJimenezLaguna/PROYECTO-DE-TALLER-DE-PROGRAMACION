package es.upm.tp;

import java.util.Scanner;

/**
 * ListaBilletes es una clase que encapsula las variables enteras usadas para definir los billetes,
 * así como también contiene funciones bara buscar, seleccionar e insertar billetes en el array de nombre listaBilletes
 * También escribe un fichero.csv con los datos de cada billete
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class ListaBilletes {

    /**
     * Atributo que contiene la capacidad de la ListaBilletes
     */
    private int capacidad;

    /**
     * Atributo que contiene la ocupación de un billete dentro de la ListaBilletes
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde están los billetes
     */
    private Billete[] ListaBilletes;

    /**
     * Constructor que crea un array con la cantidad de billetes recibidos.
     *
     * @param capacidad especifica la capacidad de la lista que contiene los billetes
     */
    public ListaBilletes(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        ListaBilletes = new Billete[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return devuelve la cantidad de billetes que hay en la listaBilletes como una variable ocupación
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la lista billetes está llena, si no, devuelve falso
     *
     * @return estaLlena es true si se ha alcanzado el máximo sino, devuelve false
     */
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (capacidad == getOcupacion()) {
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter  para conseguir un vuelo
     *
     * @param i variable que toma la posición del billete dentro del array
     * @return Devuelve la posición (i) de un vuelo dentro del array ListaVuelos
     */
    public Billete getBillete(int i) {
        return ListaBilletes[i];
    }

    /**
     * Función que inserta el billete pasado por parámetro al array listaBilletes
     *
     * @param billete Billete que si quiere insertar
     * @return Devuelve True si se ha podido insertar false cuando no se ha podido
     */
    public boolean insertarBillete(Billete billete) {
        boolean insertar = false;
        if (!estaLlena()) {
            ListaBilletes[ocupacion] = billete;
            ocupacion++;
            insertar = true;
        }
        return insertar;
    }

    /**
     * FunciÓn que busca el billete que coincide con el localizador pasado por parÁmetro
     *
     * @param localizador Localizador que es único de cada billete
     * @return Devuelve el billete que se ha buscado
     */
    public Billete buscarBillete(String localizador) {
        Billete resultado = null;
        for (int i = 0; i < ocupacion; i++) {
            if (ListaBilletes[i].getLocalizador().equals(localizador)) {
                resultado = ListaBilletes[i];
            }
        }
        return resultado;
    }

    /**
     * Función que busca el billete que corresponde con la fila y columna pasada por parámetros
     *
     * @param idVuelo ID del vuelo que corresponde con el billete
     * @param fila    Fila del billete en los asientos del avión
     * @param columna Columna del billete en los asientos del avión
     * @return Devuelve el billete que coincide con los datos pasado por parámetro
     */
    public Billete buscarBillete(String idVuelo, int fila, int columna) {
        Billete resultado = null;
        for (int i = 0; i < ocupacion; i++) {
            if (ListaBilletes[i].getVuelo().getID().equals(idVuelo)) {
                if ((ListaBilletes[i].getFila() == fila) && (ListaBilletes[i].getColumna() == columna)) {
                    resultado = ListaBilletes[i];
                }
            }
        }
        return resultado;
    }

    /**
     * Funci´ón que elimina el billete que corresponde con el localizador pasado por parámetro
     *
     * @param localizador Localizador que es único de cada billete
     * @return Devuelve True si se ha podido eliminar y false si no se ha podido
     */
    public boolean eliminarBillete(String localizador) {
        boolean eliminado = true;
        for (int i = 0; i < ocupacion; i++) {
            if (ListaBilletes[i].getLocalizador().equals(localizador)) {
                for (int j = i; j < ocupacion - 1; j++) {
                    ListaBilletes[i] = ListaBilletes[i + 1];
                }
            }
        }
        ocupacion--;
        return eliminado;
    }

    /**
     * Función que muestra por pantalla los billetes de la lista
     */
    // Muestra por pantalla los billetes de la lista
    public void listarBilletes() {
        for (int i = 0; i < ocupacion; i++) {
            System.out.println(ListaBilletes[i].toString());
        }
    }

    /**
     * Función que selecciona un billete mediante el localizador que aporta el usuario
     *
     * @param teclado Teclado por donde el usuario escribe la información pedida
     * @param mensaje Mensaje que se le muestra al usuario de lo que debe aportar
     * @return Devuelve el billete seleccionado si coincide con el localizador que dice el usuario
     */
    // Permite seleccionar un billete existente a partir de su localizador, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente hasta que se introduzca un localizador correcto
    public Billete seleccionarBillete(Scanner teclado, String mensaje) {
        Billete billeteSeleccionado = null;
        do {
            System.out.print(mensaje);
            String localizador = teclado.nextLine();
            billeteSeleccionado = buscarBillete(localizador);
            if (billeteSeleccionado == null) {
                System.out.println("Localizador no encontrado.");
            }
        } while (billeteSeleccionado == null);
        return billeteSeleccionado;
    }

    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero);

    // Métodos estáticos
    // Lee los billetes del fichero CSV y los añade a las lista de sus respectivos Vuelos y Pasajeros
    public static void leerBilletesCsv(String ficheroBilletes, ListaVuelos vuelos, ListaPasajeros pasajeros);
}