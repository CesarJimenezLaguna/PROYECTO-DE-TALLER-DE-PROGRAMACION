package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Vuelo {
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
     * Constructor of the class
     *
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
    public Vuelo(String id, Avion avion, Aeropuerto origen, int terminalOrigen, Fecha salida, Aeropuerto destino, int terminalDestino, Fecha llegada, double precio);
    public String getID();
    public Avion getAvion();
    public Aeropuerto getOrigen();
    public int getTerminalOrigen();
    public Fecha getSalida();
    public Aeropuerto getDestino();
    public int getTerminalDestino();
    public Fecha getLlegada();
    public double getPrecio();
    public double getPrecioPreferente();
    public double getPrecioPrimera();
    public int numAsientosLibres();
    public boolean vueloLleno();
    public boolean asientoOcupado(int fila, int columna);
    public Billete buscarBillete(String localizador);
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
    public boolean desocuparAsiento(String localizador);
    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero);
    // Devuelve una cadena con información completa del vuelo
    //Ejemplo: Vuelo PM0066 de Josep Tarradellas Barcelona-El Prat(BCN) T2 (01/01/2023 08:15:00) a Gran Canaria(LPA) T1 (01/01/2023 11:00:05) en Boeing 747(EC-LKD) por 182,52€, asientos libres: 409
    public String toString();
    // Devuelve una cadena con información abreviada del vuelo
    //Ejemplo: Vuelo PM0066 de BCN T2 (01/01/2023 08:15:00) a LPA T1 (01/01/2023 11:00:05)
    public String toStringSimple();
    //Devuelve true si el código origen, destino y fecha son los mismos que el vuelo
    public boolean coincide(String codigoOrigen, String codigoDestino, Fecha fecha);
    // Muestra la matriz  de asientos del vuelo, ejemplo:
    //   A  B  C  D  E  F
    // 1( )(X)( )( )( )( )
    // 2{X}{X}{ }{ }{ }{ }
    // 3{ }{ }{ }{X}{X}{X}
    // 4{ }{ }{ }{ }{ }{ }
    // 5{ }{ }{X}{ }{ }{ }
    // 6[ ][ ][ ][ ][ ][ ]
    // 7[X][X][X][ ][ ][ ]
    // 8[ ][ ][ ][ ][ ][ ]
    // 9[ ][X][ ][ ][ ][X]
    //10[ ][ ][ ][ ][ ][ ]
    public void imprimirMatrizAsientos();
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

    //Genera un ID de vuelo. Este consistirá en una cadena de 6 caracteres, de los cuales los dos 
    //primeros serán PM y los 4 siguientes serán números aleatorios. Ejemplo: PM0123
    //NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
    public static String generarID(Random rand);
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