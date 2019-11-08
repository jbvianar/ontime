/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

import java.util.Date;





/**
 *
 * @author Sony
 */
public class Pedido {
    private Long id;
    private String observacoes;//opcional
    private String agendamento;//opcional
    private Date horario;//data + horário
    private Integer senhadopedido;
    private String status;
    private Double valortotal;
    private String cliente_login;
    private String estabelecimento_login;//variável para futura escalabilidade

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

    public Integer getSenhadopedido() {
        return senhadopedido;
    }

    public void setSenhadopedido(Integer senhadopedido) {
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
