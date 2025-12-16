package Clientes;

public class Clientes {
    private int codcliente;
    private String nombre;
    private int telefono;

    public void  Clientes (int codcliente,String nombre, int telefono ) {
        this.codcliente = codcliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public int getCodcliente() {
        return this.codcliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getTelefono() {
        return this.telefono;
    }
}
