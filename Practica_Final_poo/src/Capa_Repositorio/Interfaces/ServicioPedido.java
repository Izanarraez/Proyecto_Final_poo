package Capa_Repositorio.Interfaces;

import Capa_Dominio.Pedido.Pedido;

import java.util.List;

/**
 * Contrato para el almacenamiento y recuperación de pedidos.
 * Define las operaciones que debe cumplir cualquier repositorio de pedidos.
 */
public interface ServicioPedido {

    /**
     * Guarda un nuevo pedido.
     * @param pedido Pedido a guardar.
     */
    void alta(Pedido pedido);
    /**
     * Obtiene todos los pedidos.
     * @return Lista completa de pedidos.
     */
    List<Pedido> listar();
    /**
     * Busca pedidos por el codigo del cliente.
     * @param codigo Codigo a buscar.
     * @return Lista de pedidos que coinciden.
     */
    List<Pedido> consultar(String codigo);
}
