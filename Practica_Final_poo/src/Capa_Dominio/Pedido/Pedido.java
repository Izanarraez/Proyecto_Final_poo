package Capa_Dominio.Pedido;

import Capa_Dominio.Cliente.Cliente;

import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<LineaPedido> lineaPedido;
    private float iva;

    public Pedido(Cliente cliente, float iva) {
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<LineaPedido>();
        this.iva = iva;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    public ArrayList<LineaPedido> getLineaPedido() {
        return this.lineaPedido;
    }
    public float getIva() {
        return this.iva;
    }
}
