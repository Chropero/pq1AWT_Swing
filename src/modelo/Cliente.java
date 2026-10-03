package modelo;
/**
 *
 * @author CHRISTIAN
 */
public class Cliente {
    private final int numero;
    private final String nombre;
    private final int edad;
    private final double puntos;

    public Cliente(int numero, String nombre, int edad, double puntos) {
        this.numero = numero;
        this.nombre = nombre;
        this.edad = edad;
        this.puntos = puntos;
    }

    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public double getPuntos() { return puntos; }
    
}
