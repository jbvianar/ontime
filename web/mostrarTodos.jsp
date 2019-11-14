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
        <%
        DecimalFormat formatarMoeda = new DecimalFormat("#,##0.00");
        %>
        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && produtos.size() > 0) {
        %> 
        <h2>Todos os produtos disponíveis</h2>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>Descrição</td>
                <td>Preço</td>
                <td>Imagem</td>
                <td>Disponível</td>
                <td>Quantidade</td>
            </tr>
            <%
                for (Produto p : produtos) {
            %>
            <form action="AdicionarProdutoCarrinhoServlet">
            <tr>
                <td><%= p.getNome()%></td>
                <td><%= p.getDescricao()%></td>
                <td>R$ <%= formatarMoeda.format(p.getPreco()) %></td>
                <td><img src="MostrarImagemProdutoServlet?foto=<%= p.getImagem()%>" alt="Imagem de <%= p.getNome()%>" width="100" height="100" /></td>
                <td>Disponível: <%= p.getQuantidade()%></td>
                <td>Quantidade
                    <input type="hidden" name="produtoId" value="<%= p.getId() %>" />
                    <input type="number" name="quantidade" value="1" min="1" max="<%= p.getQuantidade()%>"/>
                    <input type="submit" value="Adicionar ao Carrinho" />
                </td>
            </tr>
            </form>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <div>Não existem produtos cadastrados</div>
        <%
            }
        %>
<%@include file="WEB-INF/pages/rodape.jsp" %>
