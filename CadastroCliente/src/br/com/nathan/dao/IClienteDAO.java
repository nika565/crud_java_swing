package br.com.nathan.dao;

import br.com.nathan.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    // Métodos de implementação
    public Boolean cadastrar(Cliente cliente);
    public void excluir(Long cpf);
    public void alterar(Cliente cliente);
    public Cliente consultar(Long cpf);
    public Collection<Cliente> buscarTodos();

}
