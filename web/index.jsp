<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.carrinho.CarrinhoItem"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!---Teste Bosco/ERICK -->
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
        <h2>Produtos</h2>
        <table border="1">
            <tr>
                <td>Id</td>
                <td>Nome</td>
                <td>Descrição</td>
                <td>Preço</td>
                <td>Imagem</td>
                <td>Disponibilidade</td>
                <td>ID da Categoria</td>
                <td>Quantidade</td>
                <td>&nbsp;</td>
            </tr>
            <%
                for (Produto p : produtos) {
            %>
            <form action="AdicionarProdutoCarrinhoServlet">
            <tr>
                
                <td><%= p.getId() %></td>
                <td><%= p.getNome()%></td>
                <td><%= p.getDescricao()%></td>
                <td>R$ <%= formatarMoeda.format(p.getPreco()) %></td>
                <td><%= p.getImagem()%></td>
                <td><%= p.getDisponibilidade()%></td>
                <td><%= p.getCategoria_id()%></td>
                <td>
                    <input type="hidden" name="produtoId" value="<%= p.getId() %>" />
                    <input type="number" name="quantidade" value="1" min="1" />
                </td>
                <td>
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
        <!---------------CARRINHO DE COMPRAS------------------------------------------>
        <%
            List<CarrinhoItem> carrinhoItens = (List<CarrinhoItem>) request.getAttribute("carrinho");
            if (carrinhoItens != null && carrinhoItens.size() > 0) {
        %>
        <h2>Meu Carrinho de Compras</h2>
        <table border="1">
            <tr>
                <td>Nome</td>
                <td>Preço</td>
                <td>Quantidade</td>
                <td>&nbsp;</td>
            </tr>
            <%
                double total = 0;
                for (CarrinhoItem c : carrinhoItens) {
                    total += c.getQuantidade() * c.getProduto().getPreco();
            %>
            <form action="RemoverProdutoCarrinhoServlet">
            <tr>
                
                <td><%= c.getProduto().getNome() %></td>
                <td>R$ <%= formatarMoeda.format(c.getProduto().getPreco()) %></td>
                <td><%= c.getQuantidade() %></td>
                <td>
                    <input type="hidden" name="produtoId" value="<%= c.getProduto().getId() %>" />
                    <input type="submit" value="Remover do Carrinho" />
                </td>
            </tr>
            </form>
            <%
                }
            %>
            <form action="ProcessarPedidoServlet">
            <tr>
                <td colspan="3">Total: R$ <%= formatarMoeda.format(total) %></td>
                <td>
                    <input type="submit" value="Finalizar Pedido" />
                </td>
            </tr>
        </table>
        <%
            }
        %>
        <!--FIM DO CARRINHO-->
<%@include file="WEB-INF/pages/rodape.jsp" %>
