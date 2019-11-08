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
<% Cliente cliente = (Cliente) request.getAttribute("cliente"); %>
<% if (cliente != null) {%>
<form action="AlterarClienteServlet" method="post">
    <div class="rotulo">Nome:</div>
    <div class="campo"><input type="text" name="nome" id="nome" value="<%= cliente.getNome()%>" required/></div>
    <div class="rotulo">Login:</div>
    <div class="campo"><input type="text" name="login" id="login" value="<%= cliente.getLogin()%>" readonly="readonly" required/></div>
    <div class="rotulo">Senha:</div>
    <div class="campo"><input type="password" name="senha" id="senha" value="<%= cliente.getSenha()%>" required/></div>
    <div class="rotulo">E-mail:</div>
    <div class="campo"><input type="text" name="email" id="email" value="<%= cliente.getEmail()%>" required/></div>
    <div class="rotulo">Telefone:</div>
    <div class="campo"><input type="text" name="telefone" id="telefone" value="<%= cliente.getTelefone()%>" /></div>
    <div class="controles"><input type="submit" value="Salvar" /></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>