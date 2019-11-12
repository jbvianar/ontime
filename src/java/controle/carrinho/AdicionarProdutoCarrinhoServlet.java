/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinho;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoNegocio;
import modelo.cookie.CookieUtils;

/**
 *
 * 
 * 
 * Servlet de controle para a ação de adicionar um item de produto no carrinho de compra
 */
public class AdicionarProdutoCarrinhoServlet extends HttpServlet {

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
        int produtoId = Integer.parseInt(request.getParameter("produtoId"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        
        Cookie c = CookieUtils.obterCookie(request);
        String novoValor = CarrinhoNegocio.adicionarItem(produtoId, quantidade, c.getValue());
        c.setValue(novoValor);
        
        request.getRequestDispatcher("InicioServlet").forward(request, response);
    }

}
