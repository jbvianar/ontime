/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.estabelecimento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.estabelecimento.EstabelecimentoNegocio;

/**
 *
 * @author Sony
 */
public class AlterarEstabelecimentoStatusServlet extends HttpServlet {

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
        String status = request.getParameter("status");
        EstabelecimentoNegocio estabelecimentoNegocio = new EstabelecimentoNegocio();
        HttpSession session = request.getSession();
        estabelecimentoNegocio.mudarStatus(Boolean.parseBoolean(status), session.getAttribute("login").toString());
        request.getRequestDispatcher("InicioServlet").forward(request, response);
                
    }

}
