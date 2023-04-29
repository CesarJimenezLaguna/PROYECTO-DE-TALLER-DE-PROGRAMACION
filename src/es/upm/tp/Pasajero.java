package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Pasajero {

    /**
     * Constructor of the class
     *
     * @param nombre
     * @param apellidos
     * @param numeroDNI
     * @param letraDNI
     * @param email
     */
    public Pasajero(String nombre, String apellidos, long numeroDNI, char letraDNI, String email, int maxBilletes);
    public String getNombre();
    public String getApellidos();
    public long getNumeroDNI();
    public char getLetraDNI();
    // Ejemplo: 00123456S
    public String getDNI();
    public String getEmail();
    // Texto que debe generar: Víctor Rodríguez Fernández, 00123456S, victor.rfernandez@upm.es
    public String toString();
    public int numBilletesComprado();
    public boolean maxBilletesAlcanzado();
    public Billete getBillete(int i);
    public boolean aniadirBillete(Billete billete);
    public Billete buscarBillete(String localizador);
    // Elimina el billete de la lista de billetes del pasajero
    public boolean cancelarBillete(String localizador);
    public void listarBilletes();
    // Encapsula la funcionalidad seleccionarBillete de ListaBilletes
    public Billete seleccionarBillete(Scanner teclado, String mensaje);
    //Métodos estáticos
    // 
    // Crea un nuevo pasajero no repetido, pidiendo por teclado los datos necesarios al usuario en el orden 
    // y con los textos indicados en los ejemplos de ejecución del enunciado
    // La función solicita repetidamente los parametros hasta que sean correctos
    public static Pasajero altaPasajero(Scanner teclado, ListaPasajeros pasajeros, int maxBilletes);
    // Correcto: 00123456 S, incorrectos: 123456789 A, 12345678 0, 12345678 A
    public static boolean correctoDNI(long numero, char letra);
    // Correcto: cristian.ramirez@upm.es, incorrecto: cristian.ramirez@gmail.com, cristian-23@upm.es, cristian.@upm.es
    public static boolean correctoEmail(String email);
}
