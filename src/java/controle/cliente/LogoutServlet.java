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

/**
 *
 *
 * 
 * Classe que representa a ação de encerrar a sessão de um usuário
 */
public class LogoutServlet extends HttpServlet {

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
        session.invalidate(); // invalida a sessão do usuário
        request.setAttribute("mensagem1", "Sua sessão foi encerrada"); // coloca uma mensagem no objeto request
        RequestDispatcher rd = request.getRequestDispatcher("InicioServlet"); // despacha a requisição para a página index.jsp, encaminhando as instâncias de request e response
        rd.forward(request, response);
    }

}
