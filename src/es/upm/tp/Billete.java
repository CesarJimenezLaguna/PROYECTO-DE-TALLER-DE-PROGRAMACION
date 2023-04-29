package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Billete {
    enum TIPO { TURISTA, PREFERENTE, PRIMERA }

    /**
     * Constructor of the class
     *
     * @param localizador
     * @param vuelo
     * @param pasajero
     * @param tipo
     * @param fila
     * @param columna
     * @param precio
     */
    public Billete(String localizador, Vuelo vuelo, Pasajero pasajero, TIPO tipo, int fila, int columna, double precio);
    public String getLocalizador();
    public Vuelo getVuelo();
    public Pasajero getPasajero();
    public TIPO getTipo();
    public int getFila();
    public int getColumna();
    // Ejemplos: "1A" para el asiento con fila 1 y columna 1, "3D" para el asiento con fila 3 y columna 4
    public String getAsiento();
    public double getPrecio();
    // Texto que debe generar: Billete PM1111AAAA para Vuelo PM1111 de MAD T4 (24/12/2022 12:35:00) a BCN T1 (24/12/2022 14:05:30) en asiento 6C (TURISTA) por 100.00€
    public String toString();
    // Cancela este billete, eliminandolo de la lista de billetes del vuelo y del pasajero correspondiente
    public boolean cancelar();
    // Imprime la informacion de este billete en un fichero siguiendo el formato de los ejemplos de ejecución del enunciado
    public boolean generarFactura(String fichero);

    // Métodos estáticos

    // Genera un localizador de billete. Este consistirá en una cadena de 10 caracteres, de los cuales los seis 
    // primeros será el ID del vuelo asociado y los 4 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
    // NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.  
    public static String generarLocalizador(Random rand, String idVuelo);
    // Crea un nuevo billete para un vuelo y pasajero específico, pidiendo por teclado los datos necesarios al usuario en el orden y 
    // con los textos indicados en los ejemplos de ejecución del enunciado
    // La función solicita repetidamente los parametros hasta que sean correctos
    public static Billete altaBillete(Scanner teclado, Random rand, Vuelo vuelo, Pasajero pasajero);
}