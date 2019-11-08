/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;




/**
 *
 * @author Sony
 */
public class Pedido_produto {//Cada item do pedido
    private Long pedido_id;
    private Integer produto_id;
    private Integer quantidade;
    private String cliente_login;//variável para facilitar a recuperação de cliente pelo item do produto

    public Long getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Long pedido_id) {
        this.pedido_id = pedido_id;
    }

    public Integer getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Integer produto_id) {
        this.produto_id = produto_id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCliente_login() {
        return cliente_login;
    }

    public void setCliente_login(String cliente_login) {
        this.cliente_login = cliente_login;
    }
}
