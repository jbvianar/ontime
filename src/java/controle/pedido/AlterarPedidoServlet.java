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

/**
 *
 *
 *
 * Classe que representa a ação de alterar dados de um pedido existente
 */
public class AlterarPedidoServlet extends HttpServlet {

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
        Long id = Long.parseLong(request.getParameter("id"));
        String observacoes = request.getParameter("observacoes");
        String agendamento = request.getParameter("agendamento");
        //Date horario = new java.util.Date(request.getParameter("horario").getTime());
        String senhadopedido = request.getParameter("senhadopedido");
        String status = request.getParameter("status");
        Double valortotal = Double.parseDouble(request.getParameter("valortotal"));
        String cliente_login = request.getParameter("cliente_login");
        String estabelecimento_login = request.getParameter("estabelecimento_login");
        // processamento
        PedidoNegocio pedidoNegocio = new PedidoNegocio();
        boolean sucessoAlterar = pedidoNegocio.alterar(id, observacoes, agendamento, senhadopedido, status, valortotal, cliente_login, estabelecimento_login);
        // saída
        if (sucessoAlterar) {
            request.setAttribute("mensagem", "Pedido alterado com sucesso");
        } else {
            request.setAttribute("mensagem", "Não foi possível alterar esta pedido");
        }
        RequestDispatcher rd = request.getRequestDispatcher("ObterPedidoServlet?id=" + id);
        rd.forward(request, response);
    }

}
