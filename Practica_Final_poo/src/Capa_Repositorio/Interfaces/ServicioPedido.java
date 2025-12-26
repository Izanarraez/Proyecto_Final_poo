package Capa_Repositorio.Interfaces;

import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;

import java.util.List;

public interface ServicioPedido {

    void alta(Pedido pedido);
    List<Pedido> listar();
    List<Pedido> consultar(String codigo);
}
