/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;

import java.util.List;


/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio dos pedido_produtos
 */
public class Pedido_produtoNegocio {
    /**
     * Método utilizado para inserir um novo pedido_produto
     *
     * @param pedido_id
     * @param produto_id
     * @param quantidade
     * @param cliente_login
     * @return
     */
    public boolean inserir(Long pedido_id, Integer produto_id, Integer quantidade, String cliente_login) {
        if (pedido_id == null || pedido_id <= 0 || produto_id == null || produto_id <= 0 || quantidade == null || quantidade <= 0 || cliente_login == null || cliente_login.trim().length() == 0) {
            return false;
        }
        Pedido_produtoDAO dao = new Pedido_produtoDAO();
        return dao.inserir(pedido_id, produto_id, quantidade, cliente_login);
    }
    
    /**
     * Método utilizado para retornar todos os pedido_produtos existentes
     * 
     * @return 
     */
    public List<Pedido_produto> obterTodos() {
        Pedido_produtoDAO dao = new Pedido_produtoDAO();
        return dao.obterTodos();
    }

    /**
     * Método utilizado para obter o pedido_produto por meio do seu pedido_id e seu produto_id
     *
     * @param pedido_id
     * @param produto_id
     * @return
     */
    public Pedido_produto obterPedido_produto(Long pedido_id, Integer produto_id) {
        if (pedido_id == null || pedido_id <= 0 || produto_id == null || produto_id <= 0) {
            return null;
        }
        Pedido_produtoDAO dao = new Pedido_produtoDAO();
        return dao.obterPedido_produto(pedido_id, produto_id);
    }

    /**
     * Método utilizado para alterar um pedido_produto existente
     *
     * @param pedido_id
     * @param produto_id
     * @param quantidade
     * @param cliente_login
     * @return
     */
    public boolean alterar(Long pedido_id, Integer produto_id, Integer quantidade, String cliente_login) {
        if (pedido_id == null || pedido_id <= 0 || produto_id == null || produto_id <= 0 || quantidade == null || quantidade <= 0) {
            return false;
        }
        Pedido_produtoDAO dao = new Pedido_produtoDAO();
        return dao.alterar(pedido_id, produto_id, quantidade, cliente_login);
    }

    /**
     * Método utilizado para excluir o pedido_produto por meio do seu pedido_id e seu produto_id
     *
     * @param pedido_id
     * @param produto_id
     * @return
     */
    public boolean excluir(Long pedido_id, Integer produto_id) {
        if (pedido_id == null || pedido_id <= 0 || produto_id == null || produto_id <= 0) {
            return false;
        }
        Pedido_produtoDAO dao = new Pedido_produtoDAO();
        return dao.excluir(pedido_id, produto_id);
    }
}
