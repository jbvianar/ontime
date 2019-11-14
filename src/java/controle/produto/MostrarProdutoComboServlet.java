/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoItem;
import modelo.carrinho.CarrinhoNegocio;
import modelo.cookie.CookieUtils;
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 *
 * Servlet que faz o controle da inicialização da página principal da aplicação
 */
public class MostrarProdutoComboServlet extends HttpServlet {

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
        boolean zerarCarrinho = false;
        if (request.getParameter("carrinhoVazio") != null && Boolean.parseBoolean(request.getParameter("carrinhoVazio"))) {
            zerarCarrinho = true;
        }
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        //request.setAttribute("produtos", produtoNegocio.obterTodos());
        request.setAttribute("combos", produtoNegocio.obterPorCategoria(1));
        //request.setAttribute("bebidas", produtoNegocio.obterPorCategoria(2));
        //request.setAttribute("salgados", produtoNegocio.obterPorCategoria(3));
        //request.setAttribute("doces", produtoNegocio.obterPorCategoria(4));
        //request.setAttribute("variedades", produtoNegocio.obterPorCategoria(5));
        Cookie c = CookieUtils.obterCookie(request); // obtém o cookie da aplicação, caso exista
        if (!zerarCarrinho) {//se não for para zerar o carrinho
            if (c == null) {
                // se o cookie não existir, cria-o vazio
                c = new Cookie(CookieUtils.COOKIE_KEY, null);
                c.setValue("");
            } else {
                // caso o cookie já exista, resgata o carrinho de compras armazenado dentro do valor do cookie
                List<CarrinhoItem> carrinho = CarrinhoNegocio.obterCarrinho(c.getValue());
                request.setAttribute("carrinho", carrinho);

            }
        } else {// se for para zerar o carrinho, cria um novo cookie vazio
            c = new Cookie(CookieUtils.COOKIE_KEY, null);
            c.setValue("");

        }
        c.setMaxAge(Integer.MAX_VALUE); // atualiza a idade do cookie para o máximo do valor inteiro
        response.addCookie(c);
        request.getRequestDispatcher("mostrarCombos.jsp").forward(request, response);
    }

}
