package es.upm.tp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        this.ocupacion = 0;
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
        boolean estaLlena = false;
        if (ocupacion == capacidad) {
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * Getter para conseguir el aeropuerto
     *
     * @param i especifica la posición del aeropuerto dentro del array
     * @return Devuelve el aeropuerto que se encuentre en la posición recibida por el parámetro
     */
    public Aeropuerto getAeropuerto(int i) {
        return listaAeropuertos[i - 1];
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
        Aeropuerto resultado = null;
        for (int i = 0; i < listaAeropuertos.length; i++) {
            if (listaAeropuertos[i].getCodigo().equals(codigo)) {
                resultado = listaAeropuertos[i];
            }
        }
        return resultado;
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

    /**
     * Escribe en un fichero los aerpuertos con todas sus características (nombre, latitud, longitud...).
     * @param nombre nombre del fichero en el que se guardan los datos.
     * @return devuelve true si se ha escrito en el fichero.
     */
    // Genera un fichero CSV con la lista de aeropuertos, sobreescribiendolo
    public boolean escribirAeropuertosCsv(String nombre) {
        FileWriter fileWriter = null;
        boolean ficheroEscrito = true;

        try {
            fileWriter = new FileWriter(nombre, false);

            for (int i = 0; i < ocupacion; i++) {
                //Cogemos el aeropuertos de la posición i, luego imprimimos
                Aeropuerto aeropuerto = listaAeropuertos[i];
                fileWriter.write(aeropuerto.getNombre() + ";" + aeropuerto.getCodigo() + ";" + aeropuerto.getLatitud() + ";" + aeropuerto.getLongitud() + ";"
                        + aeropuerto.getTerminales());

                if (i != (ocupacion - 1)) {
                    fileWriter.write("\n");
                }
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println("Fichero " + nombre + " no encontrado.");
            ficheroEscrito = false;
        }
        catch (IOException exception1) {
            System.out.println("Error de escritura en fichero " + nombre + ".");
            ficheroEscrito = false;
        }

        finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                }
                catch (IOException ioException) {
                    System.out.println("Error de cierre del fichero " + nombre + ".");
                    ficheroEscrito = false;
                }
            }
        }
        return ficheroEscrito;
    }

    /**
     * Crea una lista de aeropuertos importada de un archivo de texto CSV
     *
     * @param fichero   nombre del fichero de donde se van a leer los datos
     * @param capacidad capacidad máxima que tendrá la lista
     * @return genera una lista de los aeropuertos con los datos del fichero CSV
     */
    //Métodos estáticos
    //Genera una lista de aeropuertos a partir del fichero CSV, usando el argumento como   
    //capacidad máxima de la lista
    public static ListaAeropuertos leerAeropuertosCsv(String fichero, int capacidad) {
        ListaAeropuertos listaAeropuertosCSV = new ListaAeropuertos(capacidad);
        Scanner scanner = null;
        int terminalesAeropuerto;
        double latitud, longitud;
        String texto;
        String codigo, nombre;

        try{
            scanner = new Scanner(new FileReader(fichero));

            do{
                texto = scanner.nextLine();
                String [] array = texto.split(";");
                //Posiciones del array
                nombre = array[0];
                codigo = array[1];
                latitud = Double.parseDouble(array[2]);
                longitud = Double.parseDouble(array[3]);
                terminalesAeropuerto = Integer.parseInt(array[4]);
                //insertar el aeropuerto a la lista
                listaAeropuertosCSV.insertarAeropuerto(new Aeropuerto(nombre, codigo, latitud, longitud, terminalesAeropuerto));

            }while(scanner.hasNext());
        }
        catch (FileNotFoundException exception) {
            System.out.println("El fichero " + fichero + " no se ha encontrado");
        }
        finally{
            if (scanner != null){
                scanner.close();
            }
        }
        return listaAeropuertosCSV;
    }
}
