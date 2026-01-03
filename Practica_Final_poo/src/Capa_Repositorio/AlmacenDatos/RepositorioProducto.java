package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.Interfaces.ServicioProducto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioProducto implements ServicioProducto {

    private final ArrayList<Producto> almacenProductos;

    public RepositorioProducto(){
        this.almacenProductos = new ArrayList<Producto>();
    }

    @Override
    public void alta(Producto producto) {

        for (Producto productoAlmacenado : this.almacenProductos) {
            if (productoAlmacenado.getCodigoProducto().equals(producto.getCodigoProducto())) {
                throw  new IllegalArgumentException("Id de producto ya existente");
            }
        }
        almacenProductos.add(producto);
    }

    @Override
    public void baja(String codigo) {

        for(int i = 0; i < almacenProductos.size(); i++){
            if(almacenProductos.get(i).getCodigoProducto().equals(codigo)){
                almacenProductos.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Producto> consultar(String nombre) {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        for(Producto producto: almacenProductos){
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                productos.add(producto);
            }
        }
        return Collections.unmodifiableList(productos);
    }

    @Override
    public List<Producto> listar() {
        return Collections.unmodifiableList(almacenProductos);
    }
}
