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
 * @author Sony
 * Classe que representa a ação de alterar dados de um estabelecimento existente
 */
public class AlterarEstabelecimentoServlet extends HttpServlet {

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
        String razaosocial = request.getParameter("razaosocial");
        String email = request.getParameter("email");
        Long cnpj = Long.parseLong(request.getParameter("cnpj"));
        Long telefone = Long.parseLong(request.getParameter("telefone"));
        String status = request.getParameter("status");
        // processamento
        EstabelecimentoNegocio estabelecimentoNegocio = new EstabelecimentoNegocio();
        boolean sucessoAlterar = estabelecimentoNegocio.alterar(login, senha, razaosocial, email, cnpj, telefone, status);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Estabelecimento alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar este estabelecimento");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ObterEstabelecimentoServlet?login=" + login);
        rd.forward(request, response);
    }

}
