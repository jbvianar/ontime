<%@page import="java.util.List"%>
<%@page import="modelo.categoria.Categoria"%>
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
<div id="titulo">Cadastro de Categorias</div>
<% List<Categoria> resultado = (List<Categoria>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID da Categoria</th>
        <th>Nome</th>
        <th class="controles"></th>
    </tr>
    <% for (Categoria item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getNome()%></td>
        <td><a href="ObterCategoriaServlet?id=<%= item.getId()%>">Alterar</a>&nbsp;<a href="ExcluirCategoriaServlet?id=<%= item.getId()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<br/>
<div><a href="novoCategoria.jsp">Criar nova categoria</a></div>
<%@include file="../rodape.jsp" %>