package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Pedido.Pedido;
import Capa_Repositorio.Interfaces.ServicioPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación en memoria del repositorio de pedidos.
 * Almacena los pedidos en una lista interna.
 */
public class RepositorioPedido implements ServicioPedido {

    private final ArrayList<Pedido> almacenPedidos;

    /**
     * Inicializa el almacén de pedidos.
     */
    public RepositorioPedido(){
        this.almacenPedidos = new ArrayList<Pedido>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void alta(Pedido pedido) {
        almacenPedidos.add(pedido);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pedido> listar() {
        return Collections.unmodifiableList(almacenPedidos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pedido> consultar(String codigo) {
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

        for (int i = 0; i < almacenPedidos.size(); i++) {
            if (almacenPedidos.get(i).getCliente().getCodcliente().equals(codigo)) {
                pedidos.add(almacenPedidos.get(i));
            }
        }

        return Collections.unmodifiableList(pedidos);
    }
}
