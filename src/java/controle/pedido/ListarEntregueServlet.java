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
import modelo.pedido.Pedido;
import modelo.pedido.PedidoNegocio;

/**
 *
 *
 *
 * Classe que representa a ação de consultar pedidos existentes
 */
public class ListarEntregueServlet extends HttpServlet {

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
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        List<Pedido> resultado = pedidoNegocio.obterTodosPorStatus("entregue");//em preparo vai para pronto
        request.setAttribute("resultado", resultado);//if do resultado, se o size for zero é porque não tem nada
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pedido/listarAberto.jsp");
        rd.forward(request, response);
    }

}
