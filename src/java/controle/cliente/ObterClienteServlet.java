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
import modelo.cliente.Cliente;
import modelo.cliente.ClienteNegocio;

/**
 *
 *
 *
 * Classe que representa a ação de obter dados de um cliente existente para
 * alteração
 */
public class ObterClienteServlet extends HttpServlet {

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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        Cliente cliente = clienteNegocio.obterCliente(login);
        request.setAttribute("cliente", cliente);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/cliente/alterar.jsp");
        rd.forward(request, response);
    }

}
