package Capa_Repositorio.Interfaces;

import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Producto.Producto;

import java.util.ArrayList;
import java.util.List;

public interface ServicioProducto {

    void alta(Producto producto);
    void baja(String codigo, ArrayList<LineaPedido> LineaPedido);
    List<Producto> consultar(String codigo);
    List<Producto> listar();
}
