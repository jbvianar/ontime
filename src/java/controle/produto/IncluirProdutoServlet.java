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
 * @author Sony Classe que representa a ação de cadastrar um novo produto
 */
public class IncluirProdutoServlet extends HttpServlet {

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
        boolean error = false;
        Integer id = null;
        String nome = null;
        String descricao = null;
        Double preco = null;
        String imagem = null;
        Integer quantidade = null;
        Boolean disponibilidade = null;
        Integer categoria_id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            nome = request.getParameter("nome");
            if (request.getParameter("descricao").trim().length() > 0) {
                descricao = request.getParameter("descricao");
            }
            preco = Double.parseDouble(request.getParameter("preco"));
            if (request.getParameter("imagem").trim().length() > 0) {
                imagem = request.getParameter("imagem");
            }
            quantidade = Integer.parseInt(request.getParameter("quantidade"));
            disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidade"));
            categoria_id = Integer.parseInt(request.getParameter("categoria_id"));
        } catch (Exception ex) {
            error = true;
        } finally {
            //recuperação
            request.setAttribute("id", id);
            request.setAttribute("nome", nome);
            request.setAttribute("descricao", descricao);
            request.setAttribute("preco", preco);
            request.setAttribute("imagem", imagem);
            request.setAttribute("quantidade", quantidade);
            request.setAttribute("disponibilidade", disponibilidade);
            request.setAttribute("categoria_id", categoria_id);
        }
        if (!error) {
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
        } else {
            request.setAttribute("mensagem", "Não foi possível inserir este produto");
            RequestDispatcher rd = request.getRequestDispatcher("novoProduto.jsp");
            rd.forward(request, response);
        }

    }

}
