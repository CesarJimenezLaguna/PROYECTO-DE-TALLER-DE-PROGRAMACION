package es.upm.tp;

/**
 * Utilidades es una clase con la funcionalidad de leer números (fecha) y letras dependiendo del tipo de variable (int, double...).
 * Clase utilizada para números de tipo int long y double y asi como para leer letras de tipo char.
 * Se recomienda utilizar esta clase para definir cifras, mensajes, asi como fechas (siendo estas introducidas por un usuario)
 * como la fecha de salida o llegada de un vuelo.
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class Utilidades {
    /**
     * Función que lee un número de tipo int pasado por parámetro
     *
     * @param teclado Teclado por donde el usuario ficilita la información
     * @param mensaje Mensaje que se le muestra al usuario de la información que tiene que aportar
     * @param minimo  límite inferior que se permite de los datos pasados por parámetro
     * @param maximo  límite superior que se permite de los datos pasados por parámetro
     * @return Devuelve el carácter que cumple los límites y que ha introducido el usuario
     */
    // Solicita un número repetidamente hasta que se introduzca uno correcto (dentro de los límites)
    public static int leerNumero(Scanner teclado, String mensaje, int minimo, int maximo);
    public static long leerNumero(Scanner teclado, String mensaje, long minimo, long maximo);
    public static double leerNumero(Scanner teclado, String mensaje, double minimo, double maximo);
    public static char leerLetra(Scanner teclado, String mensaje, char minimo, char maximo);
    // Solicita una fecha repetidamente hasta que se introduzca una correcta
    public static Fecha leerFecha(Scanner teclado, String mensaje);
    // Solicita una fecha y hora repetidamente hasta que se introduzcan unas correctas
    public static Fecha leerFechaHora(Scanner teclado, String mensaje) {
        int hora, minuto, segundo, dia, mes, anio;
        System.out.println(mensaje);
        do {
            dia = leerNumero(teclado, "Ingrese día:", 1, 31);
            mes = leerNumero(teclado, "Ingrese mes:", 1, 12);
            anio = leerNumero(teclado, "Ingrese año:", 1900, 3000);
            hora = leerNumero(teclado, "Ingrese hora", 0, 23);
            minuto = leerNumero(teclado, "Ingrese minuto:", 0, 59);
            segundo = leerNumero(teclado, "Ingrese segundo:", 0, 59);

            if (!Fecha.comprobarFecha(dia, mes, anio) || !Fecha.comprobarHora(hora, minuto, segundo)) {
                System.out.println("Fecha u hora introducida incorrecta.");
            }

        } while (!Fecha.comprobarFecha(dia, mes, anio) || !Fecha.comprobarFecha(hora, minuto, segundo));
        return new Fecha(dia, mes, anio, hora, minuto, segundo);
    }
}
