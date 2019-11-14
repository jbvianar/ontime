<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.estabelecimento.Estabelecimento"%>
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
<div id="titulo">Cadastro de Estabelecimentos</div>
<% List<Estabelecimento> resultado = (List<Estabelecimento>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>Login</th>
        <th>Razão Social</th>
        <th>CNPJ</th>
        <th>Telefone</th>
        <th>Status</th>
        <th class="controles"></th>
    </tr>
    <% for (Estabelecimento item : resultado) {%>
    <tr>
        <td><%= item.getLogin()%></td>
        <td><%= item.getRazaosocial()%></td>
        <td><%= item.getCnpj()%></td>
        <td><%= item.getTelefone()%></td>
        <td><%= item.getStatus()%></td>
        <td><a href="ObterEstabelecimentoServlet?login=<%= item.getLogin()%>">Alterar</a>&nbsp;<a href="ExcluirEstabelecimentoServlet?login=<%= item.getLogin()%>">Excluir</a></td>
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>