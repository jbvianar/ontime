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

        <h2>Faça sua escolha!</h2>
        <table border="1">
            <tr>
                <td><a href="MostrarProdutoComboServlet">Combos</a></td>
                <td><a href="MostrarProdutoBebidaServlet">Bebidas</a></td>
            </tr>
            <tr>
                <td><a href="MostrarProdutoSalgadoServlet">Salgados</a></td>
                <td><a href="MostrarProdutoDoceServlet">Doces</a></td>
            </tr>
            <tr>
                <td><a href="MostrarProdutoVariedadeServlet">Variedades</a></td>
                <td><a href="MostrarTodosServlet">Todos</a></td>
            </tr>
        </table>

<%@include file="WEB-INF/pages/rodape.jsp" %>
