/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

import java.util.Date;
import java.util.List;
import modelo.pedido_produto.Pedido_produto;





/**
 *
 * @author Sony
 */
public class Pedido {
    private Long id;//AUTOINCREMENTO
    private String observacoes;//opcional
    private String agendamento;//opcional
    private Date horario;//data + horário - AUTOCAPTADO
    private String senhadopedido;//AUTOGERADO
    private String status;//mudado para "em preparo na hora da compra" e modificado pelo estabelecimento
    private Double valortotal;//AUTOCALCULADO
    private String cliente_login;//AUTOCAPTADO
    private String estabelecimento_login;//variável para futura escalabilidade - AUTOCAPTADO
    
    private String cliente_nome;//para recuperar o nome do cliente sem mostrar seu login
    List<Pedido_produto> produtos;//vetor para recuperar os produtos do pedido e mostrá-los

    public List<Pedido_produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Pedido_produto> produtos) {
        this.produtos = produtos;
    }
    
    

    public String getCliente_nome() {
        return cliente_nome;
    }

    public void setCliente_nome(String cliente_nome) {
        this.cliente_nome = cliente_nome;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(String agendamento) {
        this.agendamento = agendamento;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getSenhadopedido() {
        return senhadopedido;
    }

    public void setSenhadopedido(String senhadopedido) {
        this.senhadopedido = senhadopedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValortotal() {
        return valortotal;
    }

    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
    }

    public String getCliente_login() {
        return cliente_login;
    }

    public void setCliente_login(String cliente_login) {
        this.cliente_login = cliente_login;
    }

    public String getEstabelecimento_login() {
        return estabelecimento_login;
    }

    public void setEstabelecimento_login(String estabelecimento_login) {
        this.estabelecimento_login = estabelecimento_login;
    }

    
}
