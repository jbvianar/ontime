<%@page import="modelo.pedido.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<div id="titulo">Cadastro de Pedidos</div>
<% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>
<% if (pedido != null) {%>
<form action="AlterarPedidoServlet" method="post">
    <div class="rotulo">ID do Pedido:</div>
    <div class="campo"><input type="text" name="id" id="id" value="<%= pedido.getId()%>" readonly="readonly" required/><span id="msg"></span></div>
    <div class="rotulo">Data e Horário:</div>
    <div class="campo"><input type="text" name="horario" id="horario" value="<%= pedido.getHorario()%>" readonly="readonly" required/></div>
    <div class="rotulo">Observações:</div>
    <div class="campo"><input type="text" name="observacoes" id="observacoes" value="<%= pedido.getObservacoes() == null ? "" : pedido.getObservacoes() %>" readonly="readonly" /></div>
    <div class="rotulo">Agendamento:</div>
    <div class="campo"><input type="text" name="agendamento" id="agendamento" value="<%= pedido.getAgendamento() == null ? "" : pedido.getAgendamento() %>" readonly="readonly" /></div>
    <div class="rotulo">Status:</div>
    <div class="campo"><input type="text" name="status" id="status" value="<%= pedido.getStatus()%>" required/></div>
    <div class="rotulo">Senha do Pedido:</div>
    <div class="campo"><input type="text" name="senhadopedido" id="senhadopedido" value="<%= pedido.getSenhadopedido()%>" required/></div>    
    <div class="rotulo">Valor Total:</div>
    <div class="campo"><input type="text" name="valortotal" id="valortotal" value="<%= pedido.getValortotal()%>" readonly="readonly" required/></div>   
    <div class="rotulo">Login do Cliente:</div>
    <div class="campo"><input type="text" name="cliente_login" id="cliente_login" value="<%= pedido.getCliente_login()%>" required/></div>
    <div class="rotulo">Login do Estabelecimento:</div>
    <div class="campo"><input type="text" name="estabelecimento_login" id="estabelecimento_login" value="<%= pedido.getEstabelecimento_login()%>" required/></div>
    <div class="controles"><input type="submit" value="Salvar" onclick="return validarCampoNumericoInt('id', 'msg')"/></div>
</form>
<% }%>
<%@include file="../rodape.jsp" %>