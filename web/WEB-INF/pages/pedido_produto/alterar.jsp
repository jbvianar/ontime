<%@page import="modelo.pedido_produto.Pedido_produto"%>
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
<div id="titulo">Cadastro de Pedido_produtos</div>
<% Pedido_produto pedido_produto = (Pedido_produto) request.getAttribute("pedido_produto"); %>
<% if (pedido_produto != null) {%>
<form action="AlterarPedido_produtoServlet" method="post">
    <div class="rotulo">ID do Pedido:</div>
    <div class="campo"><input type="text" name="pedido_id" id="pedido_id" value="<%= pedido_produto.getPedido_id()%>" readonly="readonly" required/><span id="msg"></span></div>
    <div class="rotulo">ID do Produto:</div>
    <div class="campo"><input type="text" name="produto_id" id="produto_id" value="<%= pedido_produto.getProduto_id()%>" readonly="readonly" required/></div>
    <div class="rotulo">Quantidade:</div>
    <div class="campo"><input type="text" name="quantidade" id="quantidade" value="<%= pedido_produto.getQuantidade()%>" required/></div><!--!!!-->
    <div class="rotulo">Login do Cliente:</div>
    <div class="campo"><input type="text" name="cliente_login" id="cliente_login" value="<%= pedido_produto.getCliente_login()%>" required/></div><!--!!!-->
    <div class="controles"><input type="submit" value="Salvar" onclick="return validarCampoNumericoInt('qtd', 'msg')"/></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>