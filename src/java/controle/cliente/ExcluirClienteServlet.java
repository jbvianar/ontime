/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cliente;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.cliente.ClienteNegocio;
import modelo.pedido.PedidoDAO;
import modelo.pedido_produto.Pedido_produtoDAO;

/**
 *
 *
 *
 * Classe que representa a ação de excluir um cliente existente
 */
public class ExcluirClienteServlet extends HttpServlet {

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
        String login = request.getParameter("login");
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        String tipo = (String) session.getAttribute("tipo");
        String cliente_login = (String) session.getAttribute("login");
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido_produtoDAO pedido_produtoDAO = new Pedido_produtoDAO();
        pedido_produtoDAO.excluirPorCliente(cliente_login);//exclui todos os pedido_produtos do cliente
        pedidoDAO.excluirPedidos(cliente_login);//exclui todos os pedidos do cliente
        boolean sucessoExcluir = clienteNegocio.excluir(login);//por fim, exclui o cadastro do cliente
        if (sucessoExcluir) {
            request.setAttribute("mensagem", "Cliente excluído com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível excluir cliente");
        }
        RequestDispatcher rd;
        if (tipo.equals("cliente")){//se o usuário é um cliente excluindo a própria conta
            if (sucessoExcluir){
                rd = request.getRequestDispatcher("LogoutServlet");
            } else {
                rd = request.getRequestDispatcher("VerClienteServlet");
            }
        } else {
            rd = request.getRequestDispatcher("ListarClienteServlet");
        }
        rd.forward(request, response);
    }

}
