package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaAeropuertos {


    /**
     * Constructor of the class
     *
     * @param capacidad
     */
    public ListaAeropuertos(int capacidad);
    public int getOcupacion();
    public boolean estaLlena();
    public Aeropuerto getAeropuerto(int i);
    public boolean insertarAeropuerto(Aeropuerto aeropuerto);
    public Aeropuerto buscarAeropuerto(String codigo);
    // Permite seleccionar un aeropuerto existente a partir de su código, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente el código hasta que se introduzca uno correcto
    public Aeropuerto seleccionarAeropuerto(Scanner teclado, String mensaje);
    // Genera un fichero CSV con la lista de aeropuertos, sobreescribiendolo
    public boolean escribirAeropuertosCsv(String nombre);

    //Métodos estáticos
    //Genera una lista de aeropuertos a partir del fichero CSV, usando el argumento como   
    //capacidad máxima de la lista
    public static ListaAeropuertos leerAeropuertosCsv(String fichero, int capacidad);
}
