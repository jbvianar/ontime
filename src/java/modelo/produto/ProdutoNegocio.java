/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;

import java.util.List;
/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio dos produtos
 */
public class ProdutoNegocio {
    /**
     * Método utilizado para inserir um novo produto
     *
     * @param id
     * @param nome
     * @param descricao
     * @param preco
     * @param imagem
     * @param quantidade
     * @param disponibilidade
     * @param categoria_id
     * @return
     */
    public boolean inserir(String nome, String descricao, Double preco, String imagem, Integer quantidade, Boolean disponibilidade, Integer categoria_id) {
        if (nome == null || nome.trim().length() == 0 || preco == null || preco <= 0 || quantidade == null || quantidade < 0 || disponibilidade == null || categoria_id == null || categoria_id <= 0) {
            return false;
        }
        ProdutoDAO dao = new ProdutoDAO();
        return dao.inserir(nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id);
    }
    
    /**
     * Método utilizado para retornar todos os produtos existentes
     * 
     * @return 
     */
    public List<Produto> obterTodos() {
        ProdutoDAO dao = new ProdutoDAO();
        return dao.obterTodos();
    }

    /**
     * Método utilizado para obter o produto por meio do seu id
     *
     * @param id
     * @return
     */
    public Produto obterProduto(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        ProdutoDAO dao = new ProdutoDAO();
        return dao.obterProduto(id);
    }

    /**
     * Método utilizado para alterar um produto existente
     *
     * @param id
     * @param nome
     * @param descricao
     * @param preco
     * @param imagem
     * @param quantidade
     * @param disponibilidade
     * @param categoria_id
     * @return
     */
    public boolean alterar(Integer id, String nome, String descricao, Double preco, String imagem, Integer quantidade, Boolean disponibilidade, Integer categoria_id) {
        if (id == null || id <= 0 || nome == null || nome.trim().length() == 0 || preco == null || preco <= 0 || quantidade == null || quantidade < 0 || disponibilidade == null || categoria_id == null || categoria_id <= 0) {
            return false;
        }
        ProdutoDAO dao = new ProdutoDAO();
        return dao.alterar(id, nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id);
    }

    /**
     * Método utilizado para excluir o produto por meio do seu id
     *
     * @param id
     * @return
     */
    public boolean excluir(Integer id) {
        if (id == null || id <= 0) {
            return false;
        }
        ProdutoDAO dao = new ProdutoDAO();
        return dao.excluir(id);
    }
}
