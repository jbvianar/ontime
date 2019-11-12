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
import modelo.estabelecimento.Estabelecimento;
import modelo.estabelecimento.EstabelecimentoNegocio;

/**
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um estabelecimento
 */
public class VerEstabelecimentoServlet extends HttpServlet {

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
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        String login = (String) session.getAttribute("login"); // recupera o atributo de login armazenado na sessão do usuário; caso não exista, é retornado nulo
        EstabelecimentoNegocio estabelecimentoNegocio = new EstabelecimentoNegocio();
        Estabelecimento estabelecimento = estabelecimentoNegocio.obterEstabelecimento(login); // recupera o objeto estabelecimento por meio do login armazenado na sessão
        if (estabelecimento != null) { // caso exista o estabelecimento com o login armazenado na sessão
            request.setAttribute("estabelecimento", estabelecimento);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verEstabelecimento.jsp");
            rd.forward(request, response);
        } else { // caso o login não exista na sessão ou não seja um login válido no sistema
            request.setAttribute("mensagem2", "Você não possui um login válido");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

}
