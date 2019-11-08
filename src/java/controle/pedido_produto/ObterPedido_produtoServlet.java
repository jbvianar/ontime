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
import modelo.pedido_produto.Pedido_produto;
import modelo.pedido_produto.Pedido_produtoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um pedido_produto existente para
 * alteração
 */
public class ObterPedido_produtoServlet extends HttpServlet {

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
        Long pedido_id = Long.parseLong(request.getParameter("pedido_id"));
        Integer produto_id = Integer.parseInt(request.getParameter("produto_id"));
        Pedido_produtoNegocio pedido_produtoNegocio = new Pedido_produtoNegocio();
        Pedido_produto pedido_produto = pedido_produtoNegocio.obterPedido_produto(pedido_id, produto_id);
        request.setAttribute("pedido_produto", pedido_produto);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pedido_produto/alterar.jsp");
        rd.forward(request, response);
    }
}
