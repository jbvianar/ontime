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

package br.com.uol.pagseguro.api.installment;

import br.com.uol.pagseguro.api.common.domain.converter.ParameterV2MapConverter;
import br.com.uol.pagseguro.api.utils.AbstractMapConverter;
import br.com.uol.pagseguro.api.utils.RequestMap;

/**
 * Converter for V2 Installment listing
 *
 * @author PagSeguro Internet Ltda.
 */
public class InstallmentRequestV2MapConverter extends AbstractMapConverter<InstallmentRequest> {

  InstallmentRequestV2MapConverter() {
  }

  private static final ParameterV2MapConverter PARAMETER_MC = new ParameterV2MapConverter();

  /**
   * Convert Interface for Installment listing on Request Map
   *
   * @param requestMap         Request Map used to pass params to api
   * @param installmentRequest Interface for Installment listing
   * @see RequestMap
   * @see InstallmentRequest
   * @see AbstractMapConverter#convert(Object)
   */
  @Override
  protected void convert(RequestMap requestMap, InstallmentRequest installmentRequest) {
    requestMap.putString("cardBrand", installmentRequest.getCardBrand());
    requestMap.putCurrency("amount", installmentRequest.getAmount());
    requestMap.putInteger("maxInstallmentNoInterest",
        installmentRequest.getMaxInstallmentNoInterest());
    requestMap.putMap(PARAMETER_MC.convert(installmentRequest.getParameters()));
  }
}
