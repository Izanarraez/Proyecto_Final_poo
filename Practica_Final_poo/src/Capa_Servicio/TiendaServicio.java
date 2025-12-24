package Capa_Servicio;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.AlmacenDatos.RepositorioCliente;
import Capa_Repositorio.AlmacenDatos.RepositorioPedido;
import Capa_Repositorio.AlmacenDatos.RepositorioProducto;
import Capa_Repositorio.Interfaces.ServicioCliente;
import Capa_Repositorio.Interfaces.ServicioPedido;
import Capa_Repositorio.Interfaces.ServicioProducto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TiendaServicio implements ServicioProducto, ServicioCliente, ServicioPedido {

    RepositorioCliente repositorioCliente;
    RepositorioProducto repositorioProducto;
    RepositorioPedido repositorioPedido;

    public TiendaServicio() {
        this.repositorioCliente = new RepositorioCliente();
        this.repositorioProducto = new RepositorioProducto();
        this.repositorioPedido = new RepositorioPedido();
    }

    /**Funcionalidades Producto*/
    public void altaProducto(Producto producto) {
        this.repositorioProducto.alta(producto);
    }

    public void ajustarStock(String codigo, int cantidad, boolean incremento) {}

    public List<Producto> consultarProductos(String nombre) {
        return(this.repositorioProducto.consultar(nombre));
    }

    public List<Producto> listadoProductos() {
        return(this.repositorioProducto.listar());
    }

    public void borrarProducto(String codigo, ArrayList<LineaPedido> LineaPedidos) {
    }


    /**Funcionalidades Cliente*/

    public void altaCliente(Cliente cliente) {
        this.repositorioCliente.alta(cliente);
    }

    public List<Cliente> consultarClientes(String nombre) {
        return(this.repositorioCliente.consultar(nombre));
    }

    public List<Cliente> listadoClientes() {
        return (this.repositorioCliente.listar());
    }


    /**Funcionalidades Pedidos*/

    public void crearPedido(Pedido pedido) {
        this.repositorioPedido.alta(pedido);
    }

    public void añadirLineaPedido(Pedido pedido, Producto producto, int unidades) {
        LineaPedido lineaPedido;
        try {
            lineaPedido = new LineaPedido(producto, unidades, producto.getPrecioUnitario());
        } catch (Exception e) {
            e.getMessage();
            return;
        }
        pedido.getLineaPedido().add(lineaPedido);
    }

    public boolean confirmarPedido(Pedido pedido) {
        LineaPedido lineaPedido;
        int unidades;
        int i;
        try {
            for (i = 0 ; i < pedido.getLineaPedido().size() ; i++){
                lineaPedido = (LineaPedido) pedido.getLineaPedido().get(i);
                unidades = lineaPedido.getUnidades();
                lineaPedido.getProducto().decrementarStock(unidades);
            }
        }catch ( Exception e){
                System.out.println("No hay stock suficiente de algunos de los productos");
                return false;
        }
        System.out.println("Pedido realizado");
        return true;
    }

    public List<Pedido> consultarPedido() {
    } //Falta buscar parametros

}
