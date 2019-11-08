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
<div id="titulo">Seus Dados</div>
<jsp:useBean id="cliente" class="modelo.cliente.Cliente" scope="request" />
<div class="rotulo">Nome:</div>
<div class="valor"><jsp:getProperty name="cliente" property="nome" /></div>
<div class="rotulo">Login:</div>
<div class="valor"><jsp:getProperty name="cliente" property="login" /></div>
<div class="rotulo">E-mail:</div>
<div class="valor"><jsp:getProperty name="cliente" property="email" /></div>
<div class="rotulo">Telefone:</div>
<div class="valor"><jsp:getProperty name="cliente" property="telefone" /></div>
</br>
<div><a href="ObterClienteServlet?login=<%= cliente.getLogin()%>">Alterar Dados</a>&nbsp;<a href="ExcluirClienteServlet?login=<%= cliente.getLogin()%>">Excluir Conta</a></div>
<%@include file="rodape.jsp" %>
