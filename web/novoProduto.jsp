<%@page import="modelo.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
    String tipo = (String) session.getAttribute("tipo");
    if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="WEB-INF/pages/cabecalhoEstabelecimento.jsp" %>
<%    }
%>
<!----------------------------------------------------------------------------------------->
<%
    if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="WEB-INF/pages/cabecalhoCliente.jsp" %>
<%    }
%>
<!----------------------------------------------------------------------------------------->
<%
    if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="WEB-INF/pages/cabecalho.jsp" %>
<%    }
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->
<h1>Cadastrar novo produto</h1>
<form name="cadastro_produto" action="IncluirProdutoServlet" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
    <div>Nome:</div>
    <div><input type="text" name="nome" id="nome" required value="<%= (request.getAttribute("nome") != null) ? request.getAttribute("nome") : ""%>"/></div>
    <div>Descrição:</div>
    <div><input type="text" name="descricao" id="descricao" value="<%= (request.getAttribute("descricao") != null) ? request.getAttribute("descricao") : ""%>"/></div>
    <div>Preço:</div>
    <div><input type="text" name="preco" id="preco" required value="<%= (request.getAttribute("preco") != null) ? request.getAttribute("preco") : ""%>"/><span id="msg"></span></div>
    <div>Imagem:</div>
    <div><input type="file" name="imagem" id="imagem" /></div>
    <div>Quantidade Disponível:</div>
    <div><input type="text" name="quantidade" id="quantidade" required value="<%= (request.getAttribute("quantidade") != null) ? request.getAttribute("quantidade") : ""%>"/></div>
    <div>Disponibilidade:</div>
    <div><input type="text" name="disponibilidade" id="disponibilidade" required value="<%= (request.getAttribute("disponibilidade") != null) ? request.getAttribute("disponibilidade") : ""%>"/></div>
    <div>ID da Categoria:</div>
    <div>
        <select name="categoria_id" id="categoria_id">
            <%
                List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
                for (int i = 0; categorias != null && i < categorias.size(); i++) {
                    Categoria c = categorias.get(i);
            %>
            <option value="<%= c.getId()%>"><%= c.getNome()%></option>
            <%
                }
            %>
        </select>
    </div>
    <div><input type="submit" value="Salvar" onclick="return validarCamposNumericos('id', 'preco', 'categoria_id', 'msg')"/></div>
</form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
