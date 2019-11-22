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
<html><!--este é o cabeçalho visto somente com login de estabelecimento-->
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
                <ul id="categorias_02" class="categorias_02">
                    <li>
                        <div class="circle_photo" id="circle_photo">
                            <img src="img/profile.png"></div>
                        <div class="texto" id="texto"><p>Olá, Fulano Fulano</p></div>
                    </li>

                    <li><a href="InicioServlet">Início</a></li>
                    <li><a href="VerEstabelecimentoServlet">Minha Conta</a></li>
                    <li><a href="ListarProdutoServlet">Lista de Produtos</a></li>
                    <li><a href="ListarAbertoServlet">Pedidos Abertos</a></li>
                    <li><a href="ListarAgendadoServlet">Pedidos Agendados</a></li>
                    <li><a href="ListarProntoServlet">Pedidos Prontos</a></li>
                    <li><a href="ListarEntregueServlet">Pedidos Entregues</a></li>
                    <li><a href="ListarHistoricoServlet">Histórico</a></li>
                    <li><a href="LogoutEstabelecimentoServlet">Sair</a></li>
                </ul>
            </div>
        </div>

        <div id="menu">

            <!--<div class="div-table-col"><a href="MostrarProdutoCarrinhoServlet">🛒</a></div>--->
            <div class="div-table-col">
                <%
                    String status = null;
                    if (request.getAttribute("status") != null) {
                        status = request.getAttribute("status").toString();
                    }
                %>
                <form action="AlterarEstabelecimentoStatusServlet" id="statusLanchonete">
                    <select name="status" id="status" onchange="document.getElementById('statusLanchonete').submit()">
                        <option value="true"<%= (status != null && status.equalsIgnoreCase("true")) ? " selected" : ""%>>Lanchonete Aberta</option>
                        <option value="false"<%= (status != null && status.equalsIgnoreCase("false")) ? " selected" : ""%>>Lanchonete Fechada</option>
                    </select>
                </form>
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
