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
 * Classe que representa a ação de alterar dados de um cliente existente
 */
public class AlterarClienteServlet extends HttpServlet {

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
        boolean sucessoAlterar = clienteNegocio.alterar(login, senha, nome, email, telefone);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Cliente alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar este cliente");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ObterClienteServlet?login=" + login);
        rd.forward(request, response);
    }

}
