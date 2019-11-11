/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;

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
 * Classe que representa os acessos aos dados de pedido_produtos persistidos em um banco de dados relacional
 */
public class Pedido_produtoDAO {
    /**
     * Método utilizado para recuperar todos os pedido_produtos registrados
     *
     * @return
     */
    public List<Pedido_produto> obterTodos() {
        List<Pedido_produto> resultado = new ArrayList<Pedido_produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT pedido_id, produto_id, quantidade, cliente_login FROM pedido_produto");
            while (resultSet.next()) {
                Pedido_produto pedido_produto = new Pedido_produto();
                pedido_produto.setPedido_id(resultSet.getLong("pedido_id"));
                pedido_produto.setProduto_id(resultSet.getInt("produto_id"));
                pedido_produto.setQuantidade(resultSet.getInt("quantidade"));
                pedido_produto.setCliente_login(resultSet.getString("cliente_login"));
                resultado.add(pedido_produto);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Pedido_produto>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um pedido_produto pelos ids
     *
     * @param pedido_id
     * @param produto_id
     * @return
     */
    public Pedido_produto obterPedido_produto(Long pedido_id, Integer produto_id) {
        Pedido_produto pedido_produto = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT pedido_id, produto_id, quantidade, cliente_login FROM pedido_produto WHERE pedido_id = ?, produto_id = ?");
            preparedStatement.setLong(1, pedido_id);
            preparedStatement.setInt(2, produto_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pedido_produto = new Pedido_produto();
                pedido_produto.setPedido_id(resultSet.getLong("pedido_id"));
                pedido_produto.setProduto_id(resultSet.getInt("produto_id"));
                pedido_produto.setQuantidade(resultSet.getInt("quantidade"));
                pedido_produto.setCliente_login(resultSet.getString("cliente_login"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return pedido_produto;
    }
    
     /**
     * Método utilizado para obter um pedido_produto pelos ids
     *
     * @param pedido_id
     * @param produto_id
     * @return
     */
    public List<Pedido_produto> obterPedido_produto(Long pedido_id) {
        List<Pedido_produto> resultado = new ArrayList<Pedido_produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT p.pedido_id, p.produto_id, p.quantidade, p.cliente_login, pp.nome as produto_nome FROM pedido_produto as p, produto as pp WHERE p.pedido_id = ? AND pp.id = p.produto_id");
            preparedStatement.setLong(1, pedido_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pedido_produto pedido_produto = new Pedido_produto();
                pedido_produto.setPedido_id(resultSet.getLong("pedido_id"));
                pedido_produto.setProduto_id(resultSet.getInt("produto_id"));
                pedido_produto.setQuantidade(resultSet.getInt("quantidade"));
                pedido_produto.setCliente_login(resultSet.getString("cliente_login"));
                pedido_produto.setProduto_nome(resultSet.getString("produto_nome"));
                resultado.add(pedido_produto);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return resultado;
    }


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
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pedido_produto (pedido_id, produto_id, quantidade, cliente_login) VALUES (?, ?, ?, ?)");
            preparedStatement.setLong(1, pedido_id);
            preparedStatement.setInt(2, produto_id);
            preparedStatement.setInt(3, quantidade);
            preparedStatement.setString(4, cliente_login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um pedido_produto já existente
     *
     * @param pedido_id
     * @param produto_id
     * @param quantidade
     * @param cliente_login
     * @return
     */
    public boolean alterar(Long pedido_id, Integer produto_id, Integer quantidade, String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedido_produto SET quantidade = ?, cliente_login = ? WHERE pedido_id = ?, produto_id = ?");
            preparedStatement.setInt(1, quantidade);
            preparedStatement.setString(2, cliente_login);
            preparedStatement.setLong(3, pedido_id);
            preparedStatement.setInt(4, produto_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um pedido_produto já existente
     *
     * @param pedido_id
     * @param produto_id
     * @return
     */
    public boolean excluir(Long pedido_id, Integer produto_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido_produto WHERE pedido_id = ?, produto_id = ?");
            preparedStatement.setLong(1, pedido_id);
            preparedStatement.setInt(2, produto_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
    
    /**
     * Método para excluir todos os pedidos de produtos de um pedido pelo seu id
     *
     * @param pedido_id
     * @return
     */
    public boolean excluirPedido(Long pedido_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido_produto WHERE pedido_id = ?");
            preparedStatement.setLong(1, pedido_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
    
    /**
     * Método para excluir todos os pedidos de produtos de um pedido pelo login do cliente
     *
     * @param cliente_login
     * @return
     */
    public boolean excluirPorCliente(String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido_produto WHERE cliente_login = ?");
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
