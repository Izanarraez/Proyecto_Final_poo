package Capa_Servicio;

import Capa_Dominio.Cliente.Cliente;
import Capa_Dominio.Pedido.LineaPedido;
import Capa_Dominio.Pedido.Pedido;
import Capa_Dominio.Producto.Producto;
import Capa_Repositorio.Interfaces.ServicioCliente;
import Capa_Repositorio.Interfaces.ServicioPedido;
import Capa_Repositorio.Interfaces.ServicioProducto;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio principal que encapsula la lógica de negocio de la tienda.
 * <p>
 * Coordina las interacciones entre los repositorios
 * </p>
 */
public class TiendaServicio {

    private final ServicioCliente repositorioCliente;
    private final ServicioProducto repositorioProducto;
    private final ServicioPedido repositorioPedido;

    /**
     * Constructor con Inyección de Dependencias.
     *
     * @param repositorioCliente  Implementación del repositorio de clientes.
     * @param repositorioProducto Implementación del repositorio de productos.
     * @param repositorioPedido   Implementación del repositorio de pedidos.
     */
    public TiendaServicio(ServicioCliente repositorioCliente, ServicioProducto repositorioProducto, ServicioPedido repositorioPedido) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
        this.repositorioPedido = repositorioPedido;
    }

    /**Funcionalidades Producto*/

    /**
     * Registra un nuevo producto en el sistema.
     *
     * @param producto Producto a dar de alta.
     */
    public void altaProducto(Producto producto) {
        this.repositorioProducto.alta(producto);
    }

    /**
     * Modifica el stock de un producto existente.
     *
     * @param codigo     Código del producto.
     * @param cantidad   Cantidad a ajustar.
     * @param incremento true para sumar, false para restar.
     * @throws IllegalArgumentException Si el producto no existe o el stock resultante es negativo.
     */
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

    /**
     * Busca productos por nombre.
     *
     * @param nombre Nombre a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Producto> consultarProductos(String nombre) {
        return this.repositorioProducto.consultar(nombre);
    }

    /**
     * Lista todos los productos del catálogo.
     *
     * @return Lista completa.
     */
    public List<Producto> listadoProductos() {
        return this.repositorioProducto.listar();
    }

    /**
     * Elimina un producto si no está asociado a pedidos confirmados.
     *
     * @param codigo Código del producto a borrar.
     * @throws Exception Si el producto está asociado a un pedido.
     */
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

    /**
     * Registra un nuevo cliente.
     *
     * @param cliente Cliente a registrar.
     */
    public void altaCliente(Cliente cliente) {
        this.repositorioCliente.alta(cliente);
    }

    /**
     * Consulta clientes por nombre.
     *
     * @param nombre Texto a buscar.
     * @return Lista de clientes.
     */
    public List<Cliente> consultarClientes(String nombre) {
        return this.repositorioCliente.consultar(nombre);
    }

    /**
     * Lista todos los clientes.
     *
     * @return Lista completa.
     */
    public List<Cliente> listadoClientes() {
        return this.repositorioCliente.listar();
    }


    /**Funcionalidades Pedidos*/

    /**
     * Registra un nuevo pedido.
     *
     * @param pedido Pedido a crear.
     */
    public void crearPedido(Pedido pedido) {
        this.repositorioPedido.alta(pedido);
    }

    /**
     * Añade una línea a un pedido y delega la validación de estado al dominio.
     *
     * @param pedido   Pedido destino.
     * @param producto Producto a añadir.
     * @param unidades Cantidad.
     * @throws IllegalStateException Si el pedido ya está confirmado.
     */
    public void añadirLineaPedido(Pedido pedido, Producto producto, int unidades) {
        if (pedido.isConfirmado()) {
            throw new IllegalStateException("Error: No se pueden añadir líneas a un pedido confirmado.");
        }
        try {
            LineaPedido lineaPedido = new LineaPedido(producto, unidades, producto.getPrecioUnitario());
            pedido.añadirLineaPedido(lineaPedido);
        } catch (Exception e) {
            throw e; //Relanza excepcion
        }
    }


    /**
     * Confirma un pedido.
     *
     * <p>
     * 1. Agrupa cantidades totales por producto.<br>
     * 2. Valida stock suficiente para el total.<br>
     * 3. Descuenta stock y cierra el pedido.
     * </p>
     *
     * @param pedido Pedido a confirmar.
     * @throws Exception Si no hay stock suficiente o ya estaba confirmado.
     */
    public void confirmarPedido(Pedido pedido) throws Exception {

        if (pedido.isConfirmado()) {
            throw new IllegalStateException("El pedido ya estaba confirmado.");
        }

        List<String> productosConfirmados = new  ArrayList<String>();

        for (LineaPedido lineaPedidoActual : pedido.getLineaPedido()) {

            Producto productoActual = lineaPedidoActual.getProducto();
            String codigoProducto = productoActual.getCodigoProducto();

            boolean procesado = false;
            for (String codigoConfirmado : productosConfirmados) {
                if (codigoConfirmado.equals(codigoProducto)) {
                    procesado = true;
                    break;
                }
            }

            if (procesado) {
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

    /**
     * Consulta pedidos por código de cliente.
     *
     * @param codigo Código del cliente.
     * @return Lista de pedidos del cliente.
     */
    public List<Pedido> consultarPedido(String codigo) {
        return repositorioPedido.consultar(codigo);
    }

    /**
     * Calcula el importe total acumulado de todos los pedidos confirmados.
     *
     * @return Suma total con IVA.
     */
    public float calcularTotalFacturado() {
        float total = 0f;
        for (Pedido pedido : this.repositorioPedido.listar()) {
            if (pedido.isConfirmado()) {
                total += pedido.calcularTotalConIva();
            }
        }
        return total;
    }

}
