package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaBilletes {
    /**
     * Constructor of the class
     *
     * @param capacidad
     */
    public ListaBilletes(int capacidad);
    public int getOcupacion();
    public boolean estaLlena();
    public Billete getBillete(int i);
    public boolean insertarBillete (Billete billete);
    public Billete buscarBillete (String localizador);
    public Billete buscarBillete (String idVuelo, int fila, int columna);
    public boolean eliminarBillete (String localizador);
    // Muestra por pantalla los billetes de la lista
    public void listarBilletes();
    // Permite seleccionar un billete existente a partir de su localizador, usando el mensaje pasado como argumento para la solicitud
    // y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente hasta que se introduzca un localizador correcto
    public Billete seleccionarBillete(Scanner teclado, String mensaje);
    // Añade los billetes al final de un fichero CSV, sin sobreescribirlo
    public boolean aniadirBilletesCsv(String fichero);

    // Métodos estáticos
    
    // Lee los billetes del fichero CSV y los añade a las lista de sus respectivos Vuelos y Pasajeros
    public static void leerBilletesCsv(String ficheroBilletes, ListaVuelos vuelos, ListaPasajeros pasajeros);
}
