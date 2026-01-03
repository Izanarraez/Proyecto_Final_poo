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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TiendaServicio {

    private final ServicioCliente repositorioCliente;
    private final ServicioProducto repositorioProducto;
    private final ServicioPedido repositorioPedido;

    public TiendaServicio() {
        this.repositorioCliente = new RepositorioCliente();
        this.repositorioProducto = new RepositorioProducto();
        this.repositorioPedido = new RepositorioPedido();
    }

    /**Funcionalidades Producto*/
    public void altaProducto(Producto producto) {
        this.repositorioProducto.alta(producto);
    }

    public void ajustarStock(String codigo, int cantidad, boolean incremento){
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
                    throw new IllegalArgumentException("Error al ajustar stock: " + e.getMessage());
                }
                return;
            }
        }
        throw new IllegalArgumentException("No se encontro el producto con el codigo: " + codigo);
    }

    public List<Producto> consultarProductos(String nombre) {
        return this.repositorioProducto.consultar(nombre);
    }

    public List<Producto> listadoProductos() {
        return this.repositorioProducto.listar();
    }

    public void borrarProducto(String codigo) throws Exception {

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
            throw new Exception("No se puede borrar: Producto asociado a un pedido confirmado.");
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
            throw new IllegalStateException("Error: No se pueden añadir líneas a un pedido confirmado.");
        }
        try {
            LineaPedido lineaPedido = new LineaPedido(producto, unidades, producto.getPrecioUnitario());
            pedido.añadirLineaPedido(lineaPedido);
        } catch (Exception e) {
            throw e; //Se relanza la excepcion anterior
        }
    }

    public void confirmarPedido(Pedido pedido) throws Exception {

        if (pedido.isConfirmado()) {
            throw new IllegalStateException("El pedido ya estaba confirmado.");
        }

        List<String> productosConfirmados = new  ArrayList<String>();

        for (LineaPedido lineaPedidoActual : pedido.getLineaPedido()) {

            Producto productoActual = lineaPedidoActual.getProducto();
            String codigoProducto = productoActual.getCodigoProducto();

            if (productosConfirmados.contains(codigoProducto)) {
                continue;
            }

            int cantidadRequerida = 0;

            for (LineaPedido lineaPedido : pedido.getLineaPedido()) {
                if (lineaPedido.getProducto().getCodigoProducto().equals(codigoProducto)) {
                    cantidadRequerida += lineaPedido.getUnidades();
                }
            }

            if (productoActual.getStock() < cantidadRequerida){
                throw  new Exception("No hay suficiente stock "+productoActual.getStock()+" del producto: " + productoActual.getNombre());
            }

            productosConfirmados.add(codigoProducto);
        }

        for (LineaPedido lineaPedido : pedido.getLineaPedido()) {
            lineaPedido.getProducto().decrementarStock(lineaPedido.getUnidades());
        }
        pedido.setConfirmado(true);
    }

    public List<Pedido> consultarPedido(String codigo) {
        return repositorioPedido.consultar(codigo);
    }

}
