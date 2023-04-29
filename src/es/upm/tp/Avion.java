package es.upm.tp;

/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class Avion {

    /**
     * Constructor of the class
     *
     * @param marca
     * @param modelo
     * @param matricula
     * @param columnas
     * @param filas
     * @param alcance
     */
    public Avion(String marca, String modelo, String matricula, int columnas, int filas, double alcance);
    public String getMarca();
    public String getModelo();
    public String getMatricula();
    public int getColumnas();
    public int getFilas();
    public double getAlcance();
    // Crea un String con los datos de un avión con el siguiente formato:
    // Boeing 737(EC-LKE): 180 asientos, hasta 5700.0 km
    public String toString();
    // Crea un String con los datos de un avión con el siguiente formato:
    // Boeing 737(EC-LKE)
    public String toStringSimple();
}
