package es.upm.tp;

/**
 * Billete es una clase que encapsula las variables enteras usadas para definir un billete concreto
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class Billete {

    /**
     * Atributo que contiene el enumerador TIPO, con los distintos tipos de billetes para comprar por los pasajeros
     */
    enum TIPO {TURISTA, PREFERENTE, PRIMERA}

    /**
     * Atributo que contiene el localizador de un billete
     */
    private String localizador;

    /**
     * Atributo que contiene el vuelo de donde se ha comprado un billete
     */
    private Vuelo vuelo;

    /**
     * Atributo que contiene el pasajero que compro un billete
     */
    private Pasajero pasajero;

    /**
     * Atributo que contiene el tipo de billete comprado (TURISTA, PREFERENTE, PRIMERA)
     */
    private TIPO tipo;

    /**
     * Atributo que contiene la fila del billete comprado
     */
    private int fila;

    /**
     * Atributo que contiene la columna del billete comprado
     */
    private int columna;

    /**
     * Atributo que contiene el precio del billete comprado
     */
    private double precio;

    /**
     * Atributo que contiene el la ocupación del billete comprado
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde se guardan los billetes (comprados o reservados) de un vuelo
     */
    private ListaBilletes listaBilletesVuelo;

    /**
     * Constructor que crea un pasajero con los parámetros que recibe
     *
     * @param localizador localizador del billete
     * @param vuelo       vuelo correspondiente al billete
     * @param pasajero    parajero que ha comprado el billete
     * @param tipo        tipo de billete
     * @param fila        fila del avión
     * @param columna     columna del avión
     * @param precio      precio del billete
     */
    public Billete(String localizador, Vuelo vuelo, Pasajero pasajero, TIPO tipo, int fila, int columna, double precio) {
        this.localizador = localizador;
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
        this.precio = precio;
        ocupacion = 0;
        listaBilletesVuelo = new ListaBilletes(vuelo.getAvion().getFilas() * vuelo.getAvion().getColumnas());
    }

    /**
     * Getter del atributo localizador
     *
     * @return Devuelve el localizador de un billete
     */
    public String getLocalizador() {
        return localizador;
    }

    /**
     * Getter del atributo vuelo
     *
     * @return Devuelve el vuelo del billete comprado
     */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /**
     * Getter del atributo pasajero
     *
     * @return Devuelve el pasajero que compro el billete
     */
    public Pasajero getPasajero() {
        return pasajero;
    }

    /**
     * Getter del atributo tipo
     *
     * @return Devuelve el tipo de billete comprado
     */
    public TIPO getTipo() {
        return tipo;
    }

    /**
     * Getter del atributo fila
     *
     * @return Devuelve la fila del asiento del billete comprado
     */
    public int getFila() {
        return columna;
    }

    /**
     * Getter del atributo columna
     *
     * @return Devuelve la columna del asiento del billete comprado
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Getter del para conseguir un asiento dependiendo de una fila y una columna
     *
     * @return Devuelve el asiento de un pasajero dependiendo de la fila y la columna seleccionada
     */
    // Ejemplos: "1A" para el asiento con fila 1 y columna 1, "3D" para el asiento con fila 3 y columna 4
    public String getAsiento() {
        char[] columna = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        int numcolumna = getColumna();
        return getFila() + "" + columna[numcolumna - 1];
    }

    /**
     * Getter del atributo precio
     *
     * @return Devuelve el precio de un billete (dependiedo del tipo del mismo comprado)
     */
    public double getPrecio() {
        if (tipo == TIPO.PRIMERA) {
            precio = precio * 1.5;
        }
        if (tipo == TIPO.PREFERENTE) {
            precio = precio + 1.25;
        }
        return precio;
    }

    /**
     * Función que muestra la información completa del billete de una forma específica según el enunciado
     *
     * @return Devuelve un String con toda la información del billete
     */
    // Texto que debe generar: Billete PM1111AAAA para Vuelo PM1111 de MAD T4 (24/12/2022 12:35:00) a BCN T1 (24/12/2022 14:05:30) en asiento 6C (TURISTA) por 100.00€
    public String toString() {
        return "Billete " + localizador + " para Vuelo " + vuelo.getID() + " de " + vuelo.getOrigen().getCodigo() + " T" + vuelo.getTerminalOrigen() + " (" + vuelo.getSalida().toString() + ") a "
                + vuelo.getDestino().getCodigo() + " T" + vuelo.getTerminalDestino() + " (" + vuelo.getLlegada().toString() + ") en asiento " +
                getAsiento() + " (" + getTipo() + ") por " + String.format("%.2f", getPrecio()).replace(".", ",") + "€";
    }

    /**
     * Función que cancela el billete y lo elimina de la lista y del pasajero al que correspondía
     *
     * @return Devuelve True si se ha podido cancelar y false si no se ha podido
     */
    // Cancela este billete, eliminandolo de la lista de billetes del vuelo y del pasajero correspondiente
    public boolean cancelar();

    /**
     * Función que imprime la información del billete en un fichero siguiendo los ejemplos del enunciado
     *
     * @param fichero Fichero en el que se escribe la información del billete
     * @return Devuelve True si se ha escrito en el fichero y false si no se ha podido
     */
    // Imprime la informacion de este billete en un fichero siguiendo el formato de los ejemplos de ejecución del enunciado
    public boolean generarFactura(String fichero);

    // Métodos estáticos

    /**
     * Función que genera un localizador para un billete concreto
     *
     * @param rand    Objeto para que el localizador generado sea aleatorio
     * @param idVuelo ID del vuelo al que esta asociado el billete
     * @return Devuelve el localizador del billete en el que se encuentra el ID del vuelo
     */
    // Genera un localizador de billete. Este consistirá en una cadena de 10 caracteres, de los cuales los seis
    // primeros será el ID del vuelo asociado y los 4 siguientes serán letras mayúsculas aleatorias. Ejemplo: PM0123ABCD
    // NOTA: Usar el objeto rand pasado como argumento para la parte aleatoria.  
    public static String generarLocalizador(Random rand, String idVuelo);

    /**
     * Función que crea un nuevo billete para un vuelo y pasajero específico pidiendo por teclado los datos necesarios al usuario como el vuelo y el pasajero
     *
     * @param teclado  Teclado por donde pasa el usuario la información requerida
     * @param rand     Parámetro para generar un localizador aleatorio del billete
     * @param vuelo    Vuelo del cual se ha comprado el billete
     * @param pasajero Pasajero que comprar el billete y al que esta asociado este
     * @return Devuelve el billete que se ha creado con los datos pasados por parámetro
     */
    // Crea un nuevo billete para un vuelo y pasajero específico, pidiendo por teclado los datos necesarios al usuario en el orden y
    // con los textos indicados en los ejemplos de ejecución del enunciado
    // La función solicita repetidamente los parametros hasta que sean correctos
    public static Billete altaBillete(Scanner teclado, Random rand, Vuelo vuelo, Pasajero pasajero);
}