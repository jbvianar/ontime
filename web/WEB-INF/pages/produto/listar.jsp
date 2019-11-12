<%@page import="java.io.File"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.produto.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        DecimalFormat formatarMoeda = new DecimalFormat("#,##0.00");
%>
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
<div id="titulo">Cadastro de Produtos</div>
<% List<Produto> resultado = (List<Produto>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID do Produto</th>
        <th>Nome do Produto</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Imagem</th>
        <th>Quantidade à venda</th>
        <th>Disponibilidade</th>
        <th>ID da Categoria</th>
        <th class="controles"></th>
    </tr>
    <% for (Produto item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getNome()%></td>
        <td><%= item.getDescricao()%></td>
        <td>R$ <%= formatarMoeda.format(item.getPreco())%></td>
        <td><img src="MostrarImagemProdutoServlet?foto=<%= item.getImagem()%>" width="100" height="100" /></td>
        <td><%= item.getQuantidade()%></td>
        <td><%= item.getDisponibilidade()%></td>
        <td><%= item.getCategoria_id()%></td>
        <td><a href="ObterProdutoServlet?id=<%= item.getId()%>">Alterar</a>&nbsp;<a href="ExcluirProdutoServlet?id=<%= item.getId()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<br/>
<div><a href="NovoProdutoServlet">Criar novo produto</a></div>
<%@include file="../rodape.jsp" %>