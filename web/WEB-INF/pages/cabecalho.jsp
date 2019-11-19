<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html><!--este é o cabeçalho visto quando não foi feito nenhum login-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OnTime - Virtual Lanches</title>
        <link href="css/principal.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <div id="logo">
            <center>
                <div id="logo-conteudo"><a href="InicioServlet">Virtual Lanches - OnTime</a></div>
            </center>
        </div>
        <div id="menu">
            <center>
                <div id="menu-conteudo" class="div-table">
                    <div class="div-table-row">
                        <div class="div-table-col"><a href="">Menu Sanduíche</a></div>
                        <div class="div-table-col"><a href="loginCliente.jsp">Faça login para fazer seu pedido<br/><br/></a>
                                                   <a href="novoCliente.jsp">Não tem login? Cadastre-se!</a></div>
                        <div class="div-table-col"><a href="loginEstabelecimento.jsp">É dono do estabelecimento? Faça seu login aqui!</a></div>
                        <div class="div-table-col"><a href="MostrarProdutoCarrinhoServlet">Carrinho de Compras</a></div>
                    </div>
                </div>
                <%
                    String mensagem1 = (String) request.getAttribute("mensagem1");
                    if (mensagem1 != null) {
                %>
                <div id="mensagem1"><b><%= mensagem1%></b></div>
                <%
                    }
                %>
                <%
                    String mensagem2 = (String) request.getAttribute("mensagem2");
                    if (mensagem2 != null) {
                %>
                <div id="mensagem2"><b><%= mensagem2%></b></div>
                <%
                    }
                %>
            </center>
        </div>
    <center>    
        <%
            String mensagem = (String) request.getAttribute("mensagem");
            if (mensagem != null) {
        %>

        <div id="mensagem"><b><%= mensagem%></b></div>
                <%
                    }
                %>
        <div id="conteudo">
