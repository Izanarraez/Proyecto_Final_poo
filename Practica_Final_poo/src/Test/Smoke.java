package Test;

import Capa_Dominio.Cliente.*;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Servicio.TiendaServicio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Smoke {
    public static void main(String[] args) {
        try {
            Cliente cliente = new Cliente("h111", "Juan", "89987987689");
            Producto producto = new Producto("hidhiqhd", "Yogur", "Alimento", 1.5F, 6);
            LineaPedido lineaPedido = new LineaPedido(producto, 4, producto.getPrecioUnitario());
            Pedido pedido = new Pedido(cliente, 0.21F);
            TiendaServicio tiendaServicio = new TiendaServicio();
            tiendaServicio.crearPedido(pedido);
            tiendaServicio.altaCliente(cliente);
            tiendaServicio.altaProducto(producto);
            tiendaServicio.añadirLineaPedido(pedido, lineaPedido.getProducto(), lineaPedido.getUnidades());
            tiendaServicio.consultarClientes("Juan");
            for (int i = 0 ; i<tiendaServicio.consultarClientes("Juan").size(); i++){
                System.out.println(tiendaServicio.consultarClientes("Juan").get(i));
            }
            tiendaServicio.consultarPedido("h111");
            for (int i = 0 ; i<tiendaServicio.consultarPedido("h111").size(); i++){
                System.out.println(tiendaServicio.consultarPedido("h111").get(i));
            }
            tiendaServicio.consultarProductos("hidhiqhd");

            for (int i = 0 ; i<tiendaServicio.consultarProductos("hidhiqhd").size(); i++){
                System.out.println(tiendaServicio.consultarProductos("hidhiqhd").get(i));
            }
            tiendaServicio.listadoClientes();

            for (int i = 0 ; i<tiendaServicio.listadoClientes().size(); i++){
                System.out.println(tiendaServicio.listadoClientes().get(i));
            }
            tiendaServicio.listadoProductos();

            for (int i = 0 ; i<tiendaServicio.listadoProductos().size(); i++){
                System.out.println(tiendaServicio.listadoProductos().get(i));
            }
            tiendaServicio.confirmarPedido(pedido);
        }catch (Exception e){
            e.getMessage();
        }
    }
}