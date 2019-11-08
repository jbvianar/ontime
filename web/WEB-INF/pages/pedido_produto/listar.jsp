<%@page import="java.util.List"%>
<%@page import="modelo.pedido_produto.Pedido_produto"%>
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
<div id="titulo">Detalhes do produto pedido</div>
<% List<Pedido_produto> resultado = (List<Pedido_produto>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID da Pedido</th>
        <th>ID do Produto</th>
        <th>Quantidade</th>
        <th>Login do Cliente</th>
        <th class="controles"></th>
    </tr>
    <% for (Pedido_produto item : resultado) {%>
    <tr>
        <td><%= item.getPedido_id()%></td>
        <td><%= item.getProduto_id()%></td>
        <td><%= item.getQuantidade()%></td>
        <td><%= item.getCliente_login()%></td>
        <td><a href="ObterPedido_produtoServlet?pedido_id=<%= item.getPedido_id()%>&produto_id=<%= item.getProduto_id()%>">Alterar</a>&nbsp;<a href="ExcluirPedido_produtoServlet?pedido_id=<%= item.getPedido_id()%>&produto_id=<%= item.getProduto_id()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<br/>
<div><a href="novoPedido_produto.jsp">Criar novo pedido_produto</a></div>
<%@include file="../rodape.jsp" %>