/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.estabelecimento;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.estabelecimento.EstabelecimentoNegocio;

/**
 *
 * @author Sony
 * Classe que representa a ação de excluir um estabelecimento existente
 */
public class ExcluirEstabelecimentoServlet extends HttpServlet {

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
        String loginEstabelecimento = (String) session.getAttribute("login");
        EstabelecimentoNegocio estabelecimentoNegocio = new EstabelecimentoNegocio();
        boolean sucessoExcluir = estabelecimentoNegocio.excluir(login);
        if (sucessoExcluir) {
            request.setAttribute("mensagem", "Estabelecimento excluído com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível excluir este estabelecimento");
        }
        RequestDispatcher rd;
        if (loginEstabelecimento.equals(login)){//se o usuário é um estabelecimento excluindo a própria conta
            rd = request.getRequestDispatcher("LogoutEstabelecimentoServlet");
        } else {
            rd = request.getRequestDispatcher("ListarEstabelecimentoServlet");
        }
        rd.forward(request, response);
    }

}
