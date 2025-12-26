package Capa_Servicio;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.AlmacenDatos.RepositorioCliente;
import Capa_Repositorio.AlmacenDatos.RepositorioPedido;
import Capa_Repositorio.AlmacenDatos.RepositorioProducto;

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
        for (Producto producto : this.repositorioProducto.listar()) {
            if (producto.getCodigoProducto().equals(codigo)){
                try {
                    if (incremento) {
                        producto.incrementarStock(cantidad);
                    }
                    else{
                        producto.decrementarStock(cantidad);
                    }
                }catch (Exception e){
                    System.out.println("Error al ajustar stock: " + e.getMessage());
                }
                return;
            }
        }
    }

    public List<Producto> consultarProductos(String nombre) {
        return this.repositorioProducto.consultar(nombre);
    }

    public List<Producto> listadoProductos() {
        return this.repositorioProducto.listar();
    }

    public void borrarProducto(String codigo) {

        boolean existeProducto = false;
        boolean pedidoConfirmadoEnProducto = false;

        for (Producto producto : this.repositorioProducto.listar()) {
            if (producto.getCodigoProducto().equals(codigo)){
                existeProducto = true;
                break;
            }
        }

        if (!existeProducto) {
            throw new IllegalArgumentException("No se ha encontrado el producto con código: " + codigo);
        }

        for (Pedido pedido : this.repositorioPedido.listar()) {
            if (pedido.isConfirmado()){
                for (LineaPedido linea : pedido.getLineaPedido()) {
                    if (linea.getProducto().getCodigoProducto().equals(codigo)) {
                        pedidoConfirmadoEnProducto = true;
                        break;
                    }
                }
            }
            if (pedidoConfirmadoEnProducto) break;
        }

        if (pedidoConfirmadoEnProducto) {
            throw new IllegalArgumentException("No se puede borrar: Producto asociado a un pedido confirmado.");
        } else {
            repositorioProducto.baja(codigo);
        }
    }


    /**Funcionalidades Cliente*/

    public void altaCliente(Cliente cliente) {
        this.repositorioCliente.alta(cliente);
    }

    public List<Cliente> consultarClientes(String nombre) {
        return this.repositorioCliente.consultar(nombre);
    }

    public List<Cliente> listadoClientes() {
        return this.repositorioCliente.listar();
    }


    /**Funcionalidades Pedidos*/

    public void crearPedido(Pedido pedido) {
        this.repositorioPedido.alta(pedido);
    }

    public void añadirLineaPedido(Pedido pedido, Producto producto, int unidades) {
        if (pedido.isConfirmado()) {
            System.out.println("Error: No se pueden añadir líneas a un pedido confirmado.");
            return;
        }
        try {
            LineaPedido lineaPedido = new LineaPedido(producto, unidades, producto.getPrecioUnitario());
            pedido.getLineaPedido().add(lineaPedido);
        } catch (Exception e) {
            System.out.println("Error al añadir línea de pedido: " + e.getMessage());
        }
    }

    public boolean confirmarPedido(Pedido pedido) {

        if (pedido.isConfirmado()) {
            System.out.println("El pedido ya estaba confirmado.");
            return true;
        }

        for (LineaPedido lineaPedido : pedido.getLineaPedido()) {
            if (lineaPedido.getProducto().getStock() < lineaPedido.getUnidades()){
                System.out.println("Error: Stock insuficiente para el producto " + lineaPedido.getProducto().getNombre());
                return false;
            }
        }
        try {
            for (LineaPedido lineaPedido : pedido.getLineaPedido()) {
                lineaPedido.getProducto().decrementarStock(lineaPedido.getUnidades());
            }
            pedido.setConfirmado(true);
            System.out.println("Pedido confirmado correctamente");
            return true;
        }catch ( Exception e){
            System.out.println("Error al confirmar pedido: " + e.getMessage());
            return false;
        }
    }

    public List<Pedido> consultarPedido(String codigo) {
        return repositorioPedido.consultar(codigo);
    }

}
