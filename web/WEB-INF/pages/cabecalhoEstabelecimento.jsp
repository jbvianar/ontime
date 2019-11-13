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
                        <div class="div-table-col"><a href="VerEstabelecimentoServlet">Meus Dados</a></div>
                        <div class="div-table-col"><a href="ListarClienteServlet">Cadastro de Clientes</a></div>
                        <div class="div-table-col"><a href="ListarEstabelecimentoServlet">Cadastro de Estabelecimentos</a></div>
                        <div class="div-table-col"><a href="ListarCategoriaServlet">Cadastro de Categorias</a></div>
                        <div class="div-table-col"><a href="ListarProdutoServlet">Cadastro de Produtos</a></div>
                        <div class="div-table-col"><a href="ListarPedidoServlet">Cadastro de Pedidos</a></div>
                        <div class="div-table-col"><a href="LogoutEstabelecimentoServlet">Sair</a></div>
                    </div>
                    <div class="div-table-col">
                        <form action="AlterarEstabelecimentoStatusServlet" id="statusLanchonete">
                            <select name="status" id="status" onchange="document.getElementById('statusLanchonete').submit()">
                                <option value="true">Lanchonete Aberta</option>
                                <option value="false">Lanchonete Fechada</option>
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
