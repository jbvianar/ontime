/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import java.util.List;
import java.util.Random;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PedidoDAO pedidoDAO = new PedidoDAO();
        Long pedidoId = pedidoDAO.obterNovoId();
        HttpSession session = request.getSession();//REQUESTPARAMETER, SEU ANIMAL
        String observacoes = null;
        if (request.getParameter("observacoes") != null && request.getParameter("observacoes").trim().length() > 0) {
            observacoes = request.getParameter("observacoes");
        };
        String agendamento = null;
        if (request.getParameter("agendamento") != null && request.getParameter("agendamento").trim().length() > 0) {
            agendamento = request.getParameter("agendamento");
        };
        //Date horario = new java.util.Date(request.getParameter("horario").getTime());
        final int TAMANHO_DA_SENHA = 4;
        Random r = new Random();
        String s = "";
        for (int i = 1; i <= TAMANHO_DA_SENHA; i++) {
            int index = r.nextInt(62);
            if (index < 10) {
                s += (char) (index + 48); //os chars 48 a 57 representam [0 - 9]
            } else if (index < 36) {
                s += (char) (index + 55); //os chars 65 a 90 representam [A - Z]
            } else {
                s += (char) (index + 61); //os chars 97 a 122 representam [a - z]
            }
        }
        System.out.println("Senha final: " + s);
        String senhadopedido = s;
        String status = "em preparo";
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
            request.setAttribute("mensagem", "Compra realizada com sucesso. Seu pedido está em preparo. Sua senha é: " + senhadopedido);
            RequestDispatcher rd = request.getRequestDispatcher("InicioServlet?carrinhoVazio=true");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Faça login como cliente para finalizar o pedido");
            RequestDispatcher rd = request.getRequestDispatcher("InicioServlet");
            rd.forward(request, response);
        }
    }

}
