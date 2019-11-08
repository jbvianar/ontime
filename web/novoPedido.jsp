<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.pedido.Pedido"%>
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
<% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>
        <h1>Novo pedido</h1>
        <form name="cadastro_pedido" action="IncluirPedidoServlet" method="post">
            <div>ID:</div>
            <div><input type="text" name="id" id="id" required value="<%= (request.getAttribute("id") != null) ? request.getAttribute("id") : "" %>"/><span id="msg"></span></div>
            <div>Observações:</div>
            <div><input type="text" name="observacoes" id="observacoes" value="<%= (request.getAttribute("observacoes") != null) ? request.getAttribute("observacoes") : "" %>"/></div>
            <div>Agendamento:</div>
            <div><input type="text" name="agendamento" id="agendamento" value="<%= (request.getAttribute("agendamento") != null) ? request.getAttribute("agendamento") : "" %>"/></div>
            <!--<div>Data e Horário:</div>
            <div><input type="text" name="horario" id="horario" required/></div>-->
            <div>Senha do Pedido:</div>
            <div><input type="text" name="senhadopedido" id="senhadopedido" required value="<%= (request.getAttribute("senhadopedido") != null) ? request.getAttribute("senhadopedido") : "" %>"/></div>
            <div>Status:</div>
            <div><input type="text" name="status" id="status" required value="<%= (request.getAttribute("status") != null) ? request.getAttribute("status") : "" %>"/></div>
            <div>Valor Total:</div>
            <div><input type="text" name="valortotal" id="valortotal" required value="<%= (request.getAttribute("valortotal") != null) ? request.getAttribute("valortotal") : "" %>"/></div>
            <div>Login do Cliente:</div>
            <div><input type="text" name="cliente_login" id="cliente_login" required value="<%= (request.getAttribute("cliente_login") != null) ? request.getAttribute("cliente_login") : "" %>"/></div>
            <div>Login do Estabelecimento:</div>
            <div><input type="text" name="estabelecimento_login" id="estabelecimento_login" required value="<%= (request.getAttribute("estabelecimento_login") != null) ? request.getAttribute("estabelecimento_login") : "" %>"/></div>
            <div><input type="submit" value="Salvar" onclick="return validarCampoNumericoInt('id', 'msg')"/></div>
        </form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
