/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.pedido.PedidoDAO;
import modelo.pedido.PedidoNegocio;
import modelo.pedido_produto.Pedido_produtoDAO;
import javax.servlet.http.Cookie;
import modelo.cookie.CookieUtils;
import modelo.carrinho.CarrinhoItem;
import modelo.carrinho.CarrinhoNegocio;

/**
 *
 * @author Sony
 */
public class ProcessarPedidoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PedidoDAO pedidoDAO = new PedidoDAO();
        Long pedidoId = pedidoDAO.obterNovoId();
        HttpSession session = request.getSession();//REQUESTPARAMETER, SEU ANIMAL
        String observacoes = null;
        if (request.getParameter("observacoes") != null && request.getParameter("observacoes").trim().length() > 0) {
            observacoes = request.getParameter("observacoes");
        };
        String agendamento = null;
        if (request.getParameter("agendamento") != null && request.getParameter("agendamento").trim().length() > 0) {
            observacoes = request.getParameter("agendamento");
        };
        //Date horario = new java.util.Date(request.getParameter("horario").getTime());
        Integer senhadopedido = 123;
        String status = "EM PREPARO";
        Double valorTotal = Double.parseDouble(request.getParameter("valorTotal"));
        String estabelecimento_login = "a";
        String cliente_login = (String) session.getAttribute("login");
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        boolean sucessoCompra = pedidoNegocio.inserir(pedidoId, observacoes, agendamento, senhadopedido, status, valorTotal, cliente_login, estabelecimento_login);
        Cookie c = CookieUtils.obterCookie(request);
        List<CarrinhoItem> itens = CarrinhoNegocio.obterCarrinho(c.getValue());
        Pedido_produtoDAO pedido_produtoDAO = new Pedido_produtoDAO();
        for (CarrinhoItem carrinhoItem : itens) {
            pedido_produtoDAO.inserir(pedidoId, carrinhoItem.getProduto().getId(), carrinhoItem.getQuantidade(), cliente_login);
        }
        if (sucessoCompra) { 
            request.setAttribute("mensagem", "Compra realizada com sucesso");
            RequestDispatcher rd = request.getRequestDispatcher("InicioServlet?carrinhoVazio=true");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Faça login como cliente para finalizar o pedido");
            RequestDispatcher rd = request.getRequestDispatcher("InicioServlet");
            rd.forward(request, response);
        }
    }

}
