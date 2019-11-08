/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cliente;

import java.io.IOException;
import java.util.List;
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
 * Classe que representa a ação de consultar clientes existentes
 */
public class ListarClienteServlet extends HttpServlet {

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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        List<Cliente> resultado = clienteNegocio.obterTodos();
        request.setAttribute("resultado", resultado);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/cliente/listar.jsp");
        rd.forward(request, response);
    }

}
