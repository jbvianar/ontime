<%@page import="java.util.List"%>
<%@page import="modelo.cliente.Cliente"%>
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
<div id="titulo">Cadastro de Clientes</div>
<% List<Cliente> resultado = (List<Cliente>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>Nome</th>
        <th>E-mail</th>
        <th>Telefone</th>
        <th class="controles"></th>
    </tr>
    <% for (Cliente item : resultado) {%>
    <tr>
        <td><%= item.getNome()%></td>
        <td><%= item.getEmail()%></td>
        <td><%= item.getTelefone()%></td>
        <!--DELETAR A LINHA DE BAIXO ASSIM QUE OS TESTES FICAREM PRONTOS-->
        <!--<td><a href="ObterClienteServlet?login=<%--= item.getLogin()--%>">Alterar</a>&nbsp;<a href="ExcluirClienteServlet?login=<%--= item.getLogin()--%>">Excluir</a></td>-->
    </tr>
    <% } %>
</table>
<% }%>
<%@include file="../rodape.jsp" %>