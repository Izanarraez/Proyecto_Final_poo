package Capa_Dominio.Cliente;

/**
 * Representa a un cliente de la tienda.
 * <p>
 * Se usa para asociar pedidos a una persona física.
 * Identificado única mediante su código.
 * </p>
 */
public class Cliente {
    private String codcliente;
    private String nombre;
    private String telefono;

    /**
     * Crea un nuevo cliente.
     *
     * @param codcliente Identificador único del cliente.
     * @param nombre     Nombre completo.
     * @param telefono   Número de contacto.
     */
    public Cliente (String codcliente, String nombre, String telefono ) {
        this.codcliente = codcliente;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    /**
     * Obtiene el código del cliente.
     * @return Código único.
     */
    public String getCodcliente() {
        return this.codcliente;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return Nombre completo.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return Número de teléfono.
     */
    public String getTelefono() {
        return this.telefono;
    }
}
