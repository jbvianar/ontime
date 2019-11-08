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
import modelo.categoria.CategoriaNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de alterar dados de uma categoria existente
 */
public class AlterarCategoriaServlet extends HttpServlet {

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
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        // processamento
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        boolean sucessoAlterar = categoriaNegocio.alterar(id, nome);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Categoria alterada com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar esta categoria");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ObterCategoriaServlet?id=" + id);
        rd.forward(request, response);
    }

}
