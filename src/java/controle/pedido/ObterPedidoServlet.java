/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.pedido.Pedido;
import modelo.pedido.PedidoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de uma pedido existente para
 * alteração
 */
public class ObterPedidoServlet extends HttpServlet {

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
        Long id = Long.parseLong(request.getParameter("id"));
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        Pedido pedido = pedidoNegocio.obterPedido(id);
        request.setAttribute("pedido", pedido);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pedido/alterar.jsp");
        rd.forward(request, response);
    }

}
