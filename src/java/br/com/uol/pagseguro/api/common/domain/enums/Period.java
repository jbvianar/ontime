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

package br.com.uol.pagseguro.api.common.domain.enums;

/**
 * Period. Used to define the periodicity of the charging.
 *
 * @author PagSeguro Internet Ltda.
 */
public enum Period {

  /**
   * Weekly charging
   */
  WEEKLY("WEEKLY"),

  /**
   * Monthly charging
   */
  MONTHLY("MONTHLY"),

  /**
   * Bimonthly charging
   */
  BIMONTHLY("BIMONTHLY"),

  /**
   * Trimonthly charging
   */
  TRIMONTHLY("TRIMONTHLY"),

  /**
   * Semiannually charging
   */
  SEMIANNUALLY ("SEMIANNUALLY"),

  /**
   * Yearly charging
   */
  YEARLY ("YEARLY"),

  /**
   * Unrecognized
   */
  UNRECOGNIZED(null);

  private final String value;

  /**
   * Constructor
   *
   * @param value Enum value
   */
  Period(String value) {
    this.value = value;
  }

  /**
   * Get value
   *
   * @return Enum value
   */
  public String getValue() {
    return value;
  }

  /**
   * Get enum by value
   *
   * @param value Enum value
   * @return Enum
   */
  public static Period fromValue(String value) {
    for (Period period : Period.values()) {
      if (period.value != null && period.value.equalsIgnoreCase(value)) {
        return period;
      }
    }
    return UNRECOGNIZED;
  }
}
