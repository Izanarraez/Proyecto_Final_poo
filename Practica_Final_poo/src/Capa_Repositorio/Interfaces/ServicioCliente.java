package Capa_Repositorio.Interfaces;

import Capa_Dominio.Cliente.Cliente;

import java.util.List;

/**
 * Contrato para el almacenamiento y recuperación de clientes.
 * Define las operaciones que debe cumplir cualquier repositorio de clientes.
 */
public interface ServicioCliente {

    /**
     * Guarda un nuevo cliente.
     * @param cliente Cliente a guardar.
     */
    void alta(Cliente cliente);
    /**
     * Busca Clientes por su nombre.
     * @param nombre Nombre a buscar.
     * @return Lista de clientes que coinciden.
     */
    List<Cliente> consultar(String nombre);
    /**
     * Obtiene todos los clientes.
     * @return Lista completa de clientes.
     */
    List<Cliente> listar ();

}
