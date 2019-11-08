/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import static configuration.Configuracao.REPOSITORIO_IMAGEM_PRODUTOS;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.ProdutoNegocio;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 *
 * @author Sony Classe que representa a ação de alterar dados de um produto
 * existente
 */
public class AlterarProdutoServlet extends HttpServlet {

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
        Integer id = null;
        String nome = null;
        String descricao = null;
        Double preco = null;
        Integer quantidade = null;
        Boolean disponibilidade = null;
        Integer categoria_id = null;
        FileItem foto = null;
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            boolean sucesso = false;
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File(REPOSITORIO_IMAGEM_PRODUTOS + File.separator + "temp"));
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Iterator<FileItem> iter = items.iterator();

                ProdutoNegocio produtoNegocio = new ProdutoNegocio();

                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (item.isFormField() && item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("nome")) {
                        nome = (item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("descricao")) {
                        descricao = (item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("preco")) {
                        preco = Double.parseDouble(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("quantidade")) {
                        quantidade = Integer.parseInt(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("disponibilidade")) {
                        disponibilidade = Boolean.parseBoolean(item.getString());
                    }
                    if (item.isFormField() && item.getFieldName().equals("categoria_id")) {
                        categoria_id = Integer.parseInt(item.getString());
                    }
                    if (!item.isFormField() && item.getFieldName().equals("imagem") && item.getContentType().startsWith("image/")) {
                        foto = item;
                    }
                }
                boolean uploadFoto = false;
                if (id != -1 && foto != null) {
                    foto.write(new File(REPOSITORIO_IMAGEM_PRODUTOS + File.separator + id + foto.getName().substring(foto.getName().lastIndexOf("."))));
                    uploadFoto = true;
                }
                String fotoStr = "";
                if (uploadFoto) {
                    fotoStr = id + foto.getName().substring(foto.getName().lastIndexOf("."));
                }
                sucesso = produtoNegocio.alterar(id, nome, descricao, preco, fotoStr, quantidade, disponibilidade, categoria_id);

                if (!sucesso) {
                    request.setAttribute("mensagem", "Não foi possível alterar este produto");
                } else {
                    request.setAttribute("mensagem", "Produto alterado com sucesso");
                }
            } catch (Exception ex) {
                request.setAttribute("mensagem", "Não foi possível alterar este produto");
            }
            RequestDispatcher rd = request.getRequestDispatcher("ObterProdutoServlet?id=" + id);
            rd.forward(request, response);
        }

    }
}
