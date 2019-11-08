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
        <h1>Novo produto</h1>
        <form name="cadastro_produto" action="IncluirProdutoServlet" method="post">
            <div>ID do Produto:</div>
            <div><input type="text" name="id" id="id" required value="<%= (request.getAttribute("id") != null) ? request.getAttribute("id") : "" %>"/><span id="msg"></span></div><!--tags span mantidas aqui para possível uso futuro na aplicação final-->
            <div>Nome:</div>
            <div><input type="text" name="nome" id="nome" required value="<%= (request.getAttribute("nome") != null) ? request.getAttribute("nome") : "" %>"/></div>
            <div>Descrição:</div>
            <div><input type="text" name="descricao" id="descricao" value="<%= (request.getAttribute("descricao") != null) ? request.getAttribute("descricao") : "" %>"/></div>
            <div>Preço:</div>
            <div><input type="text" name="preco" id="preco" required value="<%= (request.getAttribute("preco") != null) ? request.getAttribute("preco") : "" %>"/><span id="msg"></span></div>
            <div>Imagem:</div>
            <div><input type="text" name="imagem" id="imagem" value="<%= (request.getAttribute("imagem") != null) ? request.getAttribute("imagem") : "" %>"/></div>
            <div>Quantidade Disponível:</div>
            <div><input type="text" name="quantidade" id="quantidade" required value="<%= (request.getAttribute("quantidade") != null) ? request.getAttribute("quantidade") : "" %>"/></div>
            <div>Disponibilidade:</div>
            <div><input type="text" name="disponibilidade" id="disponibilidade" required value="<%= (request.getAttribute("disponibilidade") != null) ? request.getAttribute("disponibilidade") : "" %>"/></div>
            <div>ID da Categoria:</div>
            <div><input type="text" name="categoria_id" id="categoria_id" required value="<%= (request.getAttribute("categoria_id") != null) ? request.getAttribute("categoria_id") : "" %>"/><span id="msg"></span></div>
            <div><input type="submit" value="Salvar" onclick="return validarCamposNumericos('id', 'preco', 'categoria_id', 'msg')"/></div>
        </form>

<%@include file="WEB-INF/pages/rodape.jsp" %>
