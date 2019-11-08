/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 * Classe estática com métodos para auxiliar a manipulação de cookies
 */
public final class CookieUtils {

    // chave do cookie da aplicação
    public static final String COOKIE_KEY = "lfps.ontimevirtuallanches.cc";

    private CookieUtils() {

    }

    /**
     * Método estático que obtém o cookie da aplicação, retorna nulo caso não exista
     * 
     * @param request
     * @return 
     */
    public static Cookie obterCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie c = null; // referência para o cookie do carrinho de compras da aplicação
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(COOKIE_KEY)) {
                // se achou a referência do carrinho de compras da aplicação, atualiza a referência 'c'
                c = cookies[i];
                break;
            }
        }
        return c;
    }

}
