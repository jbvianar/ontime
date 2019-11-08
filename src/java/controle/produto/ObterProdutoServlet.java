/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um produto existente para
 * alteração
 */
public class ObterProdutoServlet extends HttpServlet {

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
        Integer id = Integer.parseInt(request.getParameter("id"));
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        Produto produto = produtoNegocio.obterProduto(id);
        request.setAttribute("produto", produto);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/produto/alterar.jsp");
        rd.forward(request, response);
    }

}
