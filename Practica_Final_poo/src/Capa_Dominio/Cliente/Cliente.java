package Capa_Dominio.Cliente;

public class Cliente {
    private String codcliente;
    private String nombre;
    private String telefono;

    public void  Clientes (String codcliente,String nombre, String telefono ) throws IllegalArgumentException {
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

    public String getTelefono() {
        return this.telefono;
    }

    public String toString() {
        return  "Codigo: "+ this.codcliente + "Nombre: "+ this.nombre + "Telefono: "+ this.telefono + "\n";
    }
}
