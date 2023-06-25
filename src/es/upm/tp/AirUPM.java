package es.upm.tp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version 1.0
 */
public class AirUPM {

    private int maxAeropuertos;

    private int maxAviones;

    private int maxVuelos;

    private int maxPasajeros;

    private int maxBilletesPasajero;

    private ListaAeropuertos listaAeropuertos = null;

    private ListaAviones listaAviones = null;

    private ListaVuelos listaVuelos = null;

    private ListaPasajeros listaPasajeros = null;

    /**
     * Constructor de la clase AirUPM
     * Inicializa los atributos correspondientes, dentro de los parametros utilizados.
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

    // Lee los datos de los ficheros especificados y los agrega a AirUPM
    public void cargarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes) {
        listaAeropuertos = ListaAeropuertos.leerAeropuertosCsv(ficheroAeropuertos, maxAeropuertos);
        listaAviones = ListaAviones.leerAvionesCsv(ficheroAviones, maxAviones);
        listaVuelos = ListaVuelos.leerVuelosCsv(ficheroVuelos, maxVuelos, listaAeropuertos, listaAviones);
        listaPasajeros = ListaPasajeros.leerPasajerosCsv(ficheroPasajeros, maxPasajeros, maxBilletesPasajero);
        ListaBilletes.leerBilletesCsv(ficheroBilletes, listaVuelos, listaPasajeros);
    }

    // Almacena los datos de AirUPM en los ficheros CSV especificados
    public boolean guardarDatos(String ficheroAeropuertos, String ficheroAviones, String ficheroVuelos, String ficheroPasajeros, String ficheroBilletes){
        boolean ficheroAeropuerto = listaAeropuertos.escribirAeropuertosCsv(ficheroAeropuertos);
        boolean ficheroAvion = listaAviones.escribirAvionesCsv(ficheroAviones);
        boolean ficheroVuelo = listaVuelos.escribirVuelosCsv(ficheroVuelos);
        boolean ficheroPasajero = listaPasajeros.escribirPasajerosCsv(ficheroPasajeros);
        PrintWriter printWriterF = null;
        boolean datosGuardados = false;

        try {
            fileWriter = new FileWriter(ficheroBilletes);
            fileWriter.write("");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        } finally {
            if (printWriterF != null) printWriterF.close();
        }

        for (int i = 0; i < listaVuelos.getOcupacion(); i++){
            listaVuelos.getVuelo(i + 1).aniadirBilletesCsv(ficheroBilletes);
        }

        if (ficheroAeropuerto && ficheroAvion && ficheroVuelo && ficheroPasajero){
            datosGuardados = true;
        }
        return datosGuardados;
    }

    public boolean maxVuelosAlcanzado() {
        return maxVuelos == listaVuelos.getOcupacion();
    }

    public boolean insertarVuelo(Vuelo vuelo) {
        boolean insertado;
        if (maxVuelosAlcanzado()) insertado = false;
        else {
            listaVuelos.insertarVuelo(vuelo);
            insertado = true;
        }
        return insertado;
    }

    public boolean maxPasajerosAlcanzado() {
        return (maxPasajeros == listaPasajeros.getOcupacion());
    }

    public boolean insertarPasajero(Pasajero pasajero) {
        boolean insertado;
        if (maxPasajerosAlcanzado()) insertado = false;
        else {
            listaPasajeros.insertarPasajero(pasajero);
            insertado = true;
        }
        return insertado;
    }

    // Funcionalidad buscarVuelo especificada en el enunciado del proyecto, que devuelve una lista de vuelos entre dos aeropuertos y
    // con una fecha de salida solicitados por teclado al usuario en el orden y con los textos indicados en los ejemplos de
    // ejecución del enunciado
    public ListaVuelos buscarVuelo(Scanner teclado) {
        System.out.print("Ingrese código de Aeropuerto Origen:");
        String codigoAeropuertoOrigen = teclado.nextLine();
        System.out.print("Ingrese código de Aeropuerto Destino:");
        String codigoAeropuertoDestino = teclado.nextLine();
        Fecha fecha = Utilidades.leerFecha(teclado, "Fecha de salida:");
        return listaVuelos.buscarVuelos(codigoAeropuertoOrigen, codigoAeropuertoDestino, fecha);
    }

    // Funcionalidad comprarBillete especificada en el enunciado del proyecto, que compra un billete para un vuelo especificado,
    // pidiendo por teclado los datos necesarios al usuario en el orden y con los textos indicados en los ejemplos de ejecución del
    // enunciado. Si la lista de pasajeros está vacía, creará un nuevo pasajero, si está llena seleccionará un pasajero, en cualquier
    // otro caso, deberá preguntar al usuario si crear o seleccionar
    public void comprarBillete(Scanner teclado, Random rand, Vuelo vuelo);

    //Métodos estáticos

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
                            character = Utilidades.leerLetra(scanner, "¿Generar factura del billete (f), cancelarlo (c) o volver al menú (m)?", 'a', 'z');
                            if (character != 'f' && character != 'c' && character != 'm') System.out.println("El valor de entrada debe ser 'f', 'c' o 'm'");
                        } while (character != 'f' && character != 'c' && character != 'm');

                        if (character == 'f') {
                            System.out.print("Introduzca la ruta donde generar la factura:");
                            String rutaFichero = scanner.nextLine();
                            billete.generarFactura(rutaFichero);
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