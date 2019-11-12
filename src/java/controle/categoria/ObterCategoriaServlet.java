/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.categoria;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de uma categoria existente para
 * alteração
 */
public class ObterCategoriaServlet extends HttpServlet {

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
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        Categoria categoria = categoriaNegocio.obterCategoria(id);
        request.setAttribute("categoria", categoria);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/categoria/alterar.jsp");
        rd.forward(request, response);
    }

}
