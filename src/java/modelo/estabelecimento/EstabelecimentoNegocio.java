/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estabelecimento;

import java.util.List;
/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio dos estabelecimentos
 */
public class EstabelecimentoNegocio {
   //método que muda só o status do estabelecimento
    public boolean mudarStatus(String login, Boolean status) {
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.mudarStatus(login, status);
    }
    /**
     * Método que verifica se o login e senha de um estabelecimento é válido
     *
     * @param login
     * @param senha
     * @return
     */
    public boolean efetuarLogin(String login, String senha) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0) {
            return false;
        }
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        Estabelecimento estabelecimento = dao.obterEstabelecimento(login);
        return (estabelecimento != null && estabelecimento.getSenha().equals(senha));
    }

    /**
     * Método utilizado para inserir um novo estabelecimento
     *
     * @param login
     * @param senha
     * @param razaosocial
     * @param email
     * @param cnpj
     * @param telefone
     * @param status
     * @return
     */
    public boolean inserir(String login, String senha, String razaosocial, String email, Long cnpj, Long telefone, Boolean status) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0 || razaosocial == null || razaosocial.trim().length() == 0 || email == null || email.trim().length() == 0 || cnpj == null || cnpj <=0 || telefone == null || telefone <=0 || email.trim().length() == 0) {
            return false;
        }
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.inserir(login, senha, razaosocial, email, cnpj, telefone, status);
    }
    
    /**
     * Método utilizado para retornar todos os estabelecimentos existentes
     * 
     * @return 
     */
    public List<Estabelecimento> obterTodos() {
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.obterTodos();
    }

    /**
     * Método utilizado para obter o estabelecimento por meio do seu login
     *
     * @param login
     * @return
     */
    public Estabelecimento obterEstabelecimento(String login) {
        if (login == null || login.trim().length() == 0) {
            return null;
        }
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.obterEstabelecimento(login);
    }

    /**
     * Método utilizado para alterar um estabelecimento existente
     *
     * @param login
     * @param senha
     * @param razaosocial
     * @param email
     * @param cnpj
     * @param telefone
     * @param status
     * @return
     */
    public boolean alterar(String login, String senha, String razaosocial, String email, Long cnpj, Long telefone, Boolean status) {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0 || razaosocial == null || razaosocial.trim().length() == 0 || email == null || email.trim().length() == 0 || cnpj == null || cnpj <=0 || telefone == null || telefone <=0 || email == null || email.trim().length() == 0) {
            return false;
        }
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.alterar(login, senha, razaosocial, email, cnpj, telefone, status);
    }

    /**
     * Método utilizado para excluir o estabelecimento por meio do seu login
     *
     * @param login
     * @return
     */
    public boolean excluir(String login) {
        if (login == null || login.trim().length() == 0) {
            return false;
        }
        EstabelecimentoDAO dao = new EstabelecimentoDAO();
        return dao.excluir(login);
    }
}
