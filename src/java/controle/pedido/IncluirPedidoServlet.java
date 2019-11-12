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
 * @author Sony Classe que representa a ação de cadastrar um novo pedido
 */
public class IncluirPedidoServlet extends HttpServlet {

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
        Long id = null;
        String observacoes = null;
        String agendamento = null;
        //Date horario = null;
        Integer senhadopedido = null;
        String status = null;
        Double valortotal = null;
        String cliente_login = null;
        String estabelecimento_login = null;
        try {
            id = Long.parseLong(request.getParameter("id"));
            if (request.getParameter("observacoes").trim().length() > 0) {
                observacoes = request.getParameter("observacoes");
            }
            if (request.getParameter("agendamento").trim().length() > 0) {
                agendamento = request.getParameter("agendamento");
            }
            //horario = new java.util.Date(request.getParameter("horario").getTime());
            senhadopedido = Integer.parseInt(request.getParameter("senhadopedido"));
            status = request.getParameter("status");
            valortotal = Double.parseDouble(request.getParameter("valortotal"));
            cliente_login = request.getParameter("cliente_login");
            estabelecimento_login = request.getParameter("estabelecimento_login");
        } catch (Exception ex) {
            error = true;
        } finally {
            //recuperação
            request.setAttribute("id", id);
            request.setAttribute("observacoes", observacoes);
            request.setAttribute("agendamento", agendamento);
            //request.setAttribute("horario", horario);
            request.setAttribute("senhadopedido", senhadopedido);
            request.setAttribute("status", status);
            request.setAttribute("valortotal", valortotal);
            request.setAttribute("cliente_login", cliente_login);
            request.setAttribute("estabelecimento_login", estabelecimento_login);
        }
        if (!error) {
            // processamento
            PedidoNegocio pedidoNegocio = new PedidoNegocio();
            boolean sucessoInserir = pedidoNegocio.inserir(id, observacoes, agendamento, senhadopedido, status, valortotal, cliente_login, estabelecimento_login);
            // saída
            if (sucessoInserir) {
                request.setAttribute("mensagem", "Pedido inserido com sucesso");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/pedido/listar.jsp");
                rd.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Não foi possível inserir este pedido");
                RequestDispatcher rd = request.getRequestDispatcher("novoPedido.jsp");
                rd.forward(request, response);
            }
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este pedido");
            RequestDispatcher rd = request.getRequestDispatcher("novoPedido.jsp");
            rd.forward(request, response);
        }

    }
}
