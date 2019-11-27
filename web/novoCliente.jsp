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
<html>

	<link href="css/principal.css" rel="stylesheet" type="text/css" />

    <meta name="viewport" content="width=device-width, initial-scale=1.0">


<head>
	<title></title>
</head>
<body>

	<section>

	 <h1>Nova conta</h1>
        <form id="cadastro-cli" name="cadastro_cliente" action="IncluirClienteServlet" method="post">
            <div id="cd_cli">Nome:</div>
            <div><input type="text" name="nome" id="nome" required value="<%= (request.getAttribute("nome") != null) ? request.getAttribute("nome") : "" %>"/></div>
            <div id="cd_cli">Login:</div>
            <div><input type="text" name="login" id="login" required value="<%= (request.getAttribute("login") != null) ? request.getAttribute("login") : "" %>"/></div>
            <div id="cd_cli">Senha:</div>
            <div><input type="password" name="senha" id="senha" required/></div>
            <div id="cd_cli">E-mail:</div>
            <div><input type="text" name="email" id="email" required value="<%= (request.getAttribute("email") != null) ? request.getAttribute("email") : "" %>"/></div>
            <div id="cd_cli">Telefone:</div>
            <div><input type="text" name="telefone" id="telefone" required value="<%= (request.getAttribute("telefone") != null) ? request.getAttribute("telefone") : "" %>"/></div>
            <div><input type="submit" value="Salvar" /></div>
        </form>
    </section>

</body>
</html>

<%@include file="WEB-INF/pages/rodape.jsp" %>
