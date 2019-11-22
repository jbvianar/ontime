/*
 * 2007-2016 [PagSeguro Internet Ltda.]
 *
 * NOTICE OF LICENSE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright: 2007-2016 PagSeguro Internet Ltda.
 * Licence: http://www.apache.org/licenses/LICENSE-2.0
 */
package br.com.uol.pagseguro.api.http;

import java.io.IOException;
import java.util.Map;

/**
 * Used to execute the requests
 *
 * @author PagSeguro Internet Ltda.
 */
public interface HttpClient {

    /**
     * Execute the requests
     *
     * @param method    Http method
     * @param targetURL target url
     * @param headers   Headers
     * @param body      Body
     * @return Http response
     * @throws IOException if a problem occurs
     */
    HttpResponse execute(HttpMethod method, String targetURL, Map<String, String> headers, HttpRequestBody body)
        throws IOException;

    /**
     * Execute the requests
     *
     * @param method    Http method
     * @param targetURL target url
     * @param headers   Headers
     * @param body      Body
     * @return Http response
     * @throws IOException if a problem occurs
     */
    HttpResponse executeXML(HttpMethod method, String targetURL, Map<String, String> headers, HttpXMLRequestBody body)
        throws IOException;

    //TODO: validate if need to change to only execute without to have to implement it in every class that extends HttpClient, maybe create an JsonCliente
    HttpResponse executeJson(HttpMethod method, String targetURL, Map<String, String> headers, HttpJsonRequestBody body)
        throws IOException;
}
