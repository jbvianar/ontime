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
import java.sql.SQLException;
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
            ResultSet resultSet = statement.executeQuery("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login ORDER BY p.id DESC");
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getString("senhadopedido"));
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
     * Método utilizado para recuperar todos os pedidos registrados
     *
     * @return
     */
    public List<Pedido> obterTodosDoDia() {
        List<Pedido> resultado = new ArrayList<Pedido>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login AND p.horario::date = current_date ORDER BY p.id DESC");
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getString("senhadopedido"));
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
            PreparedStatement preparedStatement = connection.prepareCall("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login AND cliente_login = ? ORDER BY p.id DESC");
            preparedStatement.setString(1, cliente_login);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getString("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setCliente_nome(resultSet.getString("cliente_nome"));
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
            PreparedStatement preparedStatement = connection.prepareCall("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login AND p.id = ? ORDER BY p.id DESC");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getString("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setCliente_nome(resultSet.getString("cliente_nome"));
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
     * Método utilizado para obter um pedido pelo status
     *
     * @param status
     * @return
     */
    public List<Pedido> obterTodosPorStatus(String status) {
        List<Pedido> resultado = new ArrayList<Pedido>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            Statement statement = connection.createStatement();
            PreparedStatement preparedStatement = connection.prepareCall("SELECT c.nome as cliente_nome, p.id, p.observacoes, p.agendamento, p.horario, p.senhadopedido, p.status, p.valortotal, p.cliente_login, p.estabelecimento_login FROM pedido as p, cliente as c WHERE c.login = p.cliente_login AND p.status = ? ORDER BY p.id DESC");
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            Pedido_produtoDAO pdao = new Pedido_produtoDAO();//////////////////
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getLong("id"));
                pedido.setObservacoes(resultSet.getString("observacoes"));
                pedido.setAgendamento(resultSet.getString("agendamento"));
                pedido.setHorario(new java.util.Date(resultSet.getTimestamp("horario").getTime()));
                pedido.setSenhadopedido(resultSet.getString("senhadopedido"));
                pedido.setStatus(resultSet.getString("status"));
                pedido.setValortotal(resultSet.getDouble("valortotal"));
                pedido.setCliente_login(resultSet.getString("cliente_login"));
                pedido.setEstabelecimento_login(resultSet.getString("estabelecimento_login"));
                pedido.setCliente_nome(resultSet.getString("cliente_nome"));
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
    public boolean inserir(Long id, String observacoes, String agendamento, String senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
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
            preparedStatement.setString(4, senhadopedido);
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
    public boolean alterar(Long id, String observacoes, String agendamento, String senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) {
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
            preparedStatement.setString(3, senhadopedido);
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
     * Método para mudar só o status de um pedido feito
     *
     * @param id
     * @param status
     * @return
     */
    
    public boolean mudarStatus(Long id, String status) {
        boolean resultado = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedido SET status = ? WHERE id = ?");
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
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

    public boolean finalizarPedido(List<CarrinhoItem> carrinho, String observacoes, String agendamento, String senhadopedido, String status, Double valortotal, String cliente_login, String estabelecimento_login) throws SQLException, Exception {
        boolean resultado = false;
        Connection connection = null;
        List<String> erros = new ArrayList<String>();
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USUARIO, JDBC_SENHA);
            connection.setAutoCommit(false);

            // etapa 1 - implementação do autoincremento
            long id = -1;
            PreparedStatement preparedStatement = connection.prepareStatement("select nextval('pedido_id_seq') AS pedidoId");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getLong("pedidoId");
            }
            resultSet.close();
            preparedStatement.close();

            // etapa 2 - criação do pedido
            preparedStatement = connection.prepareStatement("INSERT INTO pedido (id, observacoes, agendamento, horario, senhadopedido, status, valortotal, cliente_login, estabelecimento_login) VALUES (?, ?, ?, now(), ?, ?, ?, ?, ?)");
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
            preparedStatement.setString(4, senhadopedido);
            preparedStatement.setString(5, status);
            preparedStatement.setDouble(6, valortotal);
            preparedStatement.setString(7, cliente_login);
            preparedStatement.setString(8, estabelecimento_login);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // etapa 3 - obter a quantidade de cada produto do carrinho
            for (CarrinhoItem ci : carrinho) {
                preparedStatement = connection.prepareStatement("SELECT quantidade FROM produto WHERE id = ?");
                preparedStatement.setInt(1, ci.getProduto().getId());
                resultSet = preparedStatement.executeQuery();
                int quantidadeEmEstoque = 0;
                while (resultSet.next()) {
                    quantidadeEmEstoque = resultSet.getInt("quantidade");
                }
                resultSet.close();
                preparedStatement.close();

                if (quantidadeEmEstoque >= ci.getQuantidade()) {//verifica se a quantidade em estoque é maior ou igual à quantidade do item no carrinho
                    // etapa 3.1 - se o pedido for aprovado, inserir os campos do carrinho no pedido
                    preparedStatement = connection.prepareStatement("INSERT INTO pedido_produto (pedido_id, produto_id, quantidade, cliente_login) VALUES (?, ?, ?, ?)");
                    preparedStatement.setLong(1, id);
                    preparedStatement.setInt(2, ci.getProduto().getId());
                    preparedStatement.setInt(3, ci.getQuantidade());
                    preparedStatement.setString(4, cliente_login);
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                    // etapa 3.2 - diminuir do estoque a quantidade dos itens comprados
                    preparedStatement = connection.prepareStatement("UPDATE produto SET quantidade = quantidade - ? WHERE id = ?");
                    preparedStatement.setInt(1, ci.getQuantidade());
                    preparedStatement.setInt(2, ci.getProduto().getId());
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                } else {//mostra mensagem de erro com os produtos cujo estoque é inferior à quantidade do pedido
                    erros.add("O produto " + ci.getProduto().getNome() + " não possui quantidade disponível");
                }
            }
            if (erros.size() != 0) {
                throw new Exception();
            }
            connection.commit();
            resultado = true;
        } catch (Exception ex) {
            if (connection != null) {//se o pedido der errado, desfazer a criação do pedido
                connection.rollback();
            }
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        if (!erros.isEmpty()) {
            String mensagem = "";
            for (String e : erros) {
                mensagem += e + "<br/>";
            }
            throw new Exception(mensagem);
        }
        return resultado;
    }
}
