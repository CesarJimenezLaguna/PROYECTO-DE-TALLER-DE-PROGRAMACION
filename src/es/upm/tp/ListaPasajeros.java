package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaPasajeros {

    /**
     * Constructor of the class
     *
     * @param capacidad
     */
    public ListaPasajeros(int capacidad);
    public int getOcupacion();
    public boolean estaLlena();
    public Pasajero getPasajero(int i);
    public boolean insertarPasajero(Pasajero pasajero);
    public Pasajero buscarPasajeroDNI(String dni);
    public Pasajero buscarPasajeroEmail(String email);
    // Permite seleccionar un pasajero existente a partir de su DNI, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente hasta que se introduzca un DNI correcto
    public Pasajero seleccionarPasajero(Scanner teclado, String mensaje);
    // Genera un fichero CSV con la lista de pasajeros, sobreescribiendolo
    public boolean escribirPasajerosCsv(String fichero);

    //Métodos estáticos
    // Genera una lista de pasajeros a partir del fichero CSV, usando los límites especificados como argumentos para la capacidad
    // de la lista y el número de billetes máximo por pasajero
    public static ListaPasajeros leerPasajerosCsv(String fichero, int capacidad, int maxBilletesPasajero);
}