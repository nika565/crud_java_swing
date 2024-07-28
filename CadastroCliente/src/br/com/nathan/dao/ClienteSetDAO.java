package br.com.nathan.dao;

import br.com.nathan.domain.Cliente;

import java.util.*;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> set;

    public ClienteSetDAO() {
        this.set = new HashSet<>();
    }

    @Override
    public Boolean cadastrar(Cliente cliente) {

        if(!this.set.isEmpty()) {

            for(Cliente clienteSet: this.set){
                if(clienteSet.getCpf().equals(cliente.getCpf())) {
                    return false;
                }
            }
        }

        this.set.add(cliente);
        return true;

    }

    @Override
    public void excluir(Long cpf) {
        // Verificação se a coleção não está vazia
        if(!this.set.isEmpty()){

            // Iterando na coleção para saber se existe o cliente
            for(Cliente clienteSet: this.set){

                // Verificando se o cpf é igual a cada iteração
                // Se for igual o objeto clinete é removido.
                if(clienteSet.getCpf().equals(cpf)){
                    this.set.remove(clienteSet);
                    return;
                }
            }
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        for(Cliente clienteSet : this.set){
            if(clienteSet.getCpf().equals(cliente.getCpf())){
                clienteSet.setNome(cliente.getNome());
                clienteSet.setEnd(cliente.getEnd());
                clienteSet.setEstado(cliente.getEstado());
                clienteSet.setTel(cliente.getTel());
                clienteSet.setNumero(cliente.getNumero());
            }
        }
    }

    @Override
    public Cliente consultar(Long cpf) {

        if(!this.set.isEmpty()){

            for(Cliente cliente : this.set){
                if(cliente.getCpf().equals(cpf)){
                    return cliente;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.set;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteSetDAO that = (ClienteSetDAO) o;
        return Objects.equals(set, that.set);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(set);
    }
}
