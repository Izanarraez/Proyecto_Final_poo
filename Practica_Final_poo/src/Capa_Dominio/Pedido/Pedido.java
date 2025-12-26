package Capa_Dominio.Pedido;

import Capa_Dominio.Cliente.Cliente;

import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private ArrayList<LineaPedido> lineaPedido;
    private float iva;
    private boolean confirmado;

    public Pedido(Cliente cliente, float iva) throws IllegalArgumentException {
        if (iva < 0 || iva > 1) {throw new IllegalArgumentException("El iva no puede ser negativo");}
        this.cliente = cliente;
        this.lineaPedido = new ArrayList<LineaPedido>();
        this.iva = iva;
        this.confirmado = false;
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

    public String toString(){
        return "Cliente:" + this.cliente.toString() + "Confirmado:"+ this.isConfirmado() +"Linea: " + this.lineaPedido.toString() + "IVA: " + this.iva + "\n";
    }
}
