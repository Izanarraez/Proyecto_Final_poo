package Capa_Dominio.Pedido;

import Capa_Dominio.Cliente.Cliente;

import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<LineaPedido> lineaPedido;
    private float iva;

    public Pedido(Cliente cliente, float iva) throws IllegalArgumentException {
        if (iva < 0 || iva > 1) {throw new IllegalArgumentException("El iva no puede ser negativo");}
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

    public String toString(){
        return this.cliente.toString() + "Linea: " + this.lineaPedido.toString() + "IVA: " + this.iva + "\n";
    }
}
