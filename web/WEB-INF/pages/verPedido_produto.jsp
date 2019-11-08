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
<jsp:useBean id="pedido_produto" class="modelo.pedido_produto.Pedido_produto" scope="request" />
<div class="rotulo">ID do Pedido:</div>
<div class="valor"><jsp:getProperty name="pedido_produto" property="pedido_id" /></div>
<div class="rotulo">ID do Produto:</div>
<div class="valor"><jsp:getProperty name="pedido_produto" property="produto_id" /></div>
<div class="rotulo">Quantidade:</div>
<div class="valor"><jsp:getProperty name="pedido_produto" property="quantidade" /></div>
<div class="rotulo">Cliente:</div>
<div class="valor"><jsp:getProperty name="pedido_produto" property="cliente_login" /></div>
</br>
<div><a href="ObterPedido_produtoServlet?pedido_id=<%= pedido_produto.getPedido_id()%>&produto_id=<%= pedido_produto.getProduto_id()%>">Alterar Dados da Pedido_produto</a>&nbsp;<a href="ExcluirPedido_produtoServlet?pedido_id=<%= pedido_produto.getPedido_id()%>&produto_id=<%= pedido_produto.getProduto_id()%>">Excluir Pedido_produto</a></div>
<%@include file="rodape.jsp" %>
