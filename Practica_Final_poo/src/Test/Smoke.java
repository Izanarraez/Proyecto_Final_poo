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

        System.out.println("Clase Smoke");

        TiendaServicio tiendaServicio = new TiendaServicio();

        Cliente juan = null;
        Producto yogur = null, pan = null;
        Pedido pedido = null;

        System.out.println("//--Casos de Uso--//");

        try {
            juan = new Cliente("C001", "Juan", "89987987689");

            tiendaServicio.altaCliente(juan);

            yogur = new Producto("P001", "Yogur", "Lacteos", 1.5F, 20);
            pan = new Producto("P002", "Pan", "Panaderia", 2F, 10);

            tiendaServicio.altaProducto(yogur);
            tiendaServicio.altaProducto(pan);

            System.out.println("[Cliente y productos creados correctamente]");

            pedido = new Pedido(juan, 0.21F);

            tiendaServicio.crearPedido(pedido);

            tiendaServicio.añadirLineaPedido(pedido,yogur,4);
            tiendaServicio.añadirLineaPedido(pedido,pan,2);

            System.out.println("[Pedido creado correctamente y Lineas de Pedido añadidas correctamente]");

            tiendaServicio.confirmarPedido(pedido);

            System.out.println("[Pedido confirmado correctamente]");

            System.out.println("** Detalles de pedido **");
            for (LineaPedido lineaPedido : pedido.getLineaPedido()){
                System.out.println("Nombre producto:"+lineaPedido.getProducto().getNombre()+
                                    " Unidades producto:"+ lineaPedido.getUnidades()+
                                    " Precio Aplicado:"+lineaPedido.getPrecioAplicado());
            }
            System.out.println("Precio total con iva " + pedido.calcularTotalConIva());

        }catch (Exception e){
            System.out.println("Se ha producido un fallo en:"+ e.getMessage());
        }

        System.out.println("//--Reglas de Negocio--//");

        System.out.println("Test 1: Stock agotado sumando varias lineas de Pedido");
        try {
            Pedido pedidoFallo = new Pedido(juan, 0.21F);
            tiendaServicio.crearPedido(pedidoFallo);

            tiendaServicio.añadirLineaPedido(pedidoFallo,pan,4);
            tiendaServicio.añadirLineaPedido(pedidoFallo,pan,5);

            tiendaServicio.confirmarPedido(pedidoFallo);

        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("Test 2: Modificar pedido ya confirmado");
        try{
            tiendaServicio.añadirLineaPedido(pedido,yogur,1);
        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("Test 3: Borrar producto en pedido confirmado");
        try{
            tiendaServicio.borrarProducto("P001");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("//--Programacion defensiva--//");

        System.out.println("Test 1: Alta producto duplicado");
        try{
            tiendaServicio.altaProducto(new Producto("P001", "Yogur2", "Lacteos", 2F, 20));
        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("Test 2: Alta producto con precio negativo");
        try{
            Producto producto = new Producto("P003", "Yogur3", "Lacteos", -3, 6);
        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("Test 3: Alta producto con stock negativo");
        try{
            Producto producto = new Producto("P004", "Yogur4", "Lacteos", 1F, -1);
        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("Test 4: Alta pedido con iva fuera de rango");
        try{
            Pedido pedidoFallo = new Pedido(juan, 1.5F);
        }catch (Exception e){
            System.out.println("Se ha producido el siguinete error "+e.getMessage());
        }

        System.out.println("//--Verificar el estado final del inventario--//");

        System.out.println("Stock yogur "+yogur.getStock()+" (stock esperado:16)");
        System.out.println("Stock yogur "+pan.getStock()+" (stock esperado:8)");
    }
}