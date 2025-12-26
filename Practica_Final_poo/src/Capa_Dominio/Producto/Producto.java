package Capa_Dominio.Producto;

public class Producto {

    private String codigoProducto;
    private String nombre;
    private String categoria;
    private float precioUnitario;
    private int stock;

    public Producto(String codigoProducto,String nombre, String categoria, float precioUnitario, int stock) {
        if (stock < 0) {throw new IllegalArgumentException("El stock no puede ser negativo");}
        if (precioUnitario <= 0) {throw new IllegalArgumentException("El precio unitario no puede ser 0 o inferior");}
        this.codigoProducto = codigoProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setPrecioUnitario(float precioUnitario) {
        if (precioUnitario <= 0){
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
        this.precioUnitario = precioUnitario;
    }

    public void incrementarStock(int unidades){
        this.stock += unidades;
    }

    public void decrementarStock(int unidades){
        if(stock - unidades < 0){
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stock -= unidades;
    }

    public String toString(){
        return "Codigo: "+this.codigoProducto + "Nombre: "+ this.nombre + "Categoria: "+ this.categoria + "PrecioUnitario: "+ this.precioUnitario + "Stock: "+this.stock + "\n";
    }
}
