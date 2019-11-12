/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

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
import modelo.carrinho.CarrinhoItem;
import modelo.pedido_produto.Pedido_produtoDAO;
import modelo.produto.Produto;
import modelo.produto.ProdutoDAO;

/**
 *
 * @author Sony Classe que representa os acessos aos dados de pedidos
 * persistidos em um banco de dados relacional
 */
public class PedidoDAO {

    //autoincremento
    public Long obterNovoId() {
        long id = -1;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("select nextval('pedido_id_seq') AS pedidoId");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("pedidoId");
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
     * Método utilizado para recuperar todos os pedidos registrados
     *
     * @return
     */
    public List<Pedido> obterTodos() {
        List<Pedido> resultado = new ArrayList<Pedido>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login");
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getInt("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setCliente_nome(resultSet.getString("cliente_nome"));
                pedido.setProdutos(pdao.obterPedido_produto(pedido.getId()));
                resultado.add(pedido);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Pedido>();
        }
        return resultado;
    }

    /**
     * Método utilizado para recuperar todos os pedidos de um certo login de
     * cliente
     *
     * @return
     */
    public List<Pedido> obterTodosPorLogin(String cliente_login) {
        List<Pedido> resultado = new ArrayList<Pedido>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, observacoes, agendamento, horario, senhadopedido, status, valortotal, cliente_login, estabelecimento_login FROM pedido WHERE cliente_login = ?");
            preparedStatement.setString(1, cliente_login);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getInt("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setProdutos(pdao.obterPedido_produto(pedido.getId()));/////////////////////
                resultado.add(pedido);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            return new ArrayList<Pedido>();
        }
        return resultado;
    }

    /**
     * Método utilizado para obter um pedido pelo id
     *
     * @param id
     * @return
     */
    public Pedido obterPedido(Long id) {
        Pedido pedido = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, observacoes, agendamento, horario, senhadopedido, status, valortotal, cliente_login, estabelecimento_login FROM pedido WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getInt("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setProdutos(pdao.obterPedido_produto(pedido.getId()));/////////////////////
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return pedido;
    }

    /**
     * Método utilizado para obter um pedido pelo login do cliente
     *
     * @param id
     * @return
     */
    public Pedido obterPedidoPorLogin(String login) {
        Pedido pedido = null;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareCall("SELECT id, observacoes, agendamento, horario, senhadopedido, status, valortotal, cliente_login, estabelecimento_login FROM pedido WHERE id = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getInt("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setProdutos(pdao.obterPedido_produto(pedido.getId()));/////////////////////
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return null;
        }
        return pedido;
    }

    /**
     * Método utilizado para inserir um novo pedido
     *
     * @param id
     * @param observacoes
     * @param agendamento
     * @param senhadopedido
     * @param status
     * @param valortotal
     * @param cliente_login
     * @param estabelecimento_login
     * @return
     */
    public boolean inserir(Long id, String observacoes, String agendamento, Integer senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pedido (id, observacoes, agendamento, horario, senhadopedido, status, valortotal, cliente_login, estabelecimento_login) VALUES (?, ?, ?, now(), ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, id);
            if (observacoes != null) {
                preparedStatement.setString(2, observacoes);
            } else {
                preparedStatement.setNull(2, java.sql.Types.VARCHAR);
            }
            if (agendamento != null) {
                preparedStatement.setString(3, agendamento);
            } else {
                preparedStatement.setNull(3, java.sql.Types.VARCHAR);
            }
            preparedStatement.setInt(4, senhadopedido);
            preparedStatement.setString(5, status);
            preparedStatement.setDouble(6, valortotal);
            preparedStatement.setString(7, cliente_login);
            preparedStatement.setString(8, estabelecimento_login);
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
     * Método utilizado para alterar um pedido já existente
     *
     * @param id
     * @param observacoes
     * @param agendamento
     * @param senhadopedido
     * @param status
     * @param valortotal
     * @param cliente_login
     * @param estabelecimento_login
     * @return
     */
    public boolean alterar(Long id, String observacoes, String agendamento, Integer senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedido SET observacoes = ?, agendamento = ?, horario = now(), senhadopedido = ?, status = ?, valortotal = ?, cliente_login = ?, estabelecimento_login = ? WHERE id = ?");
            if (observacoes != null) {
                preparedStatement.setString(1, observacoes);
            } else {
                preparedStatement.setNull(1, java.sql.Types.VARCHAR);
            }
            if (agendamento != null) {
                preparedStatement.setString(2, agendamento);
            } else {
                preparedStatement.setNull(2, java.sql.Types.VARCHAR);
            }
            preparedStatement.setInt(3, senhadopedido);
            preparedStatement.setString(4, status);
            preparedStatement.setDouble(5, valortotal);
            preparedStatement.setString(6, cliente_login);
            preparedStatement.setString(7, estabelecimento_login);
            preparedStatement.setLong(8, id);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    /**
     * Método para excluir um pedido já existente
     *
     * @param id
     * @return
     */
    public boolean excluir(Long id) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido WHERE id = ?");
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
     * Método para excluir todos os pedidos de um cliente
     *
     * @param cliente_login
     * @return
     */
    public boolean excluirPedidos(String cliente_login) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido WHERE cliente_login = ?");
            preparedStatement.setString(1, cliente_login);
            resultado = (preparedStatement.executeUpdate() > 0);
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            return false;
        }
        return resultado;
    }

    public boolean verificarEstoque(List<CarrinhoItem> itens) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (int i = 0; i < itens.size(); i++) {
            CarrinhoItem c = itens.get(i);
            Produto p = produtoDAO.obterProduto(c.getProduto().getId());
            if (c.getQuantidade() > p.getQuantidade()) {
                return false;
            }
        }
        return true;
    }
}
