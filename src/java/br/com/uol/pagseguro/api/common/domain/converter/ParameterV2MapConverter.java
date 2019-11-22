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

package br.com.uol.pagseguro.api.common.domain.converter;

import br.com.uol.pagseguro.api.common.domain.Parameter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for parameter. Used in version 2 of the application
 * Used to convert attributes of parameter in request map.
 *
 * @author PagSeguro Internet Ltda.
 */
public class ParameterV2MapConverter extends AbstractMapConverter<Iterable<? extends Parameter>> {

  /**
   * Convert attributes of parameter in request map
   *
   * @param requestMap Request Map used to pass params to api
   * @param parameters The interface of parameters
   * @see RequestMap
   * @see Parameter
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, Iterable<? extends Parameter> parameters) {
    for (Parameter parameter : parameters) {
      requestMap.putString(parameter.getName(), parameter.getValue());
    }
  }
}
