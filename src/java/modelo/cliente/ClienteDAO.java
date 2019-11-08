/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

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
 *
 *
 * Classe que representa os acessos aos dados de clientes persistidos em um
 * banco de dados relacional
 */
public class ClienteDAO {

    /**
     * Método utilizado para recuperar todos os clientes registrados
     *
     * @return
     */
    public List<Cliente> obterTodos() {
        List<Cliente> resultado = new ArrayList<Cliente>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, senha, nome, email, telefone FROM cliente");
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setLogin(resultSet.getString("login"));
                cliente.setSenha(resultSet.getString("senha"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getLong("telefone"));
                resultado.add(cliente);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Cliente>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um cliente pelo login
     *
     * @param login
     * @return
     */
    public Cliente obterCliente(String login) {
        Cliente cliente = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT login, senha, nome, email, telefone FROM cliente WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cliente = new Cliente();
                cliente.setLogin(resultSet.getString("login"));
                cliente.setSenha(resultSet.getString("senha"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefone(resultSet.getLong("telefone"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return cliente;
    }

    /**
     * Método utilizado para inserir um novo cliente
     *
     * @param login
     * @param senha
     * @param nome
     * @param email
     * @param telefone
     * @return
     */
    public boolean inserir(String login, String senha, String nome, String email, Long telefone) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (login, senha, nome, email, telefone) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, nome);
            preparedStatement.setString(4, email);
            if (telefone != null) {
                preparedStatement.setLong(5, telefone);
            } else {
                preparedStatement.setNull(5, java.sql.Types.BIGINT);
            }
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um cliente já existente
     *
     * @param login
     * @param senha
     * @param nome
     * @param email
     * @param telefone
     * @return
     */
    public boolean alterar(String login, String senha, String nome, String email, Long telefone) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cliente SET senha = ?, nome = ?, email = ?, telefone = ? WHERE login = ?");
            preparedStatement.setString(1, senha);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, telefone);
            preparedStatement.setString(5, login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um cliente já existente
     *
     * @param login
     * @return
     */
    public boolean excluir(String login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cliente WHERE login = ?");
            preparedStatement.setString(1, login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

}
