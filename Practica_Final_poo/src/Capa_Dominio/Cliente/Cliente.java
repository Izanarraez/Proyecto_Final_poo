package Capa_Dominio.Cliente;

public class Cliente {
    private String codcliente;
    private String nombre;
    private int telefono;

    public void  Clientes (String codcliente,String nombre, int telefono ) {
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
