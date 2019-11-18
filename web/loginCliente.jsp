<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--SELETOR DE CABEÇALHO: EXIBIRÁ UM CABEÇALHO DIFERENTE PARA CLIENTE, ESTABELECIMENTO OU USUÁRIO NÃO LOGADO-->
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
        <h1>Faça seu login</h1>
            <form name="logincliente" action="LoginServlet" method="post">
                <div>Login:</div>
                <div><input type="text" name="login" required/><span id="msg"></span></div>
                <div>Senha:</div>
                <div><input type="password" name="senha" required/><span id="msg"></span></div>
                <div><input type="submit" value="Fazer Login" /></div>
            </form>
<%@include file="WEB-INF/pages/rodape.jsp" %>
