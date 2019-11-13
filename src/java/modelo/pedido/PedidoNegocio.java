/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

import java.util.List;
/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio dos pedidos
 */
public class PedidoNegocio {
    /**
     * Método utilizado para inserir um novo pedido
     *
     * @param id
     * @param observacoes
     * @param agendamento
     * @param senhadopedido
     * @param status
     * @param valortotal
     * @param cliente_login
     * @param estabelecimento_login
     * @return
     */
    public boolean inserir(Long id, String observacoes, String agendamento, String senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
        if (id == null || id <= 0 || senhadopedido == null || senhadopedido.trim().length() == 0 || status == null || status.trim().length() == 0 || valortotal == null || valortotal <= 0 || cliente_login == null || cliente_login.trim().length() == 0 || estabelecimento_login == null || estabelecimento_login.trim().length() == 0) {
            return false;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.inserir(id, observacoes, agendamento, senhadopedido, status, valortotal, cliente_login, estabelecimento_login);
    }
    
    /**
     * Método utilizado para retornar todos os pedidos existentes
     * 
     * @return 
     */
    public List<Pedido> obterTodos() {
        PedidoDAO dao = new PedidoDAO();
        return dao.obterTodos();
    }
    
    /**
     * Método utilizado para retornar todos os pedidos de um certo cliente pelo seu login
     * 
     * @param cliente_login
     * @return 
     */
    public List<Pedido> obterTodosPorLogin(String cliente_login) {
        if (cliente_login == null || cliente_login.trim().length() == 0) {
            return null;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.obterTodosPorLogin(cliente_login);
    }

    /**
     * Método utilizado para obter um pedido por meio do seu id
     *
     * @param id
     * @return
     */
    public Pedido obterPedido(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.obterPedido(id);
    }
    
        /**
     * Método utilizado para obter pedido por meio do login do cliente
     *
     * @param cliente_login
     * @return
     */
    public Pedido obterPedidoPorLogin(String cliente_login) {
        if (cliente_login == null || cliente_login.trim().length() == 0) {
            return null;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.obterPedidoPorLogin(cliente_login);
    }

    /**
     * Método utilizado para alterar um pedido existente
     *
     * @param id
     * @param observacoes
     * @param agendamento
     * @param senhadopedido
     * @param status
     * @param valortotal
     * @param cliente_login
     * @param estabelecimento_login
     * @return
     */
    public boolean alterar(Long id, String observacoes, String agendamento, String senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
        if (id == null || id <= 0 || senhadopedido == null || senhadopedido.trim().length() == 0 || status == null || status.trim().length() == 0 || valortotal == null || valortotal <= 0 || cliente_login == null || cliente_login.trim().length() == 0 || estabelecimento_login == null || estabelecimento_login.trim().length() == 0) {
            return false;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.alterar(id, observacoes, agendamento, senhadopedido, status, valortotal, cliente_login, estabelecimento_login);
    }

    /**
     * Método utilizado para excluir um pedido por meio do seu id
     *
     * @param id
     * @return
     */
    public boolean excluir(Long id) {
        if (id == null || id <= 0) {
            return false;
        }
        PedidoDAO dao = new PedidoDAO();
        return dao.excluir(id);
    }
    
    
}
