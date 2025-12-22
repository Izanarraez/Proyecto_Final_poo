package Capa_Repositorio.AlmacenDatos;

import Capa_Dominio.Cliente.Cliente;
import Capa_Repositorio.Interfaces.ServicioCliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioCliente implements ServicioCliente {

    private ArrayList<Cliente> almacenClientes = new ArrayList<Cliente>();

    @Override
    public void alta(Cliente cliente) {
        almacenClientes.add(cliente);
    }

    @Override
    public List<Cliente> consultar(String nombre) {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        for(Cliente cliente: almacenClientes){
            if (cliente.getNombre().equals(nombre)){
                clientes.add(cliente);
            }
        }
        return Collections.unmodifiableList(clientes);
    }

    @Override
    public List<Cliente> listar() {
        return Collections.unmodifiableList(almacenClientes);
    }
}
