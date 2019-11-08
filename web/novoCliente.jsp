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
        <h1>Novo cliente</h1>
        <form name="cadastro_cliente" action="IncluirClienteServlet" method="post">
            <div>Nome:</div>
            <div><input type="text" name="nome" id="nome" required value="<%= (request.getAttribute("nome") != null) ? request.getAttribute("nome") : "" %>"/></div>
            <div>Login:</div>
            <div><input type="text" name="login" id="login" required value="<%= (request.getAttribute("login") != null) ? request.getAttribute("login") : "" %>"/></div>
            <div>Senha:</div>
            <div><input type="password" name="senha" id="senha" required/></div>
            <div>E-mail:</div>
            <div><input type="text" name="email" id="email" required value="<%= (request.getAttribute("email") != null) ? request.getAttribute("email") : "" %>"/></div>
            <div>Telefone:</div>
            <div><input type="text" name="telefone" id="telefone" required value="<%= (request.getAttribute("telefone") != null) ? request.getAttribute("telefone") : "" %>"/></div>
            <div><input type="submit" value="Salvar" /></div>
        </form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
