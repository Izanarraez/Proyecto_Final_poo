package Capa_Dominio.Cliente;

public class Cliente {
    private String codcliente;
    private String nombre;
    private int telefono;

    public void  Clientes (String codcliente,String nombre, int telefono ) throws IllegalArgumentException {
        if (telefono < 0) {throw new IllegalArgumentException("El teléfono no puede ser negativo");}
        this.codcliente = codcliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    public String getCodcliente() {
        return this.codcliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getTelefono() {
        return this.telefono;
    }
}
