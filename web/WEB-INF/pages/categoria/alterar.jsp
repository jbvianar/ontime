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
<% Categoria categoria = (Categoria) request.getAttribute("categoria"); %>
<% if (categoria != null) {%>
<form action="AlterarCategoriaServlet" method="post">
    <div class="rotulo">ID da Categoria:</div>
    <div class="campo"><input type="text" name="id" id="id" value="<%= categoria.getId()%>" readonly="readonly" required/><span id="msg"></span></div>
    <div class="rotulo">Descrição:</div>
    <div class="campo"><input type="text" name="nome" id="nome" value="<%= categoria.getNome()%>" required/></div>
    <div class="controles"><input type="submit" value="Salvar" onclick="return validarCampoNumericoInt('id', 'msg')"/></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>