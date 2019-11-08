function isInt(n){//função que checa se o número em questão é inteiro
    if (n % 1 === 0){
        return true;//número digitado é inteiro
    } else {
        return false;//número digitado não é inteiro
    }
}

function validarCampoNumerico(id, msg) {
    var valor = document.getElementById(id).value;
    if (valor == null || valor == "" || /^\s+$/.test(valor) || isNaN(valor)) {
        document.getElementById(msg).innerHTML = "Digite um número";        
        return false;
    }
    document.getElementById(msg).innerHTML = "";
    return true;
}

function validarCampoNumericoInt(id, msg) {//validar se o campo numérico é inteiro
    var valor = document.getElementById(id).value;
    if (valor == null || valor == "" || /^\s+$/.test(valor) || isNaN(valor) || !isInt(valor)) {
        document.getElementById(msg).innerHTML = "Digite um número inteiro";        
        return false;
    }
    document.getElementById(msg).innerHTML = "";
    return true;
}

function validarCamposNumericos(id, preco, categoria_id, msg) {
    var valor1 = document.getElementById(id).value;
    var valor2 = document.getElementById(preco).value;
    var valor3 = document.getElementById(categoria_id).value;
    if (valor1 == null || valor1 == "" || /^\s+$/.test(valor1) || isNaN(valor1) || !isInt(valor1)) {
        window.alert("Preencha o campo 'ID do Produto' com um número inteiro");        
        return false;
    }
    if (valor2 == null || valor2 == "" || /^\s+$/.test(valor2) || isNaN(valor2)) {
        window.alert("Preencha o campo 'Preço' com um número");        
        return false;
    }
    if (valor3 == null || valor3 == "" || /^\s+$/.test(valor3) || isNaN(valor3) || !isInt(valor3)) {
        window.alert("Preencha o campo 'ID da Categoria' com um número inteiro");        
        return false;
    }
    document.getElementById(msg).innerHTML = "";
    return true;
}
