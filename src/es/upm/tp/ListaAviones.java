package es.upm.tp;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * ListaAviones es una clase que encapsula las variables enteras usadas para definir los aviones,
 * así como también contiene funciones para buscar, seleccionar e insertar aviones en el array de nombre ListaAviones
 * También escribe un fichero.csv con los datos de cada avión
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */

public class ListaAviones {

    /**
     * Atributo que devuelve la capacidad de la ListaAvion
     */
    private int capacidad;

    /**
     * Atributo que devuelve la ocupacion del avión dentro de la lista
     */
    private int ocupacion;

    /**
     * Atributo que contiene el array donde están los aviones
     */
    private Avion[] ListaAviones;

    /**
     * Constructor que crea un array con la cantidad de aviones recibidos
     *
     * @param capacidad especifica la capacidad de la lista que contiene los aviones
     */
    public ListaAviones(int capacidad) {
        this.ocupacion = 0;
        this.capacidad = capacidad;
        ListaAviones = new Avion[capacidad];
    }

    /**
     * Getter del atributo ocupación
     *
     * @return devuelve la cantidad de aviones que hay en el array ListaAviones con la variable ocupacion
     */
    public int getOcupacion() {
        return ocupacion;
    }

    /**
     * Devuelve verdadero si la lista aviones está llena, si no, devuelve falso
     *
     * @return estaLlena como true si la lista tiene el máximo de aviones
     */
    public boolean estaLlena() {
        boolean estaLlena = false;
        if (ocupacion == capacidad) {
            estaLlena = true;
        }
        return estaLlena;
    }

    /**
     * @param posicion es la posición que se pasa por parámetro
     * @return devuelve el avión que se encuentra en esa posición
     */
    public Avion getAvion(int posicion) {
        return ListaAviones[posicion - 1];
    }

    /**
     * Inserta un avión en el array ListaAviones
     *
     * @param avion avión que se quiere insertar en la lista
     * @return devuelve true si se ha insertado el avión o false si no se ha añadido
     */
    public boolean insertarAvion(Avion avion) {
        boolean insertar = false;
        if (!estaLlena()) {
            ListaAviones[ocupacion] = avion;
            ocupacion++;
            insertar = true;
        }
        return insertar;
    }

    /**
     * Busca un avión por medio de su matrícula
     *
     * @param matricula código que identifica a un avión
     * @return devuelve el avión correspondiente a la matrícula introducida por parámetro
     */
    public Avion buscarAvion(String matricula) {
        Avion resultado = null;
        for (int i = 0; i < ocupacion; i++) {
            if (Objects.equals(ListaAviones[i].getMatricula(), matricula)) ;
            resultado = ListaAviones[i];
        }
        return resultado;
    }

    /**
     * Selecciona el avión, si existe, de la matrícula que se pasa por parámetro. Además, comprueba si tiene alcance suficiente para el vuelo requerido
     *
     * @param teclado el usuario introduce la matrícula del avión que desea
     * @param mensaje mensaje que se muestra por pantalla
     * @param alcance alcance máximo que tiene el avión seleccionado
     * @return devuelve el avión seleccionado si cumple los requisitos (que exista y tenga alcance suficiente)
     */
    // Permite seleccionar un avión existente a partir de su matrícula, y comprueba si dispone de un alcance mayor o igual que el pasado como argumento,
    // usando el mensaje pasado como argumento para la solicitud y siguiendo el orden y los textos mostrados en el enunciado
    // La función solicita repetidamente la matrícula del avión hasta que se introduzca uno con alcance suficiente
    public Avion seleccionarAvion(Scanner teclado, String mensaje, double alcance) {
        String matricula = "";
        boolean alcanceSuficiente = false;
        Avion avion = null;
        do {
            System.out.print(mensaje);
            matricula = teclado.nextLine();
            avion = buscarAvion(matricula);

            if (avion == null) {
                System.out.println("Matrícula de avión no encontrada.");
            } else if (avion.getAlcance() < alcance) {
                System.out.printf(String.format("Avión seleccionado con alcance insuficiente (menor que %.3f km).\n", alcance).replace(',', '.'));
            } else {
                alcanceSuficiente = true;
            }
        } while (!alcanceSuficiente);
        //} while (avion == null || !alcanceSuficiente);
        return avion;
    }

    /**
     * Escribe en un fichero los aviones de una lista con sus caracteristicas
     *
     * @param nombre es el nombre del fichero donde se van a guardar los datos
     * @return devuelve true si se ha copiado en el fichero y false si no se ha podido
     */
    // Genera un fichero CSV con la lista de aviones, sobreescribiendolo
    public boolean escribirAvionesCsv(String nombre) {
        PrintWriter printWriterF = null;
        Avion avion;
        boolean ficheroEscrito = true;

        try {
            printWriterF = new PrintWriter(nombre);
            for (int i = 0; i < ocupacion; i++){
                avion = ListaAviones[i];
                printWriterF.println(avion.getMarca() + ";" + avion.getModelo() + ";" + avion.getMatricula() + ";" + avion.getFilas() + ";" + avion.getColumnas() + ";" + avion.getAlcance());
                if (i != ocupacion - 1) printWriterF.println();
            }
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("Fichero " + nombre + " no encontrado.");
            ficheroEscrito = false;
        } catch (IOException ioException) {
            System.out.println("Error de escritura en fichero " + nombre + ".");
            ficheroEscrito = false;
        } finally {
            if (printWriterF != null) {
                printWriterF.close();
            }
        }
        return ficheroEscrito;
    }

    /**
     * Crea una lista con la funcion escribirAvionesCsv, tomando los datos del fichero CSV
     *
     * @param fichero   fichero donde se lee los aviones con sus caracteristicas
     * @param capacidad es la capacidad de la lista de aviones a la que se hace referencia
     * @return devuelve la listaImportada
     */
    //Métodos estáticos
    // Genera una lista de aviones a partir del fichero CSV, usando el argumento como   
    // capacidad máxima de la lista
    public static ListaAviones leerAvionesCsv(String fichero, int capacidad) {
        ListaAviones listaAvionesCSV = new ListaAviones(capacidad);
        Scanner scanner = null;
        String texto;

        try {
            scanner = new Scanner(new FileReader(fichero));
            String marca, modelo, matricula;
            int columnas, filas;
            double alcance;

            do {
                texto = scanner.nextLine();
                String [] matrizAvion = texto.split(";");
                marca = matrizAvion[0];
                modelo = matrizAvion[1];
                matricula = matrizAvion[2];
                filas = Integer.parseInt(matrizAvion[3]);
                columnas = Integer.parseInt(matrizAvion[4]);
                alcance = Double.parseDouble(matrizAvion[5]);
                listaAvionesCSV.insertarAvion(new Avion(marca, modelo, matricula, columnas, filas, alcance));
            } while (scanner.hasNext());
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Fichero " + fichero + " no encontrado.");
        }
        finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return listaAvionesCSV;
    }
}