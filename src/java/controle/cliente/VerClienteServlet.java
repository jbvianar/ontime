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
import modelo.cliente.Cliente;
import modelo.cliente.ClienteNegocio;

/**
 *
 *
 * 
 * Classe que representa a ação de obter dados de um cliente
 */
public class VerClienteServlet extends HttpServlet {

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
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        String login = (String) session.getAttribute("login"); // recupera o atributo de login armazenado na sessão do usuário; caso não exista, é retornado nulo
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        Cliente cliente = clienteNegocio.obterCliente(login); // recupera o objeto cliente por meio do login armazenado na sessão
        if (cliente != null) { // caso exista o cliente com o login armazenado na sessão
            request.setAttribute("cliente", cliente);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verCliente.jsp");
            rd.forward(request, response);
        } else { // caso o login não exista na sessão ou não seja um login válido no sistema
            request.setAttribute("mensagem1", "Você não possui um login válido");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

}
