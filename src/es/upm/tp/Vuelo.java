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
    public Billete buscarBillete(int fila, int columna);
    //Si está desocupado el asiento que indica el billete, lo pone ocupado y devuelve true, si no devuelve false
    public boolean ocuparAsiento(Billete billete);
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
    public boolean generarListaPasajeros(String fichero);

    //Métodos estáticos

    //Genera un ID de vuelo. Este consistirá en una cadena de 6 caracteres, de los cuales los dos 
    //primeros serán PM y los 4 siguientes serán números aleatorios. Ejemplo: PM0123
    //NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.
    public static String generarID(Random rand);
    //Crea y devuelve un objeto Vuelo de los datos que selecciona el usuario de aeropuertos y aviones y la restricción de que
    //no puede estar repetido el identificador, siguiendo las indicaciones del enunciado
    //La función solicita repetidamente los parametros hasta que sean correctos
    public static Vuelo altaVuelo(Scanner teclado, Random rand, ListaAeropuertos aeropuertos, ListaAviones aviones, ListaVuelos vuelos);
}