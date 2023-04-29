package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Utilidades {
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo);
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo);
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo);
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo);
    // Solicita una fecha repetidamente hasta que se introduzca una correcta
    public static Fecha leerFecha(Scanner teclado, String mensaje);
    // Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
    public static Fecha leerFechaHora(Scanner teclado, String mensaje);
}
