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
import modelo.cliente.ClienteNegocio;

/**
 *
 *
 * 
 * Classe que representa a ação de cadastrar um novo cliente
 */
public class NovoClienteServlet extends HttpServlet {

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
        // entrada
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        Long telefone = Long.parseLong(request.getParameter("telefone"));
        // processamento
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        boolean sucessoInserir = clienteNegocio.inserir(login, senha, nome, email, telefone);
        // saída
        if (sucessoInserir) {
            request.setAttribute("mensagem1", "Cliente inserido com sucesso");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/principal.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este cliente");
            RequestDispatcher rd = request.getRequestDispatcher("novoCliente.jsp");
            rd.forward(request, response);
        }
    }

}
