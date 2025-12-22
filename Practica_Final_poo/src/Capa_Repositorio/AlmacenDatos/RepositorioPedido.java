package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Pedido.Pedido;
import Capa_Repositorio.Interfaces.ServicioPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioPedido implements ServicioPedido {

    private ArrayList<Pedido> almacenPedidos = new ArrayList<Pedido>();


    @Override
    public void alta(Pedido pedido) {
        almacenPedidos.add(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return Collections.unmodifiableList(almacenPedidos);
    }
}
