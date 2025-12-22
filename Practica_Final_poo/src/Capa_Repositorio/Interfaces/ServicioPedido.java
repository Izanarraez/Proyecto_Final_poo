package Capa_Repositorio.Interfaces;

import Capa_Dominio.Pedido.Pedido;

import java.util.List;

public interface ServicioPedido {

    void alta(Pedido pedido);
    List<Pedido> listar();

}
