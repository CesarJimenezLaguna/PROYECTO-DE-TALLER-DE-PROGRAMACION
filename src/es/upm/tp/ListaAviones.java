package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class ListaAviones {

    /**
     * Constructor of the class
     *
     * @param capacidad
     */
    public ListaAviones(int capacidad);
    public int getOcupacion();
    public boolean estaLlena();
    public Avion getAvion(int posicion);
    public boolean insertarAvion(Avion avion);
    public Avion buscarAvion(String matricula);
    // Permite seleccionar un avión existente a partir de su matrícula, y comprueba si dispone de un alcance mayor o igual que el pasado como argumento,
    // usando el mensaje pasado como argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente la matrícula del avión hasta que se introduzca uno con alcance suficiente
    public Avion seleccionarAvion(Scanner teclado, String mensaje, double alcance);
    // Genera un fichero CSV con la lista de aviones, sobreescribiendolo
    public boolean escribirAvionesCsv(String nombre);

    //Métodos estáticos
    // Genera una lista de aviones a partir del fichero CSV, usando el argumento como   
    // capacidad máxima de la lista
    public static ListaAviones leerAvionesCsv(String fichero, int capacidad);
}
