package Capa_Dominio.Pedido;

import Capa_Dominio.Producto.Producto;

/**
 * Representa una línea de pedidos.
 * <p>
 * Es inmutable una vez creada. Captura el precio del producto en el momento
 * de la creación para garantizar la integridad del pedido.
 * </p>
 */
public class LineaPedido {

    private Producto producto;
    private int unidades;
    private float precioAplicado;

    /**
     * Crea una línea de pedido.
     *
     * @param producto       Producto asociado.
     * @param unidades       Cantidad solicitada (debe ser > 0).
     * @param precioAplicado Precio unitario en el momento de la compra.
     * @throws IllegalArgumentException Si las unidades son menores o iguales a 0.
     */
    public LineaPedido(Producto producto, int unidades, float precioAplicado) throws IllegalArgumentException {
        if (unidades <= 0){
            throw new IllegalArgumentException("Las unidades tienen que ser mayores a 0");
        }
        this.producto = producto;
        this.unidades = unidades;
        this.precioAplicado = precioAplicado;
    }

    /**
     * Obtiene el producto asociado a la línea.
     * @return Objeto Producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Obtiene la cantidad de unidades solicitadas.
     * @return Número de unidades.
     */
    public int getUnidades() {
        return unidades;
    }

    /**
     * Obtiene el precio que se aplicó al crear la línea.
     * @return Precio unitario histórico.
     */
    public float getPrecioAplicado() {
        return precioAplicado;
    }
}
