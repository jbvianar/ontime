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
        <h1>Nova categoria</h1>
        <form name="cadastro_categoria" action="IncluirCategoriaServlet" method="post">
            <div>ID da Categoria:</div>
            <div><input type="text" name="id" id="id" required value="<%= (request.getAttribute("id") != null) ? request.getAttribute("id") : "" %>"/><span id="msg"></span></div>
            <div>Nome:</div>
            <div><input type="text" name="nome" id="nome" required value="<%= (request.getAttribute("nome") != null) ? request.getAttribute("nome") : "" %>"/></div>
            <div><input type="submit" value="Salvar" onclick="return validarCampoNumericoInt('id', 'msg')"/></div>
        </form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
