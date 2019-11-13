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
import modelo.estabelecimento.EstabelecimentoNegocio;

/**
 *
 *
 *
 * Classe que representa a ação de cadastrar um novo estabelecimento
 */
public class IncluirEstabelecimentoServlet extends HttpServlet {

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
        // entrada
        boolean error = false;
        String login = null;
        String senha = null;
        String razaosocial = null;
        String email = null;
        Long cnpj = null;
        Long telefone = null;
        Boolean status = null;
        try {
            login = request.getParameter("login");
            senha = request.getParameter("senha");
            razaosocial = request.getParameter("razaosocial");
            email = request.getParameter("email");
            cnpj = Long.parseLong(request.getParameter("cnpj"));
            telefone = Long.parseLong(request.getParameter("telefone"));
           status = Boolean.parseBoolean(request.getParameter("status"));
        } catch (Exception ex) {
            error = true;
        } finally {
            //recuperação
            request.setAttribute("login", login);
            request.setAttribute("senha", senha);
            request.setAttribute("razaosocial", razaosocial);
            request.setAttribute("email", email);
            request.setAttribute("cnpj", cnpj);
            request.setAttribute("telefone", telefone);
            request.setAttribute("status", status);
        }
        if (!error) {
            // processamento
            EstabelecimentoNegocio estabelecimentoNegocio = new EstabelecimentoNegocio();
            boolean sucessoInserir = estabelecimentoNegocio.inserir(login, senha, razaosocial, email, cnpj, telefone, status);
            // saída
            if (sucessoInserir) {
                request.setAttribute("mensagem2", "Estabelecimento inserido com sucesso");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/principal.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Não foi possível inserir este estabelecimento");
                RequestDispatcher rd = request.getRequestDispatcher("novoEstabelecimento.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este estabelecimento");
            RequestDispatcher rd = request.getRequestDispatcher("novoEstabelecimento.jsp");
            rd.forward(request, response);
        }
    }

}
