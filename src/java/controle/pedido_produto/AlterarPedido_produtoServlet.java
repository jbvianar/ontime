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
import modelo.pedido_produto.Pedido_produtoNegocio;

/**
 *
 *
 *
 * Classe que representa a ação de alterar dados de um pedido_produto existente
 */
public class AlterarPedido_produtoServlet extends HttpServlet {

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
        Long pedido_id = Long.parseLong(request.getParameter("pedido_id"));
        Integer produto_id = Integer.parseInt(request.getParameter("produto_id"));
        Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String cliente_login = request.getParameter("cliente_login");
        // processamento
        Pedido_produtoNegocio pedido_produtoNegocio = new Pedido_produtoNegocio();
        boolean sucessoAlterar = pedido_produtoNegocio.alterar(pedido_id, produto_id, quantidade, cliente_login);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Pedido_produto alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar este pedido_produto");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ObterPedido_produtoServlet?pedido_id=" + pedido_id + "&" + produto_id);
        rd.forward(request, response);
    }
}
