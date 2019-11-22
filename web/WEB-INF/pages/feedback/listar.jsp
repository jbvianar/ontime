<%@page import="java.util.List"%>
<%@page import="modelo.feedback.Feedback"%>
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
<div id="titulo">Lista de Sugestões/Reclamações</div>
<% List<Feedback> resultado = (List<Feedback>) request.getAttribute("resultado"); %>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID do Feedback</th>
        <th>Horário</th>
        <th>Mensagem</th>
        <th>Cliente</th>
        <th class="controles"></th>
    </tr>
    <% for (Feedback item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= item.getHorario()%></td>
        <td><%= item.getMsg()%></td>
        <td><%= item.getCliente_nome()%></td>
        <!--<td><a href="ObterFeedbackServlet?id=<%--= item.getId()--%>">Alterar</a>&nbsp;<a href="ExcluirFeedbackServlet?id=<%--= item.getId()--%>">Excluir</a></td>-->
    </tr>
    <% } %>
</table>
<% }%>
<br/>
<!--<div><a href="novoFeedback.jsp">Criar novo feedback</a></div>-->
<%@include file="../rodape.jsp" %>