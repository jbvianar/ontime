/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.feedback;

import static configuration.Configuracao.JDBC_DRIVER;
import static configuration.Configuracao.JDBC_SENHA;
import static configuration.Configuracao.JDBC_URL;
import static configuration.Configuracao.JDBC_USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sony 
 * Classe que representa os acessos aos dados de feedbacks
 * persistidos em um banco de dados relacional
 */
public class FeedbackDAO {

    //autoincremento
    public Long obterNovoId() {
        long id = -1;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("select nextval('feedback_id_seq') AS feedbackId");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("feedback");
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return id;
        }
        return id;
    }

    /**
     * Método utilizado para recuperar todos os feedbacks registrados
     *
     * @return
     */
    public List<Feedback> obterTodos() {
        List<Feedback> resultado = new ArrayList<Feedback>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT c.nome as cliente_nome, f.id, f.horario, f.msg, f.cliente_login FROM feedback as f, cliente as c WHERE c.login = f.cliente_login ORDER BY f.id DESC");
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getLong("id"));
                feedback.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                feedback.setMsg(resultSet.getString("msg"));
                feedback.setCliente_login(resultSet.getString("cliente_login"));
                feedback.setCliente_nome(resultSet.getString("cliente_nome"));
                resultado.add(feedback);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Feedback>();
        }
        return resultado;
    }

    /**
     * Método utilizado para recuperar todos os feedbacks de um certo login de
     * cliente
     *
     * @return
     */
    public List<Feedback> obterTodosPorLogin(String cliente_login) {
        List<Feedback> resultado = new ArrayList<Feedback>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareCall("SELECT c.nome as cliente_nome, f.id, f.horario, f.msg, f.cliente_login FROM feedback as f, cliente as c WHERE c.login = f.cliente_login AND cliente_login = ? ORDER BY f.id DESC");
            preparedStatement.setString(1, cliente_login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getLong("id"));
                feedback.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                feedback.setMsg(resultSet.getString("msg"));
                feedback.setCliente_login(resultSet.getString("cliente_login"));
                feedback.setCliente_nome(resultSet.getString("cliente_nome"));
                resultado.add(feedback);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Feedback>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um feedback pelo id
     *
     * @param id
     * @return
     */
    public Feedback obterFeedback(Long id) {
        Feedback feedback = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT c.nome as cliente_nome, f.id, f.horario, f.msg, f.cliente_login, FROM feedback as f, cliente as c WHERE c.login = f.cliente_login AND f.id = ? ORDER BY f.id DESC");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                feedback = new Feedback();
                feedback.setId(resultSet.getLong("id"));
                feedback.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                feedback.setMsg(resultSet.getString("msg"));
                feedback.setCliente_login(resultSet.getString("cliente_login"));
                feedback.setCliente_nome(resultSet.getString("cliente_nome"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return feedback;
    }

    /**
     * Método utilizado para inserir um novo feedback
     *
     * @param msg
     * @param cliente_login
     * @return
     */
    public boolean inserir(String msg, String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO feedback (horario, msg, cliente_login) VALUES (now(), ?, ?)");
            preparedStatement.setString(1, msg);
            preparedStatement.setString(2, cliente_login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um feedback já existente
     *
     * @param id
     * @param msg
     * @param cliente_login
     * @return
     */
    public boolean alterar(Long id, String msg, String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE feedback SET horario = now(), msg = ?, cliente_login = ? WHERE id = ?");
            preparedStatement.setString(1, msg);
            preparedStatement.setString(2, cliente_login);
            preparedStatement.setLong(3, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
    
    /**
     * Método para excluir um feedback já existente
     *
     * @param id
     * @return
     */
    public boolean excluir(Long id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM feedback WHERE id = ?");
            preparedStatement.setLong(1, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir todos os feedbacks de um cliente
     *
     * @param cliente_login
     * @return
     */
    public boolean excluirFeedbacks(String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM feedback WHERE cliente_login = ?");
            preparedStatement.setString(1, cliente_login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

}
