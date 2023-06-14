package es.upm.tp;

import javax.swing.plaf.TreeUI;
import java.util.IllegalFormatCodePointException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Pasajero es una clase que encapsula las variables enteras usadas para definir un pasajero concreto
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */
public class Pasajero {

    /**
     * Atributo que contiene el  nombre del pasajero
     */
    private String nombre;

    /**
     * Atributo que contiene los apellidos del pasajero
     */
    private String apellidos;

    /**
     * Atributo que contiene la parte numérica del DNI del pasajero
     */
    private long numeroDNI;

    /**
     * Atributo que contiene la parte alfabética del DNI del pasajero
     */
    private char letraDNI;

    /**
     * Atributo que contiene el email del pasajero
     */
    private String email;

    /**
     * Atributo que contiene la cantindad máxima de billetes que se pueden comprar en un vuelo
     */
    private int maxBilletes;

    /**
     * Atributo que contiene el array donde se guardan los billetes (comprados) de un pasajero
     */
    private ListaBilletes listaBilletesPasajeros;

    /**
     * Constructor que crea un pasajero con los parámetros (nombre, apellidos, numero del DNI, letra del DNI y email) que recibe.
     *
     * @param nombre    nombre del pasajero
     * @param apellidos apellidos del pasajero
     * @param numeroDNI número del DNI del pasajero
     * @param letraDNI  letra del DNI del pasajero
     * @param email     email del pasajero
     */
    public Pasajero(String nombre, String apellidos, long numeroDNI, char letraDNI, String email, int maxBilletes) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroDNI = numeroDNI;
        this.letraDNI = letraDNI;
        this.email = email;
        this.maxBilletes = maxBilletes;
        listaBilletesPasajeros = new ListaBilletes(maxBilletes);
    }

    /**
     * Getter del atributo nombre
     *
     * @return Devuelve el nombre del pasajero
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del atributo apellidos
     *
     * @return Devuelve los apellidos del pasajero
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Getter del atributo numeroDNI
     *
     * @return Devuelve la parte numérica del DNI  de un pasajero
     */
    public long getNumeroDNI() {
        return numeroDNI;
    }

    /**
     * Getter del atributo letraDNI
     *
     * @return Devuelve la parte alfabética del DNI de un pasajero
     */
    public char getLetraDNI() {
        return letraDNI;
    }

    /**
     * Getter del atributo DNI
     *
     * @return Devuelve el DNI completo de un pasajero (numeroDNI + letraDNI), en un formato específico (00123456S)
     */
    public String getDNI() { // Ejemplo: 00123456S
        return String.format("%08d", numeroDNI) + String.valueOf(letraDNI); // Rellena con 0s
    }

    /**
     * Getter del atributo email
     *
     * @return Devuelve el email de un pasajero
     */
    public String getEmail() {
        return email;
    }

    public ListaBilletes getListaBilletesPasajero(){
        return this.listaBilletesPasajeros;
    }

    /**
     * Función que imprime la información de un pasajero con un formato específico,
     * ("Víctor Rodríguez Fernández, 00123456S, victor.rfernandez@upm.es")
     *
     * @return String que muestra los datos abreviados de un pasajero
     */
    // Texto que debe generar: Víctor Rodríguez Fernández, 00123456S, victor.rfernandez@upm.es
    public String toString() {
        return nombre + " " + apellidos + ", " + getDNI() + ", " + email;
    }

    /**
     * Función que comprueba la ocupación de un billete dentro del array listaBilletesPasajero
     *
     * @return Devuelve la ocupación del billete comprado por un pasajero dentro del array listaBilletesPasajero
     */
    public int numBilletesComprado() {
        return listaBilletesPasajeros.getOcupacion();
    }

    /**
     * Función que comprueba si se ha alcanzado el máximo de billetes comprados
     *
     * @return Devuelve true si se ha alcanzado el máximo de billetes que se puede comprar
     */
    public boolean maxBilletesAlcanzado() {
        return maxBilletes == numBilletesComprado();
    }

    /**
     * Getter para conseguir un billete de la lista
     *
     * @param i posición del billete en la lista
     * @return billete que se encuentra en la posición pasada por parámetro (i)
     */
    public Billete getBillete(int i) {
        return listaBilletesPasajeros.getBillete(i);
    }

    /**
     * Función que añade el billete pasado por parámetro a la listaBilletesPasajero
     *
     * @param billete que se añade a la lista
     * @return Devuelve true si se ha podido añadir el billete y false si no se ha podido
     */
    public boolean aniadirBillete(Billete billete) {
        boolean añadido = false;
        if (!maxBilletesAlcanzado()) {
            listaBilletesPasajeros.insertarBillete(billete);
            añadido = true;
        }
        return añadido;
    }

    /**
     * Función que busca un billete mediante el localizador pasado por parámetro
     *
     * @param localizador Localizador que es único de cada billete y que lo identifica
     * @return Devuelve el billete que se estaba buscando
     */
    public Billete buscarBillete(String localizador) {
        Billete localizado = null;
        localizado = listaBilletesPasajeros.buscarBillete(localizador);
        return localizado;
    }

    /**
     * Función que devuelve true si se ha podido cancelar el billete mediante el localizador pasado por parámetro que esta ligado al billete
     *
     * @param localizador Localizador único de cada billete y que lo identifica
     * @return Devuelve true si se ha cancelado el billete y false si no se ha podido
     */
    // Elimina el billete de la lista de billetes del pasajero
    public boolean cancelarBillete(String localizador) {
        return listaBilletesPasajeros.eliminarBillete(localizador);
    }

    /**
     * Función que encapsulado la función listarBilletes de la clase ListarBilletes
     */
    public void listarBilletes() {
        listaBilletesPasajeros.listarBilletes();
    }

    /**
     * FunciÓn que encapsula la funcionalidad seleccionarBillete de listaBillete
     *
     * @param teclado Son los datos que aporta el usuario
     * @param mensaje Mensajes que se le muestra al usuario
     * @return Devuelve el billete seleccionado
     */
    // Encapsula la funcionalidad seleccionarBillete de ListaBilletes
    public Billete seleccionarBillete(Scanner teclado, String mensaje) {
        return listaBilletesPasajeros.seleccionarBillete(teclado, mensaje);
    }

    /**
     * Función que crea y devuelve un objeto pasajero con los parámetros que selecciona el usuario como el nombre,
     * apellidos, numero del DNI, letra del DNI y el email
     *
     * @param teclado     información que afrece el usuario
     * @param pasajeros   pasajeros que realizan el vuelo
     * @param maxBilletes billetes máximos de un vuelo específico
     * @return Devuelve el pasajero que se ha creado con la información recibida
     */
    //Métodos estáticos
    // Crea un nuevo pasajero no repetido, pidiendo por teclado los datos necesarios al usuario en el orden 
    // y con los textos indicados en los ejemplos de ejecución del enunciado
    // La función solicita repetidamente los parametros hasta que sean correctos
    public static Pasajero altaPasajero(Scanner teclado, ListaPasajeros pasajeros, int maxBilletes) {
        Pasajero nuevoPasajero = null;
        System.out.print("Ingrese nombre:");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese apellidos:");
        String apellidos = teclado.nextLine();
        long numero;
        String numeroDNI;
        char letra;
        boolean existeDni, existeEmail;
        String email;

        do {
            existeDni = false;
            numero = Utilidades.leerNumero(teclado, "Ingrese número de DNI:", 00000000L, 99999999L);
            numeroDNI = String.valueOf(String.format("%08d",numero));
            letra = Utilidades.leerLetra(teclado, "Ingrese letra de DNI:", 'A','Z');
            if (pasajeros.buscarPasajeroDNI(numeroDNI + letra) != null){
                existeDni = true;
                System.out.println("DNI ya existe.");
            }
            if (!correctoDNI(numero, letra)){
                System.out.println("DNI incorrecto.");
            }
        } while ((!correctoDNI(numero, letra)) || (existeDni));

        do {
            existeEmail = false;
            System.out.print("Ingrese email:");
            email = teclado.nextLine();
            if (pasajeros.buscarPasajeroEmail(email) != null){
                existeEmail = true;
                System.out.println("Email ya existe.");
            }
        }while(!correctoEmail(email) || (existeEmail));
        nuevoPasajero = new Pasajero(nombre, apellidos, numero, letra, email, maxBilletes);
        pasajeros.insertarPasajero(nuevoPasajero);
        //
        //
        //
        //
        // return nuevoPasajero;

    }

    /**
     * Función que comprueba si el DNI es correcto ya que tiene que cumplir que el resto de la división del número entre 23 se igual a la letra según la tabla del enunciado
     *
     * @param numero Número de DNI
     * @param letra  Letra del DNI
     * @return Devuelve true si el DNI es correcto y false si no es correcto ya que no cumple los requisitos
     */
    // Correcto: 00123456 S, incorrectos: 123456789 A, 12345678 0, 12345678 A
    public static boolean correctoDNI(long numero, char letra) {
        boolean correcto = true;

        if (numero < 0 || numero > 99999999){
            correcto = false;
        }

        char[] letraDNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int numLetra = (int) numero % 23;
        if (letra != letraDNI[numLetra]) {
            correcto = false;
        }
        return correcto;
    }

    /**
     * Función que comprueba si el email pasado por parámetro es correcto, ya que tiene que cumplir una serie de requisitos (Como por ejemplo que no empiece por punto)
     *
     * @param email email del usuario
     * @return Devuelve true si el email cumple todos los requisitos y si no los cumple devuelve false
     */
    // Correcto: cristian.ramirez@upm.es, incorrecto: cristian.ramirez@gmail.com, cristian-23@upm.es, cristian.@upm.es
    public static boolean correctoEmail(String email) {
        String[] emailCompleto = email.split("@");
        String primeraParte = emailCompleto[0];
        String segundaParte = emailCompleto[1];
        int longitud = primeraParte.length();
        boolean correcto = true;
        if (segundaParte.equals("alumnos.upm.es") || segundaParte.equals("upm.es")) {
            if (primeraParte.charAt(0) == '.' || primeraParte.charAt(longitud - 1) == '.') {
                System.out.println("Email incorrecto.");
                correcto = false;
            } else {
                for (int i = 0; i < longitud; i++) {
                    if ((primeraParte.charAt(i) < 65 || primeraParte.charAt(i) > 90) && (primeraParte.charAt(i) < 97 || primeraParte.charAt(i) > 122) && primeraParte.charAt(i) != '.') {
                        System.out.println("Email incorrecto");
                        correcto = false;
                    }
                }
            }
        } else {
            System.out.println("Email incorrecto.");
            correcto = false;
        }
        return correcto;
    }
}