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
        <title>OnTime</title>
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
                        <div class="div-table-col"><a href="InicioServlet">Home</a></div>
                        <div class="div-table-col"><a href="VerClienteServlet">Meus Dados de Cliente</a></div>
                        <div class="div-table-col"><a href="VerPedidoServlet">Meus Pedidos</a></div>
                        <div class="div-table-col"><a href="LogoutServlet">Sair</a></div>
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
