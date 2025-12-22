package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.Interfaces.ServicioProducto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioProducto implements ServicioProducto {

    private ArrayList<Producto> almacenProductos = new ArrayList<Producto>();


    @Override
    public void alta(Producto producto) {
        almacenProductos.add(producto);
    }

    @Override
    public void baja(String codigo, ArrayList<LineaPedido> LineaPedido) {

        for(int i = 0; i < almacenProductos.size(); i++){
            if(LineaPedido.get(i).getProducto().getCodigoProducto().equals(codigo)){
                almacenProductos.remove(i);
            }
        }
    }

    @Override
    public List<Producto> consultar(String nombre) {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        for(Producto producto: almacenProductos){
            if (producto.getNombre().equals(nombre)){
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
