package Capa_Dominio.Producto;


/**
 * Representa un producto de la tienda.
 * <p>
 * Contiene la información y controla la gestión del stock. Asegurando
 * que este nunca sea negativo y mantiene su identificacion unica mediante el codigo.
 * </p>
 */
public class Producto {

    private String codigoProducto;
    private String nombre;
    private String categoria;
    private float precioUnitario;
    private int stock;

    /**
     * Crea un nuevo producto con validaciónes.
     *
     * @param codigoProducto Identificador único del producto.
     * @param nombre         Nombre descriptivo.
     * @param categoria      Categoría a la que pertenece.
     * @param precioUnitario Precio por unidad (debe ser > 0).
     * @param stock          Cantidad inicial en inventario (debe ser >= 0).
     * @throws IllegalArgumentException Si el stock es negativo o el precio es <= 0.
     */
    public Producto(String codigoProducto,String nombre, String categoria, float precioUnitario, int stock) {
        if (stock < 0) {throw new IllegalArgumentException("El stock no puede ser negativo");}
        if (precioUnitario <= 0) {throw new IllegalArgumentException("El precio unitario no puede ser 0 o inferior");}
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    /**
     * Obtiene el código único del producto.
     * @return Código identificador.
     */
    public String getCodigoProducto() {
        return codigoProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la categoría del producto.
     * @return Nombre de la categoría.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el precio actual por unidad.
     * @return Precio unitario.
     */
    public float getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Obtiene la cantidad de unidades disponibles en inventario.
     * @return Stock actual.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Actualiza el precio unitario del producto.
     *
     * @param precioUnitario Nuevo precio (debe ser > 0).
     * @throws IllegalArgumentException Si el precio es negativo o cero.
     */
    public void setPrecioUnitario(float precioUnitario) throws IllegalArgumentException {
        if (precioUnitario <= 0){
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        this.precioUnitario = precioUnitario;
    }

    /**
     * Incrementa el stock disponible.
     *
     * @param unidades Cantidad de unidades a añadir.
     */
    public void incrementarStock(int unidades){
        this.stock += unidades;
    }

    /**
     * Decrementa el stock disponible, validando que no quede en negativo.
     *
     * @param unidades Cantidad de unidades a restar.
     * @throws IllegalArgumentException Si el stock resultante fuera negativo.
     */
    public void decrementarStock(int unidades) throws IllegalArgumentException{
        if(stock - unidades < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock -= unidades;
    }
}
