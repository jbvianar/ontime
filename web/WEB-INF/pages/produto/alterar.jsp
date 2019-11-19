<%@page import="modelo.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="../cabecalhoEstabelecimento.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="../cabecalhoCliente.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="../cabecalho.jsp" %>
<%
}
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->
<div id="titulo">Alterar produto</div>
<% Produto produto = (Produto) request.getAttribute("produto"); %>
<% if (produto != null) {%>
<form action="AlterarProdutoServlet" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
    <div class="rotulo">ID do Produto:</div>
    <div class="campo"><input type="text" name="id" id="id" value="<%= produto.getId()%>" readonly="readonly" required/><span id="msg"></span></div>
    <div class="rotulo">Nome do Produto:</div>
    <div class="campo"><input type="text" name="nome" id="nome" value="<%= produto.getNome()%>" required/></div>
    <div class="rotulo">Descrição:</div>
    <div class="campo"><input type="text" name="descricao" id="descricao" value="<%= produto.getDescricao()%>" /></div>
    <div class="rotulo">Preço:</div>
    <div class="campo"><input type="text" name="preco" id="preco" value="<%= produto.getPreco()%>" required/><span id="msg"></span></div>
    <div class="rotulo">Imagem:</div>
    <div class="campo"><input type="file" name="imagem" id="imagem" /><span id="msg"></span></div><!--tags span mantidas aqui para possível uso futuro na aplicação final-->
    <div class="rotulo">Quantidade à venda:</div>
    <div class="campo"><input type="text" name="quantidade" id="quantidade" value="<%= produto.getQuantidade()%>" required/><span id="msg"></span></div><!--tags span mantidas aqui para possível uso futuro na aplicação final-->
    <div class="rotulo">Disponível?</div>
    <div class="campo"><select name="disponibilidade" id="disponibilidade">
            <option value="true">Sim</option>
            <option value="false">Não</option>
        </select>
    </div>
    <div class="rotulo">Categoria:</div>
    <div class="campo">
        <select name="categoria_id" id="categoria_id">
            <%
                List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
                for (int i = 0; categorias != null && i < categorias.size(); i++) {
                    Categoria c = categorias.get(i);
            %>
            <option value="<%= c.getId()%>"<%= produto.getCategoria_id() == c.getId() ? " selected" : "" %>><%= c.getNome()%></option>
            <%
                }
            %>
        </select>
    </div>
    <div class="controles"><input type="submit" value="Salvar" onclick="return validarCamposNumericos('id', 'preco', 'categoria_id', 'msg')"/></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>