package Capa_Servicio;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.AlmacenDatos.RepositorioCliente;
import Capa_Repositorio.AlmacenDatos.RepositorioPedido;
import Capa_Repositorio.AlmacenDatos.RepositorioProducto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TiendaServicio {

    RepositorioCliente repositorioCliente;
    RepositorioProducto repositorioProducto;
    RepositorioPedido repositorioPedido;

    public TiendaServicio() {
        this.repositorioCliente = new RepositorioCliente();
        this.repositorioProducto = new RepositorioProducto();
        this.repositorioPedido = new RepositorioPedido();
    }

    /**Funcionalidades Producto*/
    public void altaProducto(Producto producto) {}

    public void ajustarStock(String codigo, int cantidad, boolean incremento) {}

    public List<Producto> consultarProductos(String nombre) {}

    public List<Producto> listadoProductos() {}

    public void borrarProducto(String codigo, ArrayList<LineaPedido> LineaPedidos) {}


    /**Funcionalidades Cliente*/

    public void altaCliente(Cliente cliente) {}

    public List<Producto> consultarClientes(String nombre) {}

    public List<Producto> listadoClientes() {}


    /**Funcionalidades Pedidos*/

    public void crearPedido(Pedido pedido) {}

    public void añadirLineaPedido(Producto producto, int unidades) {}

    public boolean confirmarPedido() {} //Falta buscar parametros

    public List<Producto> consultarPedido() {} //Falta buscar parametros

}
