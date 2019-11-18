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
<div id="titulo">Alterar Dados do Estabelecimento</div>
<% Estabelecimento estabelecimento = (Estabelecimento) request.getAttribute("estabelecimento"); %>
<% if (estabelecimento != null) {%>
<form action="AlterarEstabelecimentoServlet" method="post">
     <div class="rotulo">Razão Social:</div>
    <div class="campo"><input type="text" name="razaosocial" id="razaosocial" value="<%= estabelecimento.getRazaosocial()%>" readonly="readonly" required/></div>
    <div class="rotulo">CNPJ:</div>
    <div class="campo"><input type="text" name="cnpj" id="cnpj" value="<%= estabelecimento.getCnpj()%>" readonly="readonly" required/><span id="msg"></span></div>
    <div class="rotulo">Login:</div>
    <div class="campo"><input type="text" name="login" id="login" value="<%= estabelecimento.getLogin()%>" readonly="readonly" required/></div>
    <div class="rotulo">Senha:</div>
    <div class="campo"><input type="password" name="senha" id="senha" value="<%= estabelecimento.getSenha()%>" required/></div>
    <div class="rotulo">E-mail:</div>
    <div class="campo"><input type="text" name="email" id="email" value="<%= estabelecimento.getEmail()%>" required/></div>
    <div class="rotulo">Telefone:</div>
    <div class="campo"><input type="text" name="telefone" id="telefone" value="<%= estabelecimento.getTelefone()%>" required/></div>
    <div class="controles"><input type="submit" value="Salvar" onclick="return validarCampoNumerico('cnpj', 'msg')"/></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>