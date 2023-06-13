package es.upm.tp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        if (ListaBilletes.length == ocupacion) {
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
        return ListaBilletes[i - 1];
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
        boolean eliminado = false;
        for (int i = 0; i < ocupacion; i++) {
            if (ListaBilletes[i].getLocalizador().equals(localizador)) {
                for (int k = i; k < ocupacion - 1; k++) {
                    ListaBilletes[k] = ListaBilletes[k + 1];
                }
                eliminado = true;
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

    /**
     * Función que añade los billetes al final del archivo CSV
     * @param fichero Fichero en el que se sobreescriben los datos de los billetes
     * @return Devuelve true si se ha podido escribir la información y false si no se ha podido
     */
    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero) {
        FileWriter fileWriter = null;
        boolean billeteAñadido = true;

        try {
            fileWriter = new FileWriter(fichero,true);
            //.
            for(int i = 0; i < ocupacion; i++){
                Billete billeteActual = ListaBilletes[i];
                fileWriter.write(billeteActual.getLocalizador() + ";" + billeteActual.getVuelo().getID() + ";" + billeteActual.getPasajero().getDNI() + ";"
                        + billeteActual.getTipo().name() + ";" + billeteActual.getFila() + ";" + billeteActual.getColumna() + ";" + billeteActual.getPrecio() + "\n");
            }
        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + fichero + " no encontrado.");
            billeteAñadido = false;
        }
        catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            billeteAñadido = false;
        }

        //COMPROBAR EL CIEERE
        finally {
            if(fileWriter != null){
                try {
                    fileWriter.close();
                }
                catch (IOException ioException) {
                    System.out.println("Error de cierre del fichero " + fichero + ".");
                    billeteAñadido = false;
                }
            }
        }
        return billeteAñadido;
    }

    /**
     * Función que lee los billetes del fichero CSV y los añade a la lista de sus respectivos vuelos y pasajeros
     * @param ficheroBilletes Fichero donde se encuentran los billetes
     * @param vuelos Vuelo actual
     * @param pasajeros Pasajeros del vuelo
     */
    // Métodos estáticos
    // Lee los billetes del fichero CSV y los añade a las lista de sus respectivos Vuelos y Pasajeros
    public static void leerBilletesCsv(String ficheroBilletes, ListaVuelos vuelos, ListaPasajeros pasajeros) {
        Scanner scanner = null;
        String entrada;
        boolean leerLogrado = true;
        String localizarBillete, idVuelo, dni, tipo;
        int filas, columnas;
        double precio;
        Pasajero pasajeroActual;
        Vuelo vueloActual;

        try {
            scanner = new Scanner(new FileReader(ficheroBilletes));

            do {
                entrada = scanner.nextLine();
                String[] arrayCSVBilletes = entrada.split(";");
                localizarBillete = arrayCSVBilletes[0];
                idVuelo = arrayCSVBilletes[1];
                dni = arrayCSVBilletes[2];
                tipo= arrayCSVBilletes[3];
                filas = Integer.parseInt(arrayCSVBilletes[4]);
                columnas = Integer.parseInt(arrayCSVBilletes[5]);
                precio = Double.parseDouble(arrayCSVBilletes[6]);
                vueloActual = vuelos.buscarVuelo(idVuelo);
                pasajeroActual = pasajeros.buscarPasajeroDNI(dni);
                Billete billete = new Billete(localizarBillete, vueloActual, pasajeroActual, Billete.TIPO.valueOf(tipo), filas, columnas, precio);
                vueloActual.getListaBilletesVuelo().insertarBillete(billete);
                pasajeroActual.getListaBilletesPasajero().insertarBillete(billete);
                vueloActual.ocuparAsiento(billete);

            } while(scanner.hasNext());
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + ficheroBilletes + " no encontrado.");
        }
        finally {
            if (scanner != null){
                scanner.close();
            }
        }
    }
}