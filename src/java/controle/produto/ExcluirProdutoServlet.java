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
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de excluir um produto existente
 */
public class ExcluirProdutoServlet extends HttpServlet {

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
        Integer id = Integer.parseInt(request.getParameter("id"));
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        boolean sucessoExcluir = produtoNegocio.excluir(id);
        if (sucessoExcluir) {
            request.setAttribute("mensagem", "Produto excluído com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível excluir este produto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarProdutoServlet");
        rd.forward(request, response);
    }

}
