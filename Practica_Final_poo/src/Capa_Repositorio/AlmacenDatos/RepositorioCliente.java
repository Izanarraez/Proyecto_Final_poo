package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Cliente.Cliente;
import Capa_Repositorio.Interfaces.ServicioCliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación en memoria del repositorio de clientes.
 * Almacena los clientes en una lista interna.
 */
public class RepositorioCliente implements ServicioCliente {

    private final ArrayList<Cliente> almacenClientes;

    /**
     * Inicializa el almacén de clientes.
     */
    public RepositorioCliente(){
        this.almacenClientes = new ArrayList<Cliente>();
    }

    /**
     * {@inheritDoc}
     * Valida que no exista ya un cliente con el mismo código.
     *
     * @throws IllegalArgumentException Si el código está duplicado.
     */
    @Override
    public void alta(Cliente cliente) {

        for(Cliente clienteAlmacenado : almacenClientes){
            if(clienteAlmacenado.getCodcliente().equals(cliente.getCodcliente())){
                throw  new IllegalArgumentException("Id de cliente ya existe");
            }
        }
        almacenClientes.add(cliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> consultar(String nombre) {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        for(Cliente cliente: almacenClientes){
            if (cliente.getNombre().toLowerCase().equals(nombre.toLowerCase())){
                clientes.add(cliente);
            }
        }
        return Collections.unmodifiableList(clientes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> listar() {
        return Collections.unmodifiableList(almacenClientes);
    }
}
