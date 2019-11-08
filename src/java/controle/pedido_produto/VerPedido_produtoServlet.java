/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido_produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.pedido_produto.Pedido_produto;
import modelo.pedido_produto.Pedido_produtoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um pedido_produto
 */
public class VerPedido_produtoServlet extends HttpServlet {

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
        Long pedido_id = (Long) session.getAttribute("pedido_id"); // recupera o atributo de pedido_id armazenado na sessão do usuário; caso não exista, é retornado nulo
        Integer produto_id = (Integer) session.getAttribute("produto_id"); // recupera o atributo de produto_id armazenado na sessão do usuário; caso não exista, é retornado nulo
        Pedido_produtoNegocio pedido_produtoNegocio = new Pedido_produtoNegocio();
        Pedido_produto pedido_produto = pedido_produtoNegocio.obterPedido_produto(pedido_id, produto_id); // recupera o objeto pedido_produto por meio do pedido_id e do produto_id armazenados na sessão
        if (pedido_produto != null) { // caso exista o pedido_produto com o id armazenado na sessão
            request.setAttribute("pedido_produto", pedido_produto);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verPedido_produto.jsp");
            rd.forward(request, response);
        } else { // caso o pedido_id ou o produto_id não exista na sessão ou não seja um pedido_id ou produto_id válido no sistema
            request.setAttribute("mensagem", "Você não possui um pedido_id ou produto_id válido");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }
}
