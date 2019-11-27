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
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>OnTime</title>

     <link href="css/principal.css" rel="stylesheet" type="text/css" />

      <script type="text/javascript" src="js/script.js"></script>

</head>
<body>

    <div id="menu">
        <div class="div-table-col"><a href="MostrarProdutoCarrinhoServlet"><img src="img/carrinho.png" width="28px" height="28px"></a><label>VIRTUAL LANCHES</label></div>
    </div>

     <div class="scrollmenu">
            <a href="#">Combos</a>
            <a href="#">Salgados</a>
            <a href="#">Bebidas</a>
            <a href="#">Doces</a>
            <a href="#">Variedades</a>
        </div>

        <form id="demo-2">
            <input type="search" placeholder="Search">
        </form>

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

     
</body>
</html>    
    <%
        String mensagem = (String) request.getAttribute("mensagem");
        if (mensagem != null) {
    %>

    <div id="mensagem"><b><%= mensagem%></b></div>
            <%
                }
            %>
    
