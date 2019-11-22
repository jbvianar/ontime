<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.carrinho.CarrinhoItem"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--SELETOR DE  CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="WEB-INF/pages/cabecalhoEstabelecimento.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="WEB-INF/pages/cabecalhoCliente.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="WEB-INF/pages/cabecalho.jsp" %>
<%
}
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->
<body>

    <h2>Faça sua escolha!</h2>
        
        <div class="grid-container-01">
        <a href="MostrarProdutoComboServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
        <a href="MostrarProdutoBebidaServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
        <a href="MostrarProdutoSalgadoServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
        <a href="MostrarProdutoDoceServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
        <a href="MostrarProdutoVariedadeServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
        <a href="MostrarTodosServlet"><div class="grid-item"><img src="img/COMBO.png"></div></a>
    </div>

<%--@include file="WEB-INF/pages/rodape.jsp" --%>
