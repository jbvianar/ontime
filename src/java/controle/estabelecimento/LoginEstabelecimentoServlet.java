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
 * Classe que representa a ação de validar um login de usuário estabelecimento
 */
public class LoginEstabelecimentoServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        // entrada
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        // processamento
        EstabelecimentoNegocio usuarioNegocio = new EstabelecimentoNegocio(); // utiliza a classe de negócio para verificar se o login e senha estão corretos
        boolean sucessoLogin = usuarioNegocio.efetuarLogin(login, senha);
        if (sucessoLogin) { // caso o login e senha estejam corretos
            HttpSession session = request.getSession(true); // cria e referencia a sessão do usuário
            session.setAttribute("login", login); // coloca o atributo login na sessão do usuário
            session.setAttribute("tipo", "estabelecimento"); //cria o atributo do tipo "estabelecimento" na sessão do usuário
            RequestDispatcher rd = request.getRequestDispatcher("ListarAbertoServlet"); // despacha a requisição para a página principal.jsp, encaminhando as instâncias de request e response 
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem2", "Login ou senha de estabelecimento incorreta"); // coloca uma mensagem no objeto request
            RequestDispatcher rd = request.getRequestDispatcher("loginEstabelecimento.jsp"); // despacha a requisição para a página index.jsp, encaminhando as instâncias de request e response
            rd.forward(request, response);
        }
    }

}
