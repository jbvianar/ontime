/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria;

import java.util.List;
/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio das categorias
 */
public class CategoriaNegocio {
    /**
     * Método utilizado para inserir uma nova categoria
     *
     * @param nome
     * @return
     */
    public boolean inserir(String nome) {
        if (nome == null || nome.trim().length() == 0) {
            return false;
        }
        CategoriaDAO dao = new CategoriaDAO();
        return dao.inserir(nome);
    }
    
    /**
     * Método utilizado para retornar todas as categorias existentes
     * 
     * @return 
     */
    public List<Categoria> obterTodos() {
        CategoriaDAO dao = new CategoriaDAO();
        return dao.obterTodos();
    }

    /**
     * Método utilizado para obter a categoria por meio do seu id
     *
     * @param id
     * @return
     */
    public Categoria obterCategoria(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        CategoriaDAO dao = new CategoriaDAO();
        return dao.obterCategoria(id);
    }

    /**
     * Método utilizado para alterar uma categoria existente
     *
     * @param id
     * @param nome
     * @return
     */
    public boolean alterar(Integer id, String nome) {
        if (id == null || id <= 0 || nome == null || nome.trim().length() == 0) {
            return false;
        }
        CategoriaDAO dao = new CategoriaDAO();
        return dao.alterar(id, nome);
    }

    /**
     * Método utilizado para excluir a categoria por meio do seu id
     *
     * @param id
     * @return
     */
    public boolean excluir(Integer id) {
        if (id == null || id <= 0) {
            return false;
        }
        CategoriaDAO dao = new CategoriaDAO();
        return dao.excluir(id);
    }
    
}
