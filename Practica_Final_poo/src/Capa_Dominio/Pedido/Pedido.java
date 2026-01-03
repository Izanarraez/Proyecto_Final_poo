package Capa_Dominio.Pedido;

import Capa_Dominio.Cliente.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private ArrayList<LineaPedido> lineaPedido;
    private float iva;
    private boolean confirmado;

    public Pedido(Cliente cliente , float iva) throws IllegalArgumentException {
        if (iva < 0 || iva > 1) {throw new IllegalArgumentException("El iva no puede ser negativo o mayor que 1");}
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<LineaPedido>();
        this.iva = iva;
        this.confirmado = false;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    public List<LineaPedido> getLineaPedido() {
        return Collections.unmodifiableList(this.lineaPedido);
    }
    public float getIva() {
        return this.iva;
    }

    public void añadirLineaPedido(LineaPedido lineaPedido) {
        if (this.confirmado) {
            throw new IllegalStateException("No se pueden añadir líneas a un pedido confirmado.");
        }
        this.lineaPedido.add(lineaPedido);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public float calcularTotalConIva() {
        float total = 0;
        for (LineaPedido linea : lineaPedido) {
            total += linea.getUnidades() * linea.getPrecioAplicado();
        }
        return total * (1 + iva);
    }
}
