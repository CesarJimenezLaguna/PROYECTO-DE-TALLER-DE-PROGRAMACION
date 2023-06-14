package es.upm.tp;

/**
 * Avión es una clase que encapsula las variables enteras usadas para definir un avion concreto.
 *
 * @author César Jiménez Laguna
 * @author Iñaki Ramos Iturria
 * @version 1.0
 */

public class Avion {

    /**
     * Atributo que contiene la marca del avión
     */
    private String marca;

    /**
     * Atributo que contiene el modelo de avión
     */
    private String modelo;

    /**
     * Atributo que contiene la matrícula del avión
     */
    private String matricula;

    /**
     * Atributo que contiene las columnas de asientos del avión
     */
    private int columnas;

    /**
     * Atributo que contiene las filas de asientos del avión
     */
    private int filas;

    /**
     * Atributo que contiene el alcance del avión
     */
    private double alcance;

    /**
     * Constructor que crea un avión con los parámetros recibidos (marca, modelo, matrícula, columnas, filas y alcance)
     *
     * @param marca     especifica la marca del avión
     * @param modelo    especifica el modelo del avión
     * @param matricula especifica la matrícula del avión
     * @param columnas  especifica columnas que tiene el avión
     * @param filas     especifica las filas que tiene el avión
     * @param alcance   especifica el alcance máximo del avión
     */
    public Avion(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     * Getter del atributo marca
     *
     * @return Devuelve la marca del avión
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Getter del atributo modelo
     *
     * @return Devuelve el modelo del avión
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Getter del atributo matrícula
     *
     * @return Devuelve la matricula del avión
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Getter del atributo columnas
     *
     * @return Devuelve las columnas de los asientos del avión
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     * Getter del atributo filas
     *
     * @return Devuelve las filas de los asientos del avión
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Getter del atributo alcance
     *
     * @return Devuelve el alcance máximo del avión
     */
    public double getAlcance() {
        return alcance;
    }

    /**
     * Devuelve los datos del un avión en un formato específico, ("(Boeing 737(EC-LKE): 180 asientos, hasta 5700.0 km)")
     *
     * @return marca, modelo, matrícula, asientos (columnas * filas) y alcance máximo
     */
    // Crea un String con los datos de un avión con el siguiente formato:
    // Boeing 737(EC-LKE): 180 asientos, hasta 5700.0 km
    public String toString() {
        return marca + " " + modelo + "(" + matricula + "): " + columnas * filas + " asientos, hasta " + alcance + " km";
    }

    /**
     * Devuelve los datos de un avión en un formato específico, ("(Boeing 737(EC-LKE))")
     *
     * @return marca, modelo y matrícula
     */
    // Crea un String con los datos de un avión con el siguiente formato:
    // Boeing 737(EC-LKE)
    public String toStringSimple() {
        return marca + " " + modelo + "(" + matricula + ")";
    }
}