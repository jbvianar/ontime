/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.pedido.PedidoNegocio;

/**
 *
 * @author Sony
 */
public class AlterarPedidoStatusServlet extends HttpServlet {

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
        Long id = Long.parseLong(request.getParameter("id"));
        String status = request.getParameter("status");
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        //HttpSession session = request.getSession();
        pedidoNegocio.mudarStatus(id, status);
        request.getRequestDispatcher("ListarProntoServlet").forward(request, response);
                
    }

}
