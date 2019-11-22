/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.feedback;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.feedback.Feedback;
import modelo.feedback.FeedbackNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de uma feedback
 */
public class VerFeedbackServlet extends HttpServlet {

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
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        String login = (String) session.getAttribute("login"); // recupera o atributo de login armazenado na sessão do usuário; caso não exista, é retornado nulo
        FeedbackNegocio feedbackNegocio = new FeedbackNegocio();
        List<Feedback> feedback = feedbackNegocio.obterTodosPorLogin(login); // recupera o objeto feedback por meio do login armazenado na sessão
        if (feedback != null) { // caso exista a feedback com o id armazenado na sessão
            request.setAttribute("feedback", feedback);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verFeedback.jsp");
            rd.forward(request, response);
        } else { // caso o login não exista na sessão ou não seja um login válido no sistema
            request.setAttribute("mensagem", "Você não possui um login válido");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verFeedback.jsp");
            rd.forward(request, response);
        }
    }

}
