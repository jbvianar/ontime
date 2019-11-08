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
<div id="titulo">Dados do Produto</div>
<jsp:useBean id="produto" class="modelo.produto.Produto" scope="request" />
<div class="rotulo">ID do Produto:</div>
<div class="valor"><jsp:getProperty name="produto" property="id" /></div>
<div class="rotulo">Nome:</div>
<div class="valor"><jsp:getProperty name="produto" property="nome" /></div>
<div class="rotulo">Descrição:</div>
<div class="valor"><jsp:getProperty name="produto" property="descricao" /></div>
<div class="rotulo">Preço:</div>
<div class="valor"><jsp:getProperty name="produto" property="preco" /></div>
<div class="rotulo">Imagem:</div>
<div class="valor"><jsp:getProperty name="produto" property="imagem" /></div>
<div class="rotulo">Quantidade:</div>
<div class="valor"><jsp:getProperty name="produto" property="quantidade" /></div>
<div class="rotulo">Disponibilidade:</div>
<div class="valor"><jsp:getProperty name="produto" property="disponibilidade" /></div>
<div class="rotulo">ID da Categoria:</div>
<div class="valor"><jsp:getProperty name="produto" property="categoria_id" /></div>
<%@include file="rodape.jsp" %>
