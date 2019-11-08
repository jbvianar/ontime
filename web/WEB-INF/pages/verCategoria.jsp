<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="cabecalhoEstabelecimento.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="cabecalhoCliente.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="cabecalho.jsp" %>
<%
}
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="titulo">Dados da Categoria</div>
<jsp:useBean id="categoria" class="modelo.categoria.Categoria" scope="request" />
<div class="rotulo">ID do Categoria:</div>
<div class="valor"><jsp:getProperty name="categoria" property="id" /></div>
<div class="rotulo">Nome:</div>
<div class="valor"><jsp:getProperty name="categoria" property="nome" /></div>
<%@include file="rodape.jsp" %>
