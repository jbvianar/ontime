<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, FUNCIONÁRIO OU USUÁRIO NÃO LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo funcionário logado
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
<div id="titulo">Meus Dados</div>
<jsp:useBean id="estabelecimento" class="modelo.estabelecimento.Estabelecimento" scope="request" />
<div class="rotulo">Razão Social:</div>
<div class="valor"><jsp:getProperty name="estabelecimento" property="razaosocial" /></div>
<div class="rotulo">CNPJ:</div>
<div class="valor"><jsp:getProperty name="estabelecimento" property="cnpj" /></div>
<div class="rotulo">Login:</div>
<div class="valor"><jsp:getProperty name="estabelecimento" property="login" /></div>
<div class="rotulo">E-mail:</div>
<div class="valor"><jsp:getProperty name="estabelecimento" property="email" /></div>
<div class="rotulo">Telefone:</div>
<div class="valor"><jsp:getProperty name="estabelecimento" property="telefone" /></div>
</br>
<div><a href="ObterEstabelecimentoServlet?login=<%= estabelecimento.getLogin()%>">Alterar Dados</a></div>
<%@include file="rodape.jsp" %>
