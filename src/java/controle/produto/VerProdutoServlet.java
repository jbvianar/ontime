/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.produto.Produto;
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de obter dados de um produto
 */
public class VerProdutoServlet extends HttpServlet {

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
        HttpSession session = request.getSession(); // recupera a sessão do usuário
        Integer id = (Integer) session.getAttribute("id"); // recupera o atributo de id armazenado na sessão do usuário; caso não exista, é retornado nulo
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        Produto produto = produtoNegocio.obterProduto(id); // recupera o objeto produto por meio do id armazenado na sessão
        if (produto != null) { // caso exista o produto com o id armazenado na sessão
            request.setAttribute("produto", produto);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/verProduto.jsp");
            rd.forward(request, response);
        } else { // caso o id não exista na sessão ou não seja um id válido no sistema
            request.setAttribute("mensagem", "Você não possui um id válido");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

}
