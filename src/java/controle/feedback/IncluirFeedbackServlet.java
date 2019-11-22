/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.feedback;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.feedback.FeedbackNegocio;

/**
 *
 *
 * @author Sony Classe que representa a ação de cadastrar uma nova categoria
 */
public class IncluirFeedbackServlet extends HttpServlet {

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
        String msg = null;
        String cliente_login = null;
        try {
            msg = request.getParameter("msg");
            cliente_login = request.getParameter("cliente_login");
        } catch (Exception ex) {
            error = true;
        } finally {
            //recuperação
            request.setAttribute("msg", msg);
            request.setAttribute("cliente_login", cliente_login);
        }
        if (!error) {
            // processamento
            FeedbackNegocio feedbackNegocio = new FeedbackNegocio();
            boolean sucessoInserir = feedbackNegocio.inserir(msg, cliente_login);
            // saída
            if (sucessoInserir) {
                request.setAttribute("mensagem", "Mensagem enviada com sucesso");
                RequestDispatcher rd = request.getRequestDispatcher("ListarCategoriaServlet");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Não foi possível enviar mensagem");
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
