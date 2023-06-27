package es.upm.tp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 * Clase que representa el funcionamiento de una línea aérea. Cumple las funciones de reservar
 * asientos de 3 tipos distintos para pasajeros, los cuales, viajan a otros aeropuertos en aviones diferentes.
 * También es posible generar facturas de billetes y listas de los pasajeros de los vuelos dados de alta.
 * Todos los datos de los pasajeros, aviones, vuelos, aeropuertos y sus respectivos billetes son guardados y cargados en ficheros.
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */

public class AirUPM {

    /**
     * Atributo que representa la capacidad máxima de la lista de Aeropuertos
     */
    private int maxAeropuertos;

    /**
     * Atributo que representa la capacidad máxima de la lista de Aviones
     */
    private int maxAviones;

    /**
     * Atributo que representa la capacidad máxima de la lista de Vuelos
     */
    private int maxVuelos;

    /**
     * Atributo que representa la capacidad máxima de la lista de Pasajeros
     */
    private int maxPasajeros;

    /**
     * Atributo que representa la capacidad máxima de la lista de Billetes
     */
    private int maxBilletesPasajero;


    /**
     * Objeto que contiene los aeropuertos disponibles del programa
     */
    private ListaAeropuertos listaAeropuertos = null;

    /**
     * Objeto que contiene los aviones disponibles del programa
     */
    private ListaAviones listaAviones = null;

    /**
     * Objeto que contiene los vuelos disponibles del programa
     */
    private ListaVuelos listaVuelos = null;

    /**
     * Objeto que contiene los pasajeros disponibles del programa
     */
    private ListaPasajeros listaPasajeros = null;

    /**
     * Constructor de la clase AirUPM
     * Inicializa los atributos correspondientes, dentro de los parámetros utilizados.
     *
     * @param maxAeropuertos      número máximo de aeropuertos que admite el programa
     * @param maxAviones          número máximo de aviones que admite el programa
     * @param maxVuelos           número máximo de vuelos que admite el programa
     * @param maxPasajeros        número máximo de pasajeros que admite el programa
     * @param maxBilletesPasajero número máximo de billetes que admite el programa por pasajero.
     */
    public AirUPM(int maxAeropuertos, int maxAviones, int maxVuelos, int maxPasajeros, int maxBilletesPasajero) {
        this.maxAeropuertos = maxAeropuertos;
        this.maxAviones = maxAviones;
        this.maxVuelos = maxVuelos;
        this.maxPasajeros = maxPasajeros;
        this.maxBilletesPasajero = maxBilletesPasajero;
    }

    /**
     *
     * Función que lee los datos de los ficheros iniciales pasados por parámetro y los añade a la clase AirUPM
     *
     * @param ficheroAeropuertos fichero que contiene la lista de los aeropuertos
     * @param ficheroAviones     fichero que contiene la lista de los aviones
     * @param ficheroVuelos      fichero que contiene la lista de los vuelos
     * @param ficheroPasajeros   fichero que contiene la lista de los pasajeros
     * @param ficheroBilletes    fichero que contiene la lista de los billetes
     */
    // Lee los datos de los ficheros especificados y los agrega a AirUPM
    public void cargarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes) {
        listaAeropuertos = ListaAeropuertos.leerAeropuertosCsv(ficheroAeropuertos, maxAeropuertos);
        listaAviones = ListaAviones.leerAvionesCsv(ficheroAviones, maxAviones);
        listaVuelos = ListaVuelos.leerVuelosCsv(ficheroVuelos, maxVuelos, listaAeropuertos, listaAviones);
        listaPasajeros = ListaPasajeros.leerPasajerosCsv(ficheroPasajeros, maxPasajeros, maxBilletesPasajero);
        ListaBilletes.leerBilletesCsv(ficheroBilletes, listaVuelos, listaPasajeros);
    }

    /**
     *
     * Función que guarda los datos de AirUPM en los ficheros.csv especificados en el programa
     *
     * @param ficheroAeropuertos fichero en donde se guardan los aeropuertos
     * @param ficheroAviones     fichero en donde se guardan los aviones
     * @param ficheroVuelos      fichero en donde se guardan los vuelos
     * @param ficheroPasajeros   fichero en donde se guardan los pasajeros
     * @param ficheroBilletes    fichero en donde se guardan los billetes
     * @return la funcion devuelve verdadero si se guardan los datos correctamente, si no es asi, indica falso
     */
    // Almacena los datos de AirUPM en los ficheros CSV especificados
    public boolean guardarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes){
        boolean ficheroAeropuerto = listaAeropuertos.escribirAeropuertosCsv(ficheroAeropuertos);
        boolean ficheroAvion = listaAviones.escribirAvionesCsv(ficheroAviones);
        boolean ficheroVuelo = listaVuelos.escribirVuelosCsv(ficheroVuelos);
        boolean ficheroPasajero = listaPasajeros.escribirPasajerosCsv(ficheroPasajeros);

        FileWriter fileWriter = null;
        boolean datosGuardados = false;

        try {
            fileWriter = new FileWriter(ficheroBilletes);
            fileWriter.write("");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        //Sobreescribimos el fichero de los billetes paar que se guarden de manera correcta
        for (int i = 1; i <= listaVuelos.getOcupacion(); i++){
            listaVuelos.getVuelo(i).aniadirBilletesCsv(ficheroBilletes);
        }

        if (ficheroAeropuerto && ficheroAvion && ficheroVuelo && ficheroPasajero){
            datosGuardados = true;
        }
        return datosGuardados;
    }

    /**
     * @return devuelve verdadero si se han alcanzado el máximo de vuelos posibles (si la lista de vuelos esta llena), sino indica falso
     */
    public boolean maxVuelosAlcanzado() {
        return maxVuelos == listaVuelos.getOcupacion();
    }

    /**
     *
     * Inserta un vuelo de la lista de Vuelos disponibles
     *
     * @param vuelo vuelo que se inserta por parámetro
     * @return devuleve verdadero se se ha insertado correctamente el vuelo y falso en caso contrario
     */
    public boolean insertarVuelo(Vuelo vuelo) {
        boolean insertado;
        if (maxVuelosAlcanzado()) insertado = false;
        else {
            listaVuelos.insertarVuelo(vuelo);
            insertado = true;
        }
        return insertado;
    }

    /**
     * @return devuelve verdadero si se ha alcanzado el numero maximo de pasajeros (listaPasajeros), en caso contrario devuelve falso
     */
    public boolean maxPasajerosAlcanzado() {
        return (maxPasajeros == listaPasajeros.getOcupacion());
    }

    /**
     * Inserta un pasajero en la lista de pasajeros
     *
     * @param pasajero pasajero a insertar por parámetro
     * @return devuelve verdadero si se ha insertado correctamente y falso si no se inserta
     */
    public boolean insertarPasajero(Pasajero pasajero) {
        boolean insertado;
        if (maxPasajerosAlcanzado()) insertado = false;
        else {
            listaPasajeros.insertarPasajero(pasajero);
            insertado = true;
        }
        return insertado;
    }

    /**
     *
     * Método que busca los vuelos los cuales tengas especificados un aeropuerto (salida-llegada) y fechas (salida-llegada)
     *
     * @param teclado objeto para guardar lo que escribe el usuario
     * @return devuelve la lista de vuelos con el los aeropuertos de llegada y salida. Tambien, devuelve las fechas (llegada y salida)
     */
    // Funcionalidad buscarVuelo especificada en el enunciado del proyecto, que devuelve una lista de vuelos entre dos aeropuertos y
    // con una fecha de salida solicitados por teclado al usuario en el orden y con los textos indicados en los ejemplos de
    // ejecución del enunciado
    public ListaVuelos buscarVuelo(Scanner teclado) {
        System.out.print("Ingrese código de Aeropuerto Origen:");
        String codigoAeropuertoOrigen = teclado.nextLine().toUpperCase();
        System.out.print("Ingrese código de Aeropuerto Destino:");
        String codigoAeropuertoDestino = teclado.nextLine().toUpperCase();
        Fecha fecha = Utilidades.leerFecha(teclado, "Fecha de salida:");
        return listaVuelos.buscarVuelos(codigoAeropuertoOrigen, codigoAeropuertoDestino, fecha);
    }

    /**
     *
     * Método que permite al usuario comprar un billete. De esta forma, le va pidiendo toda la información necesaria como el DNI
     *
     * @param teclado objeto para guardar lo que escribe el usuario
     * @param rand número aleatorio para crear el idetificador de un billete (aleatorio)
     * @param vuelo vuelo del cual se va a comprar un billete
     */
    // Funcionalidad comprarBillete especificada en el enunciado del proyecto, que compra un billete para un vuelo especificado,
    // pidiendo por teclado los datos necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del
    // enunciado. Si la lista de pasajeros está vacía, creará un nuevo pasajero, si está llena seleccionará un pasajero, en cualquier
    // otro caso, deberá preguntar al usuario si crear o seleccionar
    public void comprarBillete(Scanner teclado, Random rand, Vuelo vuelo) {
        char respuesta;
        Pasajero pasajeroVuelo = null;
        Billete billetePasajero = null;

        if (vuelo.vueloLleno()) {
            System.out.println("El vuelo " + vuelo.getID() + " está lleno, no se pueden comprar más billetes");
        } else {

            if (listaPasajeros.estaLlena()) { //Pasajero existente
                pasajeroVuelo = listaPasajeros.seleccionarPasajero(teclado, "Ingrese DNI del pasajero:");
                if (pasajeroVuelo.maxBilletesAlcanzado()) {
                    System.out.println("El Pasajero seleccionado no puede adquirir más billetes.");
                } else {
                    billetePasajero = Billete.altaBillete(teclado, rand, vuelo, pasajeroVuelo);
                    System.out.println("Billete " + billetePasajero.getLocalizador() + " comprado con éxito.");
                }
            } else {

                do {
                    //Sólo se aceptan respuestas en minúsculas
                    //respuesta = Utilidades.leerLetra(teclado, "¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?", 'a', 'z');

                    //Acepta respuestas en mayúsculas y minúsculas indistintamente
                    do {
                        System.out.print("¿Comprar billete para un nuevo pasajero (n), o para uno ya existente (e)?");
                        respuesta = Character.toLowerCase(teclado.nextLine().charAt(0));
                    } while (respuesta < 'a' || respuesta > 'z');

                    if (respuesta != 'n' && respuesta != 'e')
                        System.out.println("El valor de entrada debe ser 'n' o 'e'");

                    if (respuesta == 'n') {
                        pasajeroVuelo = Pasajero.altaPasajero(teclado, listaPasajeros, maxBilletesPasajero);
                        billetePasajero = Billete.altaBillete(teclado, rand, vuelo, pasajeroVuelo);
                        //asiento acupado en la función alta
                        System.out.println("Billete " + billetePasajero.getLocalizador() + " comprado con éxito.");
                    }

                    if (respuesta == 'e') {
                        pasajeroVuelo = listaPasajeros.seleccionarPasajero(teclado, "Ingrese DNI del pasajero:");
                        if (pasajeroVuelo.maxBilletesAlcanzado())
                            System.out.println("El Pasajero seleccionado no puede adquirir más billetes.");
                        else {
                            billetePasajero = Billete.altaBillete(teclado, rand, vuelo, pasajeroVuelo);
                            //si se ha ocupado el asiento
                            System.out.println("Billete " + billetePasajero.getLocalizador() + " comprado con éxito.");
                        }
                    }
                } while (respuesta != 'n' && respuesta != 'e');
            }
        }
    }

    //Métodos estáticos

    /**
     *
     * Muestra un menú con las opciones del programa, devuelve un (int) de la opción introducida por teclado
     *
     * @param teclado objeto que guarda lo que escribe el usuario
     * @return devuelve la opción introducida por teclado
     */
    // Muestra el menú y solicita una opción por teclado
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("1. Alta Vuelo");
        System.out.println("2. Alta Pasajero");
        System.out.println("3. Buscar Vuelo");
        System.out.println("4. Mostrar billetes de Pasajero");
        System.out.println("5. Generar lista de Pasajeros");
        System.out.println("0. Salir");
        opcion = Utilidades.leerNumero(teclado, "Seleccione opción:", 0, 5);
        return opcion;
    }

    /**
     * Método que ejecuta el programa AirUPM
     *
     * @param args argumentos con los que se inicia el programa
     */
    // Carga los datos de los ficheros CSV pasados por argumento (consola) en AirUPM, llama iterativamente al menú y realiza la
    //  opción especificada hasta que se indique la opción Salir, y finalmente guarda los datos de AirUPM en los mismos ficheros CSV
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AirUPM airUPM = new AirUPM(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        airUPM.cargarDatos(args[5], args[6], args[7], args[8], args[9]);
        Random random = new Random();
        ListaVuelos listaVuelos;
        Vuelo vuelo;
        int opcion;

        do {
            opcion = menu(scanner);
            switch (opcion) {
                case 0:
                    airUPM.guardarDatos(args[5], args[6], args[7], args[8], args[9]);
                    break;

                case 1:
                    if (!airUPM.maxVuelosAlcanzado())
                        Vuelo.altaVuelo(scanner, random, airUPM.listaAeropuertos, airUPM.listaAviones, airUPM.listaVuelos);
                    else System.out.println("No se pueden dar de alta más vuelos.");
                    break;

                case 2:
                    if (!airUPM.maxPasajerosAlcanzado()) {
                        Pasajero nuevoPasajero = Pasajero.altaPasajero(scanner, airUPM.listaPasajeros, airUPM.maxBilletesPasajero);
                        if (nuevoPasajero != null)
                            System.out.println("Pasajero con DNI " + nuevoPasajero.getDNI() + " dado de alta con éxito.");
                    } else System.out.println("No se pueden dar de alta más pasajeros.");
                    break;

                case 3:
                    listaVuelos = airUPM.buscarVuelo(scanner);
                    if (listaVuelos.getOcupacion() != 0) {
                        listaVuelos.listarVuelos();
                        vuelo = listaVuelos.seleccionarVuelo(scanner, "Ingrese ID de vuelo para comprar billete o escriba CANCELAR:", "CANCELAR");
                        if (vuelo != null){
                            airUPM.comprarBillete(scanner, random, vuelo);
                        }
                    } else System.out.println("No se ha encontrado ningún vuelo.");
                    break;

                case 4:
                    Pasajero pasajero = airUPM.listaPasajeros.seleccionarPasajero(scanner, "Ingrese DNI del pasajero:");
                    if (pasajero.numBilletesComprado() != 0) {
                        pasajero.getListaBilletesPasajero().listarBilletes();
                        Billete billete = pasajero.getListaBilletesPasajero().seleccionarBillete(scanner, "Ingrese localizador del billete:");
                        char character;

                        do {
                            //Sólo se aceptan respuestas en minúsculas
                            character = Utilidades.leerLetra(scanner, "¿Generar factura del billete (f), cancelarlo (c) o volver al menú (m)?", 'a', 'z');

                            //Acepta respuestas en mayúsculas y minúsculas indistintamente
                            do {
                                System.out.print("¿Generar factura del billete (f), cancelarlo (c) o volver al menú (m)?");
                                character = Character.toLowerCase(scanner.nextLine().charAt(0));
                            } while (character < 'a' || character > 'z');

                            if (character != 'f' && character != 'c' && character != 'm') System.out.println("El valor de entrada debe ser 'f', 'c' o 'm'");
                        } while (character != 'f' && character != 'c' && character != 'm');

                        if (character == 'f') {
                            System.out.print("Introduzca la ruta donde generar la factura:");
                            String rutaFichero = scanner.nextLine();
                            billete.generarFactura(rutaFichero);
                            System.out.println(" generada en " + rutaFichero);
                        } else if (character == 'c') {
                            String localizadorBillete = billete.getLocalizador();
                            if (billete.cancelar()) System.out.println("Billete " + localizadorBillete + " cancelado.");
                        }
                    } else System.out.println("El pasajero seleccionado no ha adquirido ningún billete.");
                    break;

                case 5:
                    Vuelo vuelo2 = airUPM.listaVuelos.seleccionarVuelo(scanner, "Ingrese ID del vuelo:","CANCELAR");
                    if (vuelo2 != null){
                        System.out.print("Introduzca la ruta donde generar la lista de pasajeros:");
                        String rutaVuelo = scanner.nextLine();
                        if (vuelo2.generarListaPasajeros(rutaVuelo)){
                            System.out.println("Lista de pasajeros del Vuelo " + vuelo2.getID() + " generada en " + rutaVuelo);
                        }
                    } else System.out.println("No se ha encontrado ningún vuelo.");
                    break;
            }
        } while (opcion != 0);
    }
}