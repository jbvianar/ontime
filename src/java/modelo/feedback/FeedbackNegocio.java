/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.feedback;

import java.util.List;
/**
 *
 * @author Sony
 * Classe de negócio que encapsula as regras de negócio dos feedbacks
 */
public class FeedbackNegocio {
    /**
     * Método utilizado para inserir um novo feedback
     *
     * @param msg
     * @param cliente_login
     * @return
     */
    public boolean inserir(String msg, String cliente_login) {
        if (msg == null || msg.trim().length() == 0 || cliente_login == null || cliente_login.trim().length() == 0) {
            return false;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.inserir(msg, cliente_login);
    }
    
    /**
     * Método utilizado para retornar todos os feedbacks existentes
     * 
     * @return 
     */
    public List<Feedback> obterTodos() {
        FeedbackDAO dao = new FeedbackDAO();
        return dao.obterTodos();
    }
    
    /**
     * Método utilizado para retornar todos os feedbacks de um certo cliente pelo seu login
     * 
     * @param cliente_login
     * @return 
     */
    public List<Feedback> obterTodosPorLogin(String cliente_login) {
        if (cliente_login == null || cliente_login.trim().length() == 0) {
            return null;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.obterTodosPorLogin(cliente_login);
    }

    /**
     * Método utilizado para obter um feedback por meio do seu id
     *
     * @param id
     * @return
     */
    public Feedback obterFeedback(Long id) {
        if (id == null || id <= 0) {
            return null;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.obterFeedback(id);
    }
   

    /**
     * Método utilizado para alterar um feedback existente
     *
     * @param id
     * @param msg
     * @param cliente_login
     * @return
     */
    public boolean alterar(Long id, String msg, String cliente_login) {
        if (id == null || id <= 0 || msg == null || msg.trim().length() == 0 || cliente_login == null || cliente_login.trim().length() == 0) {
            return false;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.alterar(id, msg, cliente_login);
    }

    /**
     * Método utilizado para excluir um feedback por meio do seu id
     *
     * @param id
     * @return
     */
    public boolean excluir(Long id) {
        if (id == null || id <= 0) {
            return false;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.excluir(id);
    }
    
    /**
     * Método para excluir todos os feedbacks de um cliente
     *
     * @param cliente_login
     * @return
     */
    public boolean excluirFeedbacks(String cliente_login) {
        if (cliente_login == null || cliente_login.trim().length() == 0) {
            return false;
        }
        FeedbackDAO dao = new FeedbackDAO();
        return dao.excluirFeedbacks(cliente_login);
    }
    
    
}
