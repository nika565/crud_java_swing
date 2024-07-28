package br.com.nathan;

import br.com.nathan.dao.ClienteMapDAO;
import br.com.nathan.dao.ClienteSetDAO;
import br.com.nathan.dao.IClienteDAO;
import br.com.nathan.domain.Cliente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {

        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair", "Projeto ebac", JOptionPane.INFORMATION_MESSAGE);

        while(!isOpcaoValida(opcao)) {
            if("".equals(opcao)){
                sair();
            }

            opcao = JOptionPane.showInputDialog(null, "Opção Inválida! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair", "Opção inválida", JOptionPane.INFORMATION_MESSAGE);
        }

        while(isOpcaoValida(opcao)){
            if(isOpcaoSair(opcao)){
                sair();
            } else if(isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if(isConsultar(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF", "Consultar", JOptionPane.INFORMATION_MESSAGE);
                consultar(dados);
            } else if(isExcluir(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite o CPF", "Excluir", JOptionPane.INFORMATION_MESSAGE);
                excluir(dados);
            } else if(isAlterar(opcao)){
                String dados = JOptionPane.showInputDialog(null, "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado.", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                editar(dados);
            }
            opcao = JOptionPane.showInputDialog(null, "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair", "Opção inválida", JOptionPane.INFORMATION_MESSAGE);

        }

    }

    private static void editar(String dados) {
        // Validar se os campos foram preenchidos
        if(dados.isEmpty()){
            JOptionPane.showMessageDialog(null, "É necessário preencher todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Convertendo para uma lista e excutar operações
        String[] dadosSeparados = dados.split(",");
        ArrayList<String> lista = new ArrayList<>();

        for(var i = 0; i < dadosSeparados.length; i++){
            if(dadosSeparados[i].trim().isEmpty()){
                lista.add(null);
                continue;
            }

            lista.add(dadosSeparados[i]);
        }

        int tamanhoLista = lista.size();

        if(tamanhoLista < 7){
            for(var i = 0; i < 7 - tamanhoLista; i++){
                lista.add("0");
            }
        }

        // Se não tiver passar null no construtor onde o valor é nulo
        Cliente cliente = new Cliente(lista.get(0), lista.get(1), lista.get(2), lista.get(3), lista.get(4), lista.get(5), lista.get(6));
        iClienteDAO.alterar(cliente);

        JOptionPane.showMessageDialog(null, "Cliente editado com sucesso com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

    }

    private static boolean isAlterar(String opcao) {
        if("4".equals(opcao)) {
            return true;
        }

        return false;
    }

    private static void excluir(String cpf) {
        iClienteDAO.excluir(Long.parseLong(cpf));
        JOptionPane.showMessageDialog(null, "Registro excluído com sucesso.", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isExcluir(String opcao) {
        if("3".equals(opcao)) {
            return true;
        }

        return false;
    }

    private static void consultar(String dados) {
        Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
        if(cliente != null){
            JOptionPane.showMessageDialog(null, "Cliente encontrado " + cliente.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, "Cliente não foi encontrado", "Erro", JOptionPane.INFORMATION_MESSAGE);

    }

    private static boolean isConsultar(String opcao) {
        if("2".equals(opcao)) {
            return true;
        }

        return false;
    }

    private static void cadastrar(String dados) {

        // Validar se os campos foram preenchidos
        if(dados.isEmpty()){
            JOptionPane.showMessageDialog(null, "É necessário preencher todos os campos", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Convertendo para uma lista e excutar operações
        String[] dadosSeparados = dados.split(",");
        ArrayList<String> lista = new ArrayList<>();

        for(var i = 0; i < dadosSeparados.length; i++){
            if(dadosSeparados[i].trim().isEmpty()){
                lista.add(null);
                continue;
            }

            lista.add(dadosSeparados[i]);
        }

        int tamanhoLista = lista.size();

        if(tamanhoLista < 7){
            for(var i = 0; i < 7 - tamanhoLista; i++){
                lista.add("0");
            }
        }

        // Se não tiver passar null no construtor onde o valor é nulo
        Cliente cliente = new Cliente(lista.get(0), lista.get(1), lista.get(2), lista.get(3), lista.get(4), lista.get(5), lista.get(6));
        Boolean isCadastrado = iClienteDAO.cadastrar(cliente);

        if(isCadastrado){
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se econtra cadastrado", "Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private static boolean isCadastro(String opcao) {
        if("1".equals(opcao)) {
            return true;
        }

        return false;
    }

    private static boolean isOpcaoSair(String opcao) {
        if("5".equals(opcao)) {
            return true;
        }

        return false;
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        if("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao)){
            return true;
        }

        return false;
    }

    private static boolean isOpcaoCadastro(String opcao) {
        if("1".equals(opcao)) {
            return true;
        }

        return false;
    }

}
