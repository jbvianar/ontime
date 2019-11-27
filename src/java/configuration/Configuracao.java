/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

/**
 *
 * 
 *
 * Classe que representa os elementos de configuração da aplicação
 */
public final class Configuracao {
        

    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/ontime";
    public static final String JDBC_USUARIO = "postgres";
    public static final String JDBC_SENHA = "jb123"; //JB
    //public static final String JDBC_SENHA = "senha"; //Rafael
    //public static final String JDBC_SENHA = "ERICK1895"; //Rafael
    //public static final String JDBC_SENHA = 5433 "ERICK1895";// ERICK
    public static final String REPOSITORIO_IMAGEM_PRODUTOS = "C:\\img";
    //public static final String loginEstabelecimento = "a";

    private Configuracao() {
        
    }
}
