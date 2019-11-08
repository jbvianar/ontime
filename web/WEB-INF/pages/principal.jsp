<!--SELETOR DE CABE�ALHO: EXIBIR� UM CABE�ALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USU�RIO N�O LOGADO-->
<%
String tipo = (String) session.getAttribute("tipo");
if (tipo != null && tipo.equals("estabelecimento")) { // usu�rio do tipo estabelecimento logado
%>
<%@include file="cabecalhoEstabelecimento.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo != null && tipo.equals("cliente")) {// usu�rio do tipo cliente logado
%>
<%@include file="cabecalhoCliente.jsp" %>
<%
}
%>
<!----------------------------------------------------------------------------------------->
<%
if (tipo == null) {// o usu�rio n�o possui uma sess�o v�lida
%>
<%@include file="cabecalho.jsp" %>
<%
}
%>
<!--------------------------FIM DO SELETOR DE CABE�ALHO----------------------------------->
<%@include file="rodape.jsp" %>
