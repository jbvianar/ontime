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

            <form id="Login_Cliente" name="logincliente" action="LoginServlet" method="post">
                <h1>Login Cliente</h1><div><input placeholder="Usuário" type="text" name="login" required/><span id="msg"></span></div>
                <div><input placeholder="Senha" type="password" name="senha" required/><span id="msg"></span></div>
                <div id="bb" ><input type="submit" value="Logar" /></div>
            </form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
