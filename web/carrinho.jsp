<%@page import="java.text.DecimalFormat"%>
<%@page import="modelo.carrinho.CarrinhoItem"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DecimalFormat formatarMoeda = new DecimalFormat("#,##0.00");
%>
<!--SELETOR DE  CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
<%
    String tipo = (String) session.getAttribute("tipo");
    if (tipo != null && tipo.equals("estabelecimento")) { // usuário do tipo estabelecimento logado
%>
<%@include file="WEB-INF/pages/cabecalhoEstabelecimento.jsp" %>
<%    }
%>
<!----------------------------------------------------------------------------------------->
<%
    if (tipo != null && tipo.equals("cliente")) {// usuário do tipo cliente logado
%>
<%@include file="WEB-INF/pages/cabecalhoCliente.jsp" %>
<%    }
%>
<!----------------------------------------------------------------------------------------->
<%
    if (tipo == null) {// o usuário não possui uma sessão válida
%>
<%@include file="WEB-INF/pages/cabecalho.jsp" %>
<%    }
%>
<!--------------------------FIM DO SELETOR DE CABEÇALHO----------------------------------->

<!---------------CARRINHO DE COMPRAS------------------------------------------>
<%
    List<CarrinhoItem> carrinhoItens = (List<CarrinhoItem>) request.getAttribute("carrinho");
    if (carrinhoItens != null && carrinhoItens.size() > 0) {
%>
<h2>Meu Carrinho de Compras</h2>
<section>
    <table border="1">
        <h2>Meu Carrinho</h2>
        <tr>
            <th>Nome</th>
            <th>Preço</th>
            <th>Quantidade</th>
            <th>&nbsp;</th>
        </tr>
        <%
            double total = 0;
            for (CarrinhoItem c : carrinhoItens) {
                total += c.getQuantidade() * c.getProduto().getPreco();
        %>
        <form action="RemoverProdutoCarrinhoServlet">
            <tr>

                <td><%= c.getProduto().getNome()%></td>
                <td>R$ <%= formatarMoeda.format(c.getProduto().getPreco())%></td>
                <td><%= c.getQuantidade()%></td>
                <td>
                    <input type="hidden" name="produtoId" value="<%= c.getProduto().getId()%>" />
                    <input type="submit" value="X" />
                </td>
            </tr>
        </form>
        <%
            }
        %>
        <form action="ProcessarPedidoServlet">
            <input type="hidden" name="valorTotal" value="<%=total%>" />
            <tr>
                <td>Observações:</td> 
                <td colspan="2"><input type="text" name="observacoes" id="observacoes" maxlength="180" value="<%= (request.getAttribute("observacoes") != null) ? request.getAttribute("observacoes") : ""%>" /></td>
                <td>Agendar:
                    <select name="agendamento" id="agendamento">
                        <option value=""></option>
                        <option value="12:00">12h00</option>
                        <option value="14:00">14h00</option>
                        <option value="16:00">16h00</option>
                        <option value="18:00">18h00</option>
                        <option value="20:00">20h00</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="3">Total: R$ <%= formatarMoeda.format(total)%>
                    <input type="submit" value="Finalizar" />
                </td>
            </tr>
        </form>
    </table>
</section>
<%
    }
%>
<!--FIM DO CARRINHO-->
<%@include file="WEB-INF/pages/rodape.jsp" %>