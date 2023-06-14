package es.upm.tp;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa un Vuelo. Esta misma contiene los atributos que lo componen y
 * los métodos necesarios para replicar las funciones del mismo, es decir, ocupar y desocupar asientos
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version     1.0
 */
public class Vuelo {

    /**
     * Matriz que indica los asientos libres del vuelo
     */
    private boolean[][] asientos;

    /**
     * ID del Vuelo
     */
    private String id;

    /**
     * Avión del vuelo
     */
    private Avion avion;

    /**
     * Aeropuerto de Origen
     */
    private Aeropuerto origen;

    /**
     * Terminal del inicio del vuelo, TerminalOrigen
     */
    private int terminalOrigen;

    /**
     * Fecha de salida del vuelo
     */
    private Fecha salida;

    /**
     * Aeropuerto de Destino
     */
    private Aeropuerto destino;

    /**
     * Terminal de din del vuelo, TerminalDestino
     */
    private int terminalDestino;

    /**
     * Fecha de llegada del vuelo
     */
    private Fecha llegada;

    /**
     * Precio de un asiento standard
     */
    private double precio;

    /**
     * Lista de billetes del vuelo
     */
    private ListaBilletes listaBilletesVuelo;

    /**
     * Constructor de la clase Vuelo
     * Inicializa los atributos y la matriz de los asientos del vuelo
     * @param id
     * @param avion
     * @param origen
     * @param terminalOrigen
     * @param salida
     * @param destino
     * @param terminalDestino
     * @param llegada
     * @param precio
     */
    public Vuelo(String id, Avion avion, Aeropuerto origen, int terminalOrigen, Fecha salida, Aeropuerto destino, int terminalDestino, Fecha llegada, double precio) {
        this.id = id;
        this.avion = avion;
        this.origen = origen;
        this.terminalOrigen = terminalOrigen;
        this.salida = salida;
        this.destino = destino;
        this.terminalDestino = terminalDestino;
        this.llegada = llegada;
        this.precio = precio;
        this.asientos = new boolean[avion.getFilas()][avion.getColumnas()];

        for(int i = 0; i < avion.getFilas(); i++){
            for(int k = 0; k < avion.getColumnas(); k++){
                asientos[i][k] = false;
            }
        }
        listaBilletesVuelo = new ListaBilletes(avion.getFilas() * avion.getColumnas());

    }

    public ListaBilletes getListaBilletesVuelo(){
        return listaBilletesVuelo;
    }

    /**
     * @return devuelve el id del vuelo
     */
    public String getID() {
        return id;
    }

    /**
     * @return devuelve el avión que se utiliza para el vuelo
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * @return devuelve el aeropuerto por el cual sale el avión
     */
    public Aeropuerto getOrigen() {
        return origen;
    }

    /**
     * @return devuelve la terminal de origen del vuelo
     */
    public int getTerminalOrigen() {
        return terminalOrigen;
    }

    /**
     * @return devuelve la fecha de salida del vuelo
     */
    public Fecha getSalida() {
        return salida;
    }

    /**
     * @return devuelve el aeropuerto de destino
     */
    public Aeropuerto getDestino() {
        return destino;
    }

    /**
     * @return devuelve la terminal de llegada del vuelo
     */
    public int getTerminalDestino() {
        return terminalDestino;
    }

    /**
     * @return devuelve la fecha de llegada del vuelo
     */
    public Fecha getLlegada() {
        return llegada;
    }

    /**
     * @return devuelve el precio de los asientos (standard)
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return devuelve el precio de los asientos preferentes
     */
    public double getPrecioPreferente() {
        return precio * 1.25;
    }

    /**
     * @return devuelve el precio de los asientos de primera clase
     */
    public double getPrecioPrimera() {
        return precio * 1.5;
    }

    /**
     * @return devuelve el número de asientos libres disponibles para ocupar
     */
    public int numAsientosLibres() {
        int asientosLibres = 0;
        for(int i = 0; i < avion.getFilas(); i++){
            for (int k = 0; k < avion.getColumnas(); k++){
                if (asientos[i][k] == false)
                    asientosLibres++;
            }
        }
        return asientosLibres;
    }

    /**
     * @return devuelve true si no quedan asientos libres y false si no quedan
     */
    public boolean vueloLleno() {
        boolean vueloLleno = false;
        if(numAsientosLibres() == 0) {
            vueloLleno = true;
        }
        return vueloLleno;
    }

    /**
     * Verifica si un asiento del vuelo (por filas y columnas) está ocupado
     * @param fila fila del asiento
     * @param columna columna del asiento
     * @return devuelve true si el asiento está ocupado y false si este mismo no lo esta
     */
    public boolean asientoOcupado(int fila, int columna) {
        return asientos[fila -1][columna - 1]; //quitamos 1 porque la matriz empieza en el 0
    }

    /**
     * Busca un billete, entre los de la lista de billetes del vuelo mediante un localizador introducido por parámetro
     * @param localizador localizador del billete a buscar
     * @return devuelve el billete que coincida con el introducido por parámetro, si no existe el billete devuelve null
     */
    public Billete buscarBillete(String localizador) {
        Billete localizado = listaBilletesVuelo.buscarBillete(localizador);
        return localizado;
    }

    /**
     * Devuelve el objeto billete que corresponde con una fila o columna, si esta libre o se excede en el límite de fila y columna devuelve null
     * @param fila fila del billete
     * @param columna columna del billete
     * @return devuelve el billete en la posición (fila y columna), si no existe un billete devuelve null
     */
    //Devuelve el obejeto billete que corresponde con una fila o columna,
    //Devolverá null si está libre o se excede en el límite de fila y columna
    public Billete buscarBillete(int fila, int columna) {
        Billete localizado = listaBilletesVuelo.buscarBillete(id, fila, columna);
        return localizado;
    }

    /**
     * Ocupa un asiento a partir de un billete introducido por parámetro
     * @param billete billete del vuelo (indica asiento a ocupar)
     * @return devuelve true si el asiento no estaba ocupado antes de invocar a la función
     */
    //Si está desocupado el asiento que indica el billete, lo pone ocupado y devuelve true, si no devuelve false
    public boolean ocuparAsiento(Billete billete) {
        boolean ocupado = false;

        if (!asientoOcupado(billete.getFila(), billete.getColumna())) {
            asientos[billete.getFila() - 1][billete.getColumna() - 1] = true; //true de ocupado
            ocupado = true;
        }
        return ocupado;
    }

    /**
     * Desocupa un asiento a partir de un billete introducido por parámetro
     * @param localizador localizador del billete (indica asiento a desocupar)
     * @return devuelve true si se ha desocupado el asiento correctamente y false si no se ha desocupado
     */
    //A traves del loalizador de billete, se desocupará el asiento
    public boolean desocuparAsiento(String localizador){
        boolean desocupado = false;
        Billete billete = listaBilletesVuelo.buscarBillete(localizador);
        if (asientoOcupado(billete.getFila(), billete.getColumna())) {
            asientos[billete.getFila() - 1][billete.getColumna() - 1] = false;
            desocupado = true;
        }
        return desocupado;
    }

    /**
     * Añade los billetes al final de un fichero CSV
     * @param fichero fichero en el que se añaden los billetes
     * @return devuelve true si se han añadido correctamente los billetes, false si no se han añadido
     */
    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero){
        return listaBilletesVuelo.aniadirBilletesCsv(fichero);
    }


    /**
     * @return devuelve un String con formato
     */
    // Devuelve una cadena con información completa del vuelo
    //Ejemplo: Vuelo PM0066 de Josep Tarradellas Barcelona-El Prat(BCN) T2 (01/01/2023 08:15:00) a Gran Canaria(LPA) T1 (01/01/2023 11:00:05) en Boeing 747(EC-LKD) por 182,52€, asientos libres: 409
    public String toString(){
        return "Vuelo " + id + " de " + origen.getNombre() + "(" + origen.getCodigo() + ") T" + terminalOrigen + " (" + salida
                + ") a " + destino.getNombre() + "(" + destino.getCodigo() + ") T" + terminalDestino + " (" + llegada + ") en "
                + avion.getMarca() + " " + avion.getModelo() + "(" + avion.getMatricula() + ") por " + String.format("%.2f",precio).replace(".", ",")
                + "€, asientos libres: " + numAsientosLibres();
    }

    /**
     * @return devuelve un String del vuelo (formato simplificado)
     */
    // Devuelve una cadena con información abreviada del vuelo
    //Ejemplo: Vuelo PM0066 de BCN T2 (01/01/2023 08:15:00) a LPA T1 (01/01/2023 11:00:05)
    public String toStringSimple(){
        return "Vuelo " + id + " de " + origen.getCodigo() + " T" + terminalOrigen + " (" + salida + ") a " + destino.getCodigo()
                + " T" + terminalDestino + " (" + llegada + ")";
    }

    /**
     * Devuelve true si el código origen, destino y la fecha coinciden con la del vuelo
     * @param codigoOrigen codigo del aeropuerto de origen (comprobar)
     * @param codigoDestino codigo del aeropuerto de destino (comprobar)
     * @param fecha fecha a comprobar
     * @return Devuelve true si el código origen, destino y fecha
     */
    //Devuelve true si el código origen, destino y fecha son los mismos que el vuelo
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha){
        boolean correcto = false;
        if (origen.getCodigo().equals(codigoOrigen) && destino.getCodigo().equals(codigoDestino) && salida.coincide(fecha)){
            correcto = true;
        }
        return correcto;
    }

    /**
     * // Muestra la matriz  de asientos del vuelo, ejemplo:
     *     //   A  B  C  D  E  F
     *     // 1( )(X)( )( )( )( )
     *     // 2{X}{X}{ }{ }{ }{ }
     *     // 3{ }{ }{ }{X}{X}{X}
     *     // 4{ }{ }{ }{ }{ }{ }
     *     // 5{ }{ }{X}{ }{ }{ }
     *     // 6[ ][ ][ ][ ][ ][ ]
     *     // 7[X][X][X][ ][ ][ ]
     *     // 8[ ][ ][ ][ ][ ][ ]
     *     // 9[ ][X][ ][ ][ ][X]
     *     //10[ ][ ][ ][ ][ ][ ]
     */
    public void imprimirMatrizAsientos(){
        System.out.print("    ");
        for (int i = 1; i <= avion.getColumnas(); i++) {
            System.out.printf("%c  ", 64 + i);
        }
        System.out.println();
        System.out.printf("  1");
        for (int j = 1; j <= avion.getColumnas(); j++) {
            if (asientoOcupado(1, j)) {
                System.out.print("(X)");
            } else {
                System.out.print("( )");
            }
        }
        System.out.println();
        for (int f = 2; f < 6; f++) {
            System.out.printf(" %2d", f);
            for (int c = 1; c <= avion.getColumnas(); c++){
                if (asientoOcupado(f, c)) {
                    System.out.print("{X}");
                } else {
                    System.out.print("{ }");
                }
            }
            System.out.println();
        }
        for (int f = 6; f <= avion.getFilas(); f++) {
            System.out.printf(" %2d", f);
            for (int c = 1; c <= avion.getColumnas(); c++){
                if (asientoOcupado(f, c)) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    /**
     * Genera una lista con los pasajeros del vuelo (con formato)
     * @param fichero fichero donde se quiere generar la lista con los pasajeros
     * @return devuelve true si ha podido escribir en el fichero la lista de pasajeros del vuelo
     */
    //Devuelve true si ha podido escribir en un fichero la lista de pasajeros del vuelo, siguiendo las indicaciones del enunciado
    public boolean generarListaPasajeros(String fichero) {
        boolean ficheroActualizado = false;
        Billete billete = null;
        FileWriter fileWriter = null;
        char[] letraAsiento = {'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            fileWriter = new FileWriter(fichero, true);
            fileWriter.write("--------------------------------------------------\n");
            fileWriter.write("--------- Lista de pasajeros en vuelo " + this.id + " ---------\n");
            fileWriter.write("--------------------------------------------------\n");
            fileWriter.write("Asiento Tipo        Pasajero");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 6; j++) {
                    if (asientos[i + 1][j + 1]) {
                        fileWriter.write(i + "" + letraAsiento[j] + "       " + (billete.getTipo() == Billete.TIPO.PREFERENTE ? billete.getTipo().toString() : billete.getTipo().toString() + "   ") + "  " +
                                billete.getPasajero().toString());
                    } else {
                        fileWriter.write(i + "" + letraAsiento[j]);
                    }
                }
            }
        } catch (FileNotFoundException excepcion1) {
            System.out.println("El fichero " + fichero + " no encontrado");
            ficheroActualizado = false;
        } catch (IOException excepcion2) {
            System.out.println("Error de escritura en fichero " + fichero + ".");
            ficheroActualizado = false;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException excepcion3) {
                    System.out.println("Error de cierre del fichero " + fichero + ".");
                    ficheroActualizado = false;
                }
            }
        }
        return ficheroActualizado;
    }

    //Métodos estáticos

    /**
     * Genera id aleatorios
     * @param rand parámetro aleatorio
     * @return devuelve id's aleatorios para el vuelo
     */
    //Genera un ID de vuelo. Este consistirá en una cadena de 6 caracteres, de los cuales los dos
    //primeros serán PM y los 4 siguientes serán números aleatorios. Ejemplo: PM0123
    //NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
    public static String generarID(Random rand){
        return String.format("PM%04d", rand.nextInt(9999));

        /*
        String idaGenerar;
        int a1 = rand.nextInt(10);
        int a2 = rand.nextInt(10);
        int a3 = rand.nextInt(10);
        int a4 = rand.nextInt(10);
        idaGenerar = "PM" + a1 + "" + a2 + "" + a3 + "" + a4;
        return idaGenerar;

        */
    }


    /**
     * Da de alta a un vuelo con un avión que va de un aeropuerto a otro
     * Cada vuelo tiene sus atributos correspondientes, si se pasa algun parametro mal, se pregunta nuevamente
     * @param teclado pasa por parámetro el teclado para no tener que declararlo
     * @param rand número aleatorio para generar el ID del vuelo
     * @param aeropuertos lista de aeropuertos entre los que puede ir el avión
     * @param aviones lista de aviones que pueden usarse en el vuelo
     * @param vuelos lista de vuelos que ya existen para no dar la misma id a dos vuelos distintos
     * @return devuelve el vuelo que se acaba de crear
     */
    //Crea y devuelve un objeto Vuelo de los datos que selecciona el usuario de aeropuertos y aviones y la restricción de que
    //no puede estar repetido el identificador, siguiendo las indicaciones del enunciado
    //La función solicita repetidamente los parametros hasta que sean correctos
    public static Vuelo altaVuelo(Scanner teclado, Random rand, ListaAeropuertos aeropuertos, ListaAviones aviones, ListaVuelos vuelos) {
        if (!vuelos.estaLlena()) {
            boolean vueloCorrecto = false;
            String vueloID;

            do {
                vueloID = generarID(rand);
                for (int i = 0; i < vuelos.getOcupacion(); i++) {
                    if (vueloID.equals(vuelos.getVuelo(i + 1).getID())) {
                        vueloCorrecto = true;
                    }
                }
            } while (vueloCorrecto);

            Aeropuerto origen = aeropuertos.seleccionarAeropuerto(teclado, "Ingrese código de Aeropuerto Origen:");
            String primerMensaje = "Ingrese Terminal Origen (1 - " + origen.getTerminales() + "):";
            int terminalOrigen = Utilidades.leerNumero(teclado, primerMensaje, 1, origen.getTerminales());

            Aeropuerto destino = aeropuertos.seleccionarAeropuerto(teclado, "Ingrese código de Aeropuerto Destino:");
            String segundoMensaje = "Ingrese Terminal Destino (1 - " + destino.getTerminales() + "):";
            int terminalDestino = Utilidades.leerNumero(teclado, segundoMensaje, 1, destino.getTerminales());

            double distancia = origen.distancia(destino);
            Avion avion = aviones.seleccionarAvion(teclado, "Ingrese matrícula de Avión:", distancia);
            Fecha llegada;
            Fecha salida;
            boolean fechaCorrecta = true;

            do{
                salida = Utilidades.leerFechaHora(teclado, "Fecha de Salida:");
                llegada = Utilidades.leerFechaHora(teclado, "Fecha de Llegada:");

                if (salida.anterior(llegada)) {
                    fechaCorrecta = true;
                }
                else {
                    System.out.println("Llegada debe ser posterior a salida.");
                    fechaCorrecta = false;
                }
            }while(!fechaCorrecta);

            double precio = Utilidades.leerNumero(teclado, "Ingrese precio de pasaje:",0, 999);
            Vuelo resultado = new Vuelo(vueloID, avion, origen, terminalOrigen, salida, destino, terminalDestino, llegada, precio);
            System.out.println("Vuelo " + vueloID + " creado con éxito.");
            vuelos.insertarVuelo(resultado);
            return resultado;
        }
        else{
            System.out.println("No se pueden dar de alta más vuelos.");
            return null;
        }
    }
}