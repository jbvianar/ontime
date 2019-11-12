/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido_produto;

import java.io.IOException;
import java.util.List;
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
 *
 * Classe que representa a ação de consultar pedido_produtos existentes
 */
public class ListarPedido_produtoServlet extends HttpServlet {

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
        Pedido_produtoNegocio pedido_produtoNegocio = new Pedido_produtoNegocio();
        List<Pedido_produto> resultado = pedido_produtoNegocio.obterTodos();
        request.setAttribute("resultado", resultado);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pedido_produto/listar.jsp");
        rd.forward(request, response);
    }
}
