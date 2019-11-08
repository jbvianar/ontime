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
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 * @author Sony
 * Classe que representa a ação de cadastrar um novo produto
 */
public class NovoProdutoServlet extends HttpServlet {

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
        // entrada
        Integer id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        Double preco = Double.parseDouble(request.getParameter("preco"));
        String imagem = request.getParameter("imagem");
        Integer quantidade = Integer.parseInt(request.getParameter("quantidade"));
        Boolean disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidade"));
        Integer categoria_id = Integer.parseInt(request.getParameter("categoria_id"));
        // processamento
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        boolean sucessoInserir = produtoNegocio.inserir(id, nome, descricao, preco, imagem, quantidade, disponibilidade, categoria_id);
        // saída
        if (sucessoInserir) {
            request.setAttribute("mensagem", "Produto inserido com sucesso");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/produto/listar.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este produto");
            RequestDispatcher rd = request.getRequestDispatcher("novoProduto.jsp");
            rd.forward(request, response);
        }
    }

}
