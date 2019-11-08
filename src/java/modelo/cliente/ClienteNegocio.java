/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

import java.util.List;

/**
 *
 *
 *
 * Classe de negócio que encapsula as regras de negócio dos clientes
 */
public class ClienteNegocio {

    /**
     * Método que verifica se o login e senha de um cliente é válido
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean efetuarLogin(String login, String senha) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0) {
            return false;
        }
        ClienteDAO dao = new ClienteDAO();
        Cliente cliente = dao.obterCliente(login);
        return (cliente != null && cliente.getSenha().equals(senha));
    }

    /**
     * Método utilizado para inserir um novo cliente
     *
     * @param nome
     * @param login
     * @param senha
     * @param email
     * @param telefone
     * @return
     */
    public boolean inserir(String login, String senha, String nome, String email, Long telefone) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0 || nome == null || nome.trim().length() == 0 || email == null || email.trim().length() == 0) {
            return false;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.inserir(login, senha, nome, email, telefone);
    }
    
    /**
     * Método utilizado para retornar todos os clientes existentes
     * 
     * @return 
     */
    public List<Cliente> obterTodos() {
        ClienteDAO dao = new ClienteDAO();
        return dao.obterTodos();
    }

    /**
     * Método utilizado para obter o cliente por meio do seu login
     *
     * @param login
     * @return
     */
    public Cliente obterCliente(String login) {
        if (login == null || login.trim().length() == 0) {
            return null;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.obterCliente(login);
    }

    /**
     * Método utilizado para alterar um cliente existente
     *
     * @param login
     * @param senha
     * @param nome
     * @param email
     * @param telefone
     * @return
     */
    public boolean alterar(String login, String senha, String nome, String email, Long telefone) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0 || nome == null || nome.trim().length() == 0 || email == null || email.trim().length() == 0) {
            return false;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.alterar(login, senha, nome, email, telefone);
    }

    /**
     * Método utilizado para excluir o cliente por meio do seu login
     *
     * @param login
     * @return
     */
    public boolean excluir(String login) {
        if (login == null || login.trim().length() == 0) {
            return false;
        }
        ClienteDAO dao = new ClienteDAO();
        return dao.excluir(login);
    }

}
