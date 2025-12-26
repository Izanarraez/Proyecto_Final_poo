package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.Interfaces.ServicioPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioPedido implements ServicioPedido {

    private final ArrayList<Pedido> almacenPedidos;

    public RepositorioPedido(){
        this.almacenPedidos = new ArrayList<Pedido>();
    }

    @Override
    public void alta(Pedido pedido) {
        almacenPedidos.add(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return Collections.unmodifiableList(almacenPedidos);
    }

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
