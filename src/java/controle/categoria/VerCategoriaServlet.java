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
import javax.servlet.http.HttpSession;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de uma categoria
 */
public class VerCategoriaServlet extends HttpServlet {

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
        Integer id = (Integer) session.getAttribute("id"); // recupera o atributo de id armazenado na sessão do usuário; caso não exista, é retornado nulo
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        Categoria categoria = categoriaNegocio.obterCategoria(id); // recupera o objeto categoria por meio do id armazenado na sessão
        if (categoria != null) { // caso exista a categoria com o id armazenado na sessão
            request.setAttribute("categoria", categoria);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verCategoria.jsp");
            rd.forward(request, response);
        } else { // caso o id não exista na sessão ou não seja um id válido no sistema
            request.setAttribute("mensagem", "Você não possui um id válido");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

}
