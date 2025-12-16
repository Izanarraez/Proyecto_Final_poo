package Clientes;

import java.util.ArrayList;

public class Pedido {
    private Clientes cliente;
    private ArrayList<LineasPedido> lineasPedido;
    private float iva;
    public Pedido(Clientes cliente, ArrayList<LineasPedido> lineasPedido, float iva) {
        this.cliente = cliente;
        this.lineasPedido = lineasPedido;
        this.iva = iva;
    }
    public Clientes getCliente() {
        return this.cliente;
    }
    public ArrayList getLineasPedido() {
        return this.lineasPedido;
    }
    public float getIva() {
        return this.iva;
    }
}
