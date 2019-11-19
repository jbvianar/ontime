<%@page import="modelo.pedido_produto.Pedido_produto"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="modelo.pedido.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        DecimalFormat formatarMoeda = new DecimalFormat("#,##0.00");
%>
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
<div id="titulo">Pedidos Entregues</div>
<% List<Pedido> resultado = (List<Pedido>) request.getAttribute("resultado"); %>
<% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");%>
<% if (resultado != null && resultado.size() > 0) { %>
<table>
    <tr>
        <th>ID do Pedido</th>
        <th>Data e Horário</th>
        <th>Itens</th>
        <th>Valor Total</th>
        <th>Observações</th>
        <th>Agendamento</th>
        <th>Status</th>
        <th>Senha do Pedido</th>
        <th>Nome do Cliente</th>
        <th class="controles"></th>
    </tr>
    <% for (Pedido item : resultado) {%>
    <tr>
        <td><%= item.getId()%></td>
        <td><%= sdf.format(item.getHorario())%></td>
        <td>
            <% List<Pedido_produto> produtos = item.getProdutos();
            for (int i = 0; i < produtos.size(); i++) {
                out.print(produtos.get(i).getProduto_nome() + " - " + produtos.get(i).getQuantidade());
                if (i < produtos.size() - 1) {
                    out.println(",");
                }
            }
            %>
        </td>
        <td>R$ <%= formatarMoeda.format(item.getValortotal())%></td>
        <td><%= item.getObservacoes() == null ? "" : item.getObservacoes() %></td>
        <td><%= item.getAgendamento() == null ? "" : item.getAgendamento() %></td>
        <td><%= item.getStatus()%></td>
        <td><%= item.getSenhadopedido()%></td>
        <td><%= item.getCliente_nome()%></td>
        <!--<td><a href="ObterPedidoServlet?id=<%--= item.getId()--%>">Alterar</a>&nbsp;<a href="ExcluirPedidoServlet?id=<%--= item.getId()--%>">Excluir</a></td>-->
    </tr>
    <% } %>
</table>
<% }%>
<br/>
<!--<div><a href="novoPedido.jsp">Criar novo pedido</a></div>-->
<%@include file="../rodape.jsp" %>