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
 * @author Sony Classe que representa a ação de cadastrar uma nova categoria
 */
public class IncluirCategoriaServlet extends HttpServlet {

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
        String nome = null;
        try {
            nome = request.getParameter("nome");
        } catch (Exception ex) {
            error = true;
        } finally {
            //recuperação
            request.setAttribute("nome", nome);
        }
        if (!error) {
            // processamento
            CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
            boolean sucessoInserir = categoriaNegocio.inserir(nome);
            // saída
            if (sucessoInserir) {
                request.setAttribute("mensagem", "Categoria inserida com sucesso");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/categoria/listar.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Não foi possível inserir esta categoria");
                RequestDispatcher rd = request.getRequestDispatcher("novoCategoria.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir esta categoria");
            RequestDispatcher rd = request.getRequestDispatcher("novoCategoria.jsp");
            rd.forward(request, response);
        }

    }

}
