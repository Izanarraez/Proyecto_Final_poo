package Capa_Dominio.Pedido;

import Capa_Dominio.Producto.Producto;

public class LineaPedido {

    private Producto producto;
    private int unidades;
    private float precioAplicado;

    public LineaPedido(Producto producto, int unidades, float precioAplicado) throws IllegalArgumentException {
        if (unidades <= 0){
            throw new IllegalArgumentException("Las unidades tienen que ser mayores a 0");
        }
        this.producto = producto;
        this.unidades = unidades;
        this.precioAplicado = precioAplicado;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getUnidades() {
        return unidades;
    }

    public float getPrecioAplicado() {
        return precioAplicado;
    }

    public String toString() {
        return this.producto.toString() + ", Unidades: "+ this.unidades + ", Precio Aplicado: "+ this.precioAplicado + "\n";
    }
}
