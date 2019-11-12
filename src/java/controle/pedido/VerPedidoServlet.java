/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.pedido.Pedido;
import modelo.pedido.PedidoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um pedido
 */
public class VerPedidoServlet extends HttpServlet {

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
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        List<Pedido> resultado = pedidoNegocio.obterTodosPorLogin(login); // recupera o objeto pedido por meio do login armazenado na sessão
        if (resultado != null) { // caso exista o pedido com o id armazenado na sessão
            request.setAttribute("resultado", resultado);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verPedido.jsp");
            rd.forward(request, response);
        } else { // caso o id não exista na sessão ou não seja um id válido no sistema
            request.setAttribute("mensagem", "Você não possui pedidos");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verPedido.jsp");
            rd.forward(request, response);
        }
    }
}
