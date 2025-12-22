package Capa_Repositorio.Interfaces;

import Capa_Dominio.Cliente.Cliente;

import java.util.List;

public interface ServicioCliente {

    void alta(Cliente cliente);
    List<Cliente> consultar(String nombre);
    List<Cliente> listar ();

}
