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
            List<Produto> produtos = (List<Produto>) request.getAttribute("combos");
            if (produtos != null && produtos.size() > 0) {
        %> 
        <h2>Combos</h2>
        <table border="1">
                        <!--<tr>
                <div>Nome</div>
                <div>Descrição</div>
                <div>Preço</div>
                <div>Imagem</div>
                <div>Disponível</div>
                <div>Quantidade</div>
            </tr>-->
            <%
                for (Produto p : produtos) {
            %>


<section>
            <div class="col" action="AdicionarProdutoCarrinhoServlet"></div>
            <div class="card-block">
                 <div class="imagem_produto"><img class="card-img-top" src="MostrarImagemProdutoServlet?foto=<%= p.getImagem()%>" alt="Imagem de <%= p.getNome()%>" width="100" height="100"/></div>
                <div><h2><%= p.getNome()%></h2></div>
                <div id="espaço"><%= p.getDescricao() %></div>
                <div id="espaço">R$ <%= formatarMoeda.format(p.getPreco()) %></div>
                <div id="espaço">Disponível: <%= p.getQuantidade()%></div>
                <div>

                    <div><input type="hidden" name="produtoId" value="<%= p.getId() %>" /></div>

                    <div class="number-input">
                    <button onclick="this.parentNode.querySelector('input[type=number]').stepDown()" ></button>
                    <input class="quantity" min="1" name="quantidade" value="1" type="number" max="<%= p.getQuantidade()%>">
                    <button onclick="this.parentNode.querySelector('input[type=number]').stepUp()" class="plus"></button>
                    </div>
                    
                    <div id="bt_prod"><input type="submit" value="Adicionar ao carrinho" /></div></div></div></div>

               </section>
        
            <%
                }
            %>
       
        <%
            } else {
        %>
        <div>Não existem produtos cadastrados nesta categoria</div>
        <%
            }
        %>
<%@include file="WEB-INF/pages/rodape.jsp" %>
