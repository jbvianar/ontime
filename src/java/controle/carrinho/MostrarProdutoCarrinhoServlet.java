/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinho;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoItem;
import modelo.carrinho.CarrinhoNegocio;
import modelo.cookie.CookieUtils;

/**
 *
 * @author Sony
 */
public class MostrarProdutoCarrinhoServlet extends HttpServlet {

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
        Cookie c = CookieUtils.obterCookie(request); // obtém o cookie da aplicação, caso exista
        
        if (c == null) {
            // se o cookie não existir, cria-o vazio
            c = new Cookie(CookieUtils.COOKIE_KEY, null);
            c.setValue("");
            //request.setAttribute("mensagem", "Seu carrinho está vazio");
        } else {
            // caso o cookie já exista, resgata o carrinho de compras armazenado dentro do valor do cookie
            List<CarrinhoItem> carrinho = CarrinhoNegocio.obterCarrinho(c.getValue());
            request.setAttribute("carrinho", carrinho);
            
        }
        c.setMaxAge(Integer.MAX_VALUE); // atualiza a idade do cookie para o máximo do valor inteiro
        response.addCookie(c); // salva o cookie no navegador do cliente
        /*if (c == null) {
            request.setAttribute("mensagem", "Seu carrinho está vazio");
            RequestDispatcher rd = request.getRequestDispatcher("carrinho.jsp");
            rd.forward(request, response);
        } else {*/
            request.getRequestDispatcher("carrinho.jsp").forward(request, response);
        //}
    }

}
