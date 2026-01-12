package Capa_Dominio.Pedido;

import Capa_Dominio.Cliente.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa un pedido realizado por un cliente.
 * <p>
 * Gestiona el ciclo de vida del pedido (creación, adición de líneas, confirmación)
 * y garantiza que no se modifique una vez confirmado.
 * </p>
 */
public class Pedido {
    private Cliente cliente;
    private ArrayList<LineaPedido> lineaPedido;
    private float iva;
    private boolean confirmado;

    /**
     * Inicia un nuevo pedido para un cliente.
     *
     * @param cliente Cliente que realiza el pedido.
     * @param iva     Porcentaje de IVA a aplicar (entre 0.0 y 1.0).
     * @throws IllegalArgumentException Si el IVA está fuera del rango válido.
     */
    public Pedido(Cliente cliente , float iva) throws IllegalArgumentException {
        if (iva < 0 || iva > 1) {throw new IllegalArgumentException("El iva no puede ser negativo o mayor que 1");}
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<LineaPedido>();
        this.iva = iva;
        this.confirmado = false;
    }

    /**
     * Obtiene el cliente asociado al pedido.
     * @return Objeto Cliente.
     */
    public Cliente getCliente() {
        return this.cliente;
    }

    /**
     * Devuelve una vista inmutable de las líneas del pedido.
     *
     * @return Lista no modificable de líneas de pedido.
     */
    public List<LineaPedido> getLineaPedido() {
        return Collections.unmodifiableList(this.lineaPedido);
    }

    /**
     * Obtiene el IVA aplicado al pedido.
     * @return Valor decimal del IVA.
     */
    public float getIva() {
        return this.iva;
    }

    /**
     * Añade una línea al pedido si este no ha sido confirmado aún.
     *
     * @param lineaPedido Línea a añadir.
     * @throws IllegalStateException Si el pedido ya está confirmado.
     */
    public void añadirLineaPedido(LineaPedido lineaPedido) {
        if (this.confirmado) {
            throw new IllegalStateException("No se pueden añadir líneas a un pedido confirmado.");
        }
        this.lineaPedido.add(lineaPedido);
    }

    /**
     * Indica si el pedido ha sido confirmado.
     *
     * @return true si está confirmado, false en caso contrario.
     */
    public boolean isConfirmado() {
        return confirmado;
    }

    /**
     * Cambia el estado de confirmación del pedido.
     *
     * @param confirmado Nuevo estado.
     */
    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    /**
     * Calcula el importe total del pedido aplicando el IVA.
     *
     * @return Importe total con IVA incluido.
     */
    public float calcularTotalConIva() {
        float total = 0;
        for (LineaPedido linea : lineaPedido) {
            total += linea.getUnidades() * linea.getPrecioAplicado();
        }
        return total * (1 + iva);
    }

    /**
     * Calcula el importe total del pedido sin el IVA.
     *
     * @return Importe total sin iva.
     */
    public float calcularTotalSinIva() {
        float total = 0;
        for (LineaPedido linea : lineaPedido) {
            total += linea.getUnidades() * linea.getPrecioAplicado();
        }
        return total;
    }
}
