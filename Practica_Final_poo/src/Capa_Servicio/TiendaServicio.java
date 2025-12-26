package Capa_Servicio;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.AlmacenDatos.RepositorioCliente;
import Capa_Repositorio.AlmacenDatos.RepositorioPedido;
import Capa_Repositorio.AlmacenDatos.RepositorioProducto;

import java.util.ArrayList;
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
    public void altaProducto(Producto producto) {
        this.repositorioProducto.alta(producto);
    }

    public void ajustarStock(String codigo, int cantidad, boolean incremento) {
        for (int i = 0; i < repositorioProducto.listar().size(); i++){
            if (repositorioProducto.listar().get(i).getCodigoProducto().equals(codigo)){
                try {
                    if (incremento) {
                        repositorioProducto.listar().get(i).incrementarStock(cantidad);
                    }
                    else{
                        repositorioProducto.listar().get(i).decrementarStock(cantidad);
                    }
                }catch (Exception e){
                    e.getMessage();
                    return;
                }
            }
        }
    }

    public List<Producto> consultarProductos(String nombre) {
        return(this.repositorioProducto.consultar(nombre));
    }

    public List<Producto> listadoProductos() {
        return(this.repositorioProducto.listar());
    }

    public void borrarProducto(String codigo) {

        try{

            boolean nombreProducto = false;
            boolean pedidoProducto = false;

            for (int i = 0; i < repositorioProducto.listar().size(); i++) {
                if (repositorioProducto.listar().get(i).getCodigoProducto().equals(codigo)) {
                    nombreProducto = true;
                }
            }
            for (int i = 0; i < repositorioPedido.listar().size(); i++) {
                for (int j = 0; j < repositorioPedido.listar().get(i).getLineaPedido().size(); j++) {
                    if (repositorioPedido.listar().get(i).getLineaPedido().get(j).getProducto().getCodigoProducto().equals(codigo)) {
                        pedidoProducto = true;
                    }
                }
            }


            if (nombreProducto) {
                if (pedidoProducto) {
                    throw  new IllegalArgumentException("No se puede borrar este producto porque esta asociado a un pedido");
                }
                else {
                    repositorioProducto.baja(codigo);
                }
            }
            else {
                throw new IllegalArgumentException("No se ha encontrado el producto");
            }
        }catch (Exception e){
            e.getMessage();
        }
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

    public void consultarPedido(String codigo) {

        ArrayList<Pedido> pedidos = (ArrayList<Pedido>) this.repositorioPedido.consultar(codigo);

        for (Pedido pedido : pedidos) {
            pedido.toString();
        }
    }

}
