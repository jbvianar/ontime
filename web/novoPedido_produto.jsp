<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
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
        <h1>Novo pedido de produto</h1>
        <form name="cadastro_pedido_produto" action="IncluirPedido_produtoServlet" method="post">
            <div>ID do Pedido:</div>
            <div><input type="text" name="pedido_id" id="pedido_id" required/></div>
            <div>ID do Produto:</div>
            <div><input type="text" name="produto_id" id="produto_id" required/></div>
            <div>Quantidade:</div>
            <div><input type="text" name="quantidade" id="quantidade" required/></div>
            <div>Login do Cliente:</div>
            <div><input type="text" name="cliente_login" id="cliente_login" required/></div>
            <div><input type="submit" value="Salvar" /></div>
        </form>
        
<%@include file="WEB-INF/pages/rodape.jsp" %>