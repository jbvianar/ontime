/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import static configuration.Configuracao.REPOSITORIO_IMAGEM_PRODUTOS;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sony
 */
public class MostrarImagemProdutoServlet extends HttpServlet {

   protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String foto = request.getParameter("foto");
        
        ServletContext servletContext = getServletContext();
        
        String imageFileName = REPOSITORIO_IMAGEM_PRODUTOS + File.separator + foto;

        String mimeType = servletContext.getMimeType(imageFileName);
        if (mimeType == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setContentType(mimeType);

        File file = new File(imageFileName);
        response.setContentLength((int) file.length());

        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }

}
