/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.feedback;

import java.util.Date;

/**
 *
 * @author Sony
 */
public class Feedback {
    private Long id;//AUTOINCREMENTO
    private Date horario;//data + horário - AUTOCAPTADO
    private String mensagem;//caixa de texto com limite de caracteres
    private String cliente_login;
    
    private String cliente_nome;//para recuperar o nome do cliente sem mostrar seu login

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

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCliente_login() {
        return cliente_login;
    }

    public void setCliente_login(String cliente_login) {
        this.cliente_login = cliente_login;
    }
    
}
