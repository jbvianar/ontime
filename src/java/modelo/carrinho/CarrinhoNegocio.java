/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.carrinho;

import java.util.ArrayList;
import java.util.List;
import modelo.produto.Produto;
import modelo.produto.ProdutoNegocio;

/**
 *
 *
 *
 * Classe de negócio que presenta todas as regras de negócio da entidade
 * carrinho de compra
 */
public final class CarrinhoNegocio {

    private static final String SEPARADOR_DE_ITENS = "SEP_ITENS";
    private static final String SEPARADOR_NO_ITEM = "SEP_REGISTRO";

    private CarrinhoNegocio() {
    }

    /**
     * Método utilizado para obter o carrinho de compras de acordo com o valor
     * do cookie
     *
     * @param valor
     * @return
     */
    public static final List<CarrinhoItem> obterCarrinho(String valor) {
        List<CarrinhoItem> carrinhoItens = new ArrayList<CarrinhoItem>();
        // se o valor do cookie está vazio ou não há separador no item, então está vazio
        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_NO_ITEM)) {
            return carrinhoItens;
        }
        // caso exista algo no carrinho de compras
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        // se o valor do cookie contiver separador de itens, então há mais de dois itens
        if (valor.contains(SEPARADOR_DE_ITENS)) {
            String[] itens = valor.split(SEPARADOR_DE_ITENS);
            for (int i = 0; itens != null && i < itens.length; i++) {
                String[] item = itens[i].split(SEPARADOR_NO_ITEM);
                CarrinhoItem carrinhoItem = new CarrinhoItem();
                Produto produto = produtoNegocio.obterProduto(Integer.parseInt(item[0]));
                carrinhoItem.setProduto(produto);
                carrinhoItem.setQuantidade(Integer.parseInt(item[1]));
                carrinhoItens.add(carrinhoItem);
            }
        // caso o valor tenha apenas um item
        } else {
            String[] item = valor.split(SEPARADOR_NO_ITEM);
            CarrinhoItem carrinhoItem = new CarrinhoItem();
            Produto produto = produtoNegocio.obterProduto(Integer.parseInt(item[0]));
            carrinhoItem.setProduto(produto);
            carrinhoItem.setQuantidade(Integer.parseInt(item[1]));
            carrinhoItens.add(carrinhoItem);
        }
        return carrinhoItens;
    }

    /**
     * Método utilizado para adicionar um novo item de produto ao carrinho de compras
     * 
     * @param produtoId
     * @param quantidade
     * @param valor
     * @return 
     */
    public static final String adicionarItem(int produtoId, int quantidade, String valor) {
        List<CarrinhoItem> carrinhoItens = obterCarrinho(valor);
        if (carrinhoItens.isEmpty()) {
            return produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        boolean adicionou = false;
        String resultado = "";
        for (CarrinhoItem carrinhoItem : carrinhoItens) {
            if (carrinhoItem.getProduto().getId() == produtoId) {
                carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() + quantidade);
                adicionou = true;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_DE_ITENS;
            }
            resultado += carrinhoItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoItem.getQuantidade();
        }
        if (!adicionou) {
            resultado += SEPARADOR_DE_ITENS + produtoId + SEPARADOR_NO_ITEM + quantidade;
        }
        return resultado;
    }

    /**
     * Método utilizado para remover um produto do carrinho de compras
     * 
     * @param produtoId
     * @param valor
     * @return 
     */
    public static final String removerItem(int produtoId, String valor) {
        List<CarrinhoItem> carrinhoItens = obterCarrinho(valor);
        if (carrinhoItens.isEmpty()) {
            return "";
        }
        String resultado = "";
        for (CarrinhoItem carrinhoItem : carrinhoItens) {
            if (carrinhoItem.getProduto().getId() == produtoId) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_DE_ITENS;
            }
            resultado += carrinhoItem.getProduto().getId() + SEPARADOR_NO_ITEM + carrinhoItem.getQuantidade();
        }
        return resultado;
    }

}
