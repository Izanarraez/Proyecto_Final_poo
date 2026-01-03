package Capa_Repositorio.Interfaces;


import Capa_Dominio.Producto.Producto;
import java.util.List;

/**
 * Contrato para el almacenamiento y recuperación de productos.
 * Define las operaciones que debe cumplir cualquier repositorio de productos.
 */
public interface ServicioProducto {

    /**
     * Guarda un nuevo producto.
     * @param producto Producto a guardar.
     */
    void alta(Producto producto);
    /**
     * Elimina un producto por su código.
     * @param codigo Código del producto.
     */
    void baja(String codigo);
    /**
     * Busca productos por su nombre.
     * @param nombre Nombre a buscar.
     * @return Lista de productos que coinciden.
     */
    List<Producto> consultar(String nombre);
    /**
     * Obtiene todos los productos.
     * @return Lista completa de productos.
     */
    List<Producto> listar();
}
