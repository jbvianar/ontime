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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>OnTime</title>

        <link href="css/principal.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="js/script.js"></script>

    </head>
    <body>

        <div id="nav-container_01">
            <div class="bg"></div>
            <div class="button" tabindex="0">
                <span class="linha-menu"></span>
                <span class="linha-menu"></span>
                <span class="linha-menu"></span>
            </div>

            <div id="nav-content" tabindex="0">
                <ul id="categorias_01" class="categorias_01">
                    <li>
                        <div class="circle_photo" id="circle_photo">
                            <img src="img/profile.png"></div>
                        <div class="texto" id="texto"><p>Olá, Fulano Fulano</p></div>
                    </li>

                    <li><a href="InicioServlet">Início</a></li>
                    <li><a href="VerClienteServlet">Minha Conta</a></li>
                    <li><a href="VerPedidoServlet">Meus Pedidos</a></li>
                    <li><a href="MostrarProdutoCarrinhoServlet">Carrinho</a></li>
                    <li><a href="LogoutServlet">Sair</a></li>
                </ul>
            </div>
        </div>

        <div id="menu">

            <div class="div-table-col"><a href="MostrarProdutoCarrinhoServlet"><img src="img/carrinho.png"></a></div>
            <div>
                <% List<Estabelecimento> resultado = (List<Estabelecimento>) request.getAttribute("status");%>
                <% for (int i = 0; resultado != null && i < resultado.size(); i++) {%>
                <% Estabelecimento item = resultado.get(i);%>  
                <div><%= (item.getStatus() != null && item.getStatus() == true) ? "Lanchonete Aberta" : "Lanchonete Fechada"%></div>
                <% } %>
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
