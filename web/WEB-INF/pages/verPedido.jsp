<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.pedido.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="./cabecalhoEstabelecimento.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="./cabecalhoCliente.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="./cabecalho.jsp" %>
<%
}
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->
<div id="titulo">Cadastro de Pedidos</div>
<% List<Pedido> resultado = (List<Pedido>) request.getAttribute("resultado"); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");%>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID do Pedido</th>
        <th>Data e Horário</th>
        <th>Login do Cliente</th>
        <th class="controles"></th>
    </tr>
    <% for (Pedido item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getObservacoes()%></td>
        <td><%= item.getAgendamento()%></td>
        <td><%= sdf.format(item.getHorario())%></td>
        <td><%= item.getSenhadopedido()%></td>
        <td><%= item.getCliente_login()%></td>
        <td><%= item.getStatus()%></td>
        <td><%= item.getValortotal()%></td>
        <td><%= item.getEstabelecimento_login()%></td>
        </tr>
    <% } %>
</table>
<% }%>
<%@include file="./rodape.jsp" %>