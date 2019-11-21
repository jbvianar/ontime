<%@page import="modelo.estabelecimento.Estabelecimento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String login = (String) session.getAttribute("login");
    if (login == null) {
        request.setAttribute("mensagem", "Você não possui um login válido");
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
%>
<!DOCTYPE html>
<html><!--este é o cabeçalho visto somente com login de cliente-->
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
                        <div class="div-table-col"><a href="VerClienteServlet">Minha Conta</a></div>
                        <div class="div-table-col"><a href="VerPedidoServlet">Meus Pedidos</a></div>
                        <div class="div-table-col"><a href="MostrarProdutoCarrinhoServlet">Carrinho de Compras</a></div>
                        <div class="div-table-col"><a href="LogoutServlet">Fazer Logout</a></div>
                        <div>
                            <% List<Estabelecimento> resultado = (List<Estabelecimento>) request.getAttribute("status");%>
                            <% for (int i = 0; resultado != null && i < resultado.size(); i++) {%>
                            <% Estabelecimento item = resultado.get(i);%>  
                            <div><%= (item.getStatus() != null && item.getStatus() == true) ? "Lanchonete Aberta" : "Lanchonete Fechada"%></div>
                            <% } %>
                        </div>
                    </div>
                </div>
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
