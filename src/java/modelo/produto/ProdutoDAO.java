/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;

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
 * Classe que representa os acessos aos dados de produtos persistidos em um banco de dados relacional
 */
public class ProdutoDAO {
    /**
     * Método utilizado para recuperar todos os produtos registrados
     *
     * @return
     */
    public List<Produto> obterTodos() {
        List<Produto> resultado = new ArrayList<Produto>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id FROM produto");
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setImagem(resultSet.getString("imagem"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setDisponibilidade(resultSet.getBoolean("disponibilidade"));
                produto.setCategoria_id(resultSet.getInt("categoria_id"));
                resultado.add(produto);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Produto>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um produto pelo id
     *
     * @param id
     * @return
     */
    public Produto obterProduto(Integer id) {
        Produto produto = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setImagem(resultSet.getString("imagem"));
                produto.setQuantidade(resultSet.getInt("quantidade"));
                produto.setDisponibilidade(resultSet.getBoolean("disponibilidade"));
                produto.setCategoria_id(resultSet.getInt("categoria_id"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return produto;
    }

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
    public boolean inserir(Integer id, String nome, String descricao, Double preco, String imagem, Integer quantidade, Boolean disponibilidade, Integer categoria_id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (id, nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, descricao);
            preparedStatement.setDouble(4, preco);
            preparedStatement.setString(5, imagem);
            preparedStatement.setInt(6, quantidade);
            preparedStatement.setBoolean(7, disponibilidade);
            preparedStatement.setInt(8, categoria_id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método utilizado para alterar um produto já existente
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
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET nome = ?, descricao = ?, preco = ?, imagem = ?, quantidade = ?, disponibilidade = ?, categoria_id = ? WHERE id = ?");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.setDouble(3, preco);
            preparedStatement.setString(4, imagem);
            preparedStatement.setInt(5, quantidade);
            preparedStatement.setBoolean(6, disponibilidade);
            preparedStatement.setInt(7, categoria_id);
            preparedStatement.setInt(8, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um produto já existente
     *
     * @param id
     * @return
     */
    public boolean excluir(Integer id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }
}
