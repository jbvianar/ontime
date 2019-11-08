/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.pedido;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.pedido.PedidoNegocio;
import modelo.pedido_produto.Pedido_produtoDAO;

/**
 *
 *
 *
 * Classe que representa a ação de excluir um pedido existente
 */
public class ExcluirPedidoServlet extends HttpServlet {

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
        Long id = Long.parseLong(request.getParameter("id"));
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        Pedido_produtoDAO pedido_produtoDAO = new Pedido_produtoDAO();
        pedido_produtoDAO.excluirPedido(id);
        boolean sucessoExcluir = pedidoNegocio.excluir(id);
        if (sucessoExcluir) {
            request.setAttribute("mensagem", "Pedido excluído com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível excluir pedido");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ListarPedidoServlet");
        rd.forward(request, response);
    }

}
