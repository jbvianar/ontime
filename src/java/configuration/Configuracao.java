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
    public static final String JDBC_SENHA = "jb123";
    public static final String REPOSITORIO_IMAGEM_PRODUTOS = "C:\\Users\\Sony\\Desktop\\img";

    private Configuracao() {
        
    }
}
