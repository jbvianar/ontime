/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.estabelecimento;

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
 * Classe que representa os acessos aos dados de estabelecimentos persistidos em um banco de dados relacional
 */
public class EstabelecimentoDAO {

    /**
     * Método utilizado para recuperar todos os estabelecimentos registrados
     *
     * @return
     */
    public List<Estabelecimento> obterTodos() {
        List<Estabelecimento> resultado = new ArrayList<Estabelecimento>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT login, senha, razaosocial, email, cnpj, telefone, status FROM estabelecimento");
            while (resultSet.next()) {
                Estabelecimento estabelecimento = new Estabelecimento();
                estabelecimento.setLogin(resultSet.getString("login"));
                estabelecimento.setSenha(resultSet.getString("senha"));
                estabelecimento.setRazaosocial(resultSet.getString("razaosocial"));                
                estabelecimento.setEmail(resultSet.getString("email"));
                estabelecimento.setCnpj(resultSet.getLong("cnpj"));
                estabelecimento.setTelefone(resultSet.getLong("telefone"));
                estabelecimento.setStatus(resultSet.getBoolean("status"));
                resultado.add(estabelecimento);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Estabelecimento>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um estabelecimento pelo login
     *
     * @param login
     * @return
     */
    public Estabelecimento obterEstabelecimento(String login) {
        Estabelecimento estabelecimento = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT login, senha, razaosocial, email, cnpj, telefone, status FROM estabelecimento WHERE login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                estabelecimento = new Estabelecimento();
                estabelecimento.setLogin(resultSet.getString("login"));
                estabelecimento.setSenha(resultSet.getString("senha"));
                estabelecimento.setRazaosocial(resultSet.getString("razaosocial"));                
                estabelecimento.setEmail(resultSet.getString("email"));
                estabelecimento.setCnpj(resultSet.getLong("cnpj"));
                estabelecimento.setTelefone(resultSet.getLong("telefone"));
                estabelecimento.setStatus(resultSet.getBoolean("status"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return estabelecimento;
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
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO estabelecimento (login, senha, razaosocial, email, cnpj, telefone, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            //\"razaoSocial\"
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, senha);
            preparedStatement.setString(3, razaosocial);
            preparedStatement.setString(4, email);
            preparedStatement.setLong(5, cnpj);
            preparedStatement.setLong(6, telefone);
            preparedStatement.setBoolean(7, status);
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
     * Método utilizado para alterar um estabelecimento já existente
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
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE estabelecimento SET senha = ?, razaosocial = ?, email = ?, cnpj = ?, telefone = ?, status = ? WHERE login = ?");
            preparedStatement.setString(1, senha);
            preparedStatement.setString(2, razaosocial);
            preparedStatement.setString(3, email);
            preparedStatement.setLong(4, cnpj);
            preparedStatement.setLong(5, telefone);
            preparedStatement.setBoolean(6, status);
            preparedStatement.setString(7, login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
    
     /**
     * Método para mudar só o status do estabelecimento
     *
     * @param login
     * @param status
     * @return
     */
    public boolean mudarStatus(String login, Boolean status) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE estabelecimento SET status = ? WHERE login = ?");
            preparedStatement.setBoolean(1, status);
            preparedStatement.setString(2, login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um estabelecimento já existente
     *
     * @param login
     * @return
     */
    public boolean excluir(String login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM estabelecimento WHERE login = ?");
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
