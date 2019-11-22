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
package br.com.uol.pagseguro.api.utils;

import br.com.uol.pagseguro.api.common.domain.Config;
import br.com.uol.pagseguro.api.common.domain.Document;
import br.com.uol.pagseguro.api.common.domain.enums.ConfigKey;
import br.com.uol.pagseguro.api.common.domain.enums.Currency;
import br.com.uol.pagseguro.api.http.HttpJsonRequestBody;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.Date;

/**
 * Class used to convert the data on a json string to pass the parameters of the requests in api
 *
 * @author PagSeguro Internet Ltda.
 */
public final class RequestJson {

  private final StringBuilder sb;

  /**
   * Constructor
   */
  public RequestJson() {
    this(new StringBuilder());
  }

  /**
   * Constructor
   *
   * @param sb StringBuilder to pass the parameters of the requests in api
   */
  public RequestJson(StringBuilder sb) {
    this.sb = sb;
  }

  /**
   * Put all json data
   *
   * @param requestJson Request Json
   * @param jsonNodeName String
   * @return RequestJson requestJson
   */
    public RequestJson putJson(RequestJson requestJson, String jsonNodeName) {
    if (requestJson == null) {
      throw new NullPointerException();
    }
    return putJson(requestJson.sb, jsonNodeName);
  }

  /**
   * Put all json data
   *
   * @param sb StringBuilder
   * @param jsonNodeName String
   * @return Request StringBuilder
   */
    public RequestJson putJson(StringBuilder sb, String jsonNodeName) {
    if (sb == null) {
      throw new NullPointerException();
    }
    if (!sb.toString().isEmpty()) {
      if(sb.length() > 0) {
        sb.setLength(sb.length() - 1);
      }
      this.sb.append(String.format("\"%s\":{%s},", jsonNodeName, sb.toString()));
    }
    return this;
  }

  /**
   * Put all json data without add a new node
   *
   * @param requestJson Request Json
   * @return RequestJson requestJson
   */
  public RequestJson putJsonList(RequestJson requestJson) {
    if (requestJson == null) {
      throw new NullPointerException();
    }
    return putJsonList(requestJson.sb);
  }

  /**
   * Put all json data without add a new node
   *
   * @param sb StringBuilder
   * @return Request StringBuilder
   */
  public RequestJson putJsonList(StringBuilder sb) {
    if (sb == null) {
      throw new NullPointerException();
    }
    if (!sb.toString().isEmpty()) {
      if(sb.length() > 0) {
        sb.setLength(sb.length() - 1);
      }
      this.sb.append(String.format("%s", sb.toString()));
    }
    return this;
  }

  /**
   * Put Json data as array
   *
   * @param requestJson Request Json
   * @param jsonNodeName String
   * @return RequestJson requestJson
   */
  public RequestJson putJsonArray(RequestJson requestJson, String jsonNodeName) {
    if (requestJson == null) {
      throw new NullPointerException();
    }
    return putJsonArray(requestJson.sb, jsonNodeName);
  }

  /**
   * Put Json data as array
   *
   * @param sb StringBuilder
   * @param jsonNodeName String
   * @return Request StringBuilder
   */
  public RequestJson putJsonArray(StringBuilder sb, String jsonNodeName) {
    if (sb == null) {
      throw new NullPointerException();
    }
    if (!sb.toString().isEmpty()) {
      if(sb.length() > 0) {
        sb.setLength(sb.length() - 1);
      }
      this.sb.append(String.format("\"%s\":[%s],", jsonNodeName, sb.toString()));
    }
    return this;
  }

  /**
   * Put string on json string
   *
   * @param key   Key
   * @param value String value
   * @return Request json
   */
  public RequestJson putString(String key, String value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null) {
      return this;
    }
    sb.append(String.format("\"%s\":\"%s\",", key, value));
    return this;
  }

  /**
   * Put string on json string
   *
   * @param key   Key
   * @param value String Builder value
   * @return Request Json
   */
  public RequestJson putString(String key, StringBuilder value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null || value.toString().isEmpty()) {
      return this;
    }
    sb.append(String.format("\"%s\":\"%s\",", key, value.toString()));
    return this;
  }

  /**
   * Put string on json string
   *
   * @param key   Key
   * @param value String value
   * @return Request json
   */
  public RequestJson putStringNoEscape(String key, String value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null) {
      return this;
    }
    sb.append(String.format("\"%s\":%s,", key, value));
    return this;
  }

  /**
   * Put integer on Json
   *
   * @param key   Key
   * @param value Integer value
   * @return Request Json
   */
  public RequestJson putInteger(String key, Integer value) {
    return putStringNoEscape(key, value == null ? null : value.toString());
  }

  /**
   * Put currency on Json
   *
   * @param key   Key
   * @param value BigDecimal value
   * @return Request json
   */
  public RequestJson putCurrency(String key, BigDecimal value) {
    return putStringNoEscape(key, value == null ? null : value.setScale(2, RoundingMode.HALF_EVEN).toString());
  }

  /**
   * Put currency on Json
   *
   * @param key   Key
   * @param value Currency value
   * @return Request Json
   * @see Currency
   */
  public RequestJson putCurrency(String key, Currency value) {
    return putStringNoEscape(key, value == null ? null : value.getStringValue());
  }

  /**
   * Put date on Json
   *
   * @param key        Key
   * @param value      Date value
   * @param dateFormat Date format
   * @return Request Json
   */
  public RequestJson putDate(String key, Date value, DateFormat dateFormat) {
    return putString(key, value == null ? null : dateFormat.format(value));
  }

  /**
   * Put Document as String in json string
   *
   * @param typeKey   typeKey
   * @param valueKey  valueKey
   * @param document Document
   * @return Request json
   */
  public RequestJson putDocument(String typeKey, String valueKey, Document document) {
    if (typeKey == null || valueKey == null) {
      throw new NullPointerException();
    }
    if (document == null) {
      return this;
    }
    sb.append(String.format("{\"%s\":\"%s\",\"%s\":\"%s\"},", typeKey, document.getType().toString(), valueKey, document.getValue()));
    return this;
  }

  /**
   * Put start key element "{" on json string
   *
   * @return Request json
   */
  public RequestJson putJsonObjectStart() {
    sb.append("{");
    return this;
  }

  /**
   * Remove comma and add end key element plus comma "}," on json string
   *
   * @return Request json
   */
  public RequestJson putJsonObjectEnd() {
    if (sb == null) {
      throw new NullPointerException();
    }
    if(sb.length() > 0) {
      sb.setLength(sb.length() - 1);
    }
    sb.append(String.format("},"));
    return this;
  }

  /**
   * Put config on Json
   *
   * @param key    Key
   * @param config Config value
   * @return Request Json
   * @see Config
   */
  public RequestJson putConfigValue(String key, Config config) {
    if (ConfigKey.DISCOUNT_PERCENT.equals(config.getKey())) {
      return putCurrency(key, config.getValue());
    } else if (ConfigKey.MAX_INSTALLMENTS_LIMIT.equals(config.getKey())) {
      return putInteger(key, config.getValue().intValue());
    } else if (ConfigKey.MAX_INSTALLMENTS_NO_INTEREST.equals(config.getKey())) {
      return putInteger(key, config.getValue().intValue());
    }
    return this;
  }

  /**
   * Convert to http request body
   *
   * @param charset Encoding
   * @return Http Request Body
   * @see HttpJsonRequestBody
   * @throws UnsupportedEncodingException if not accepted encode is used
   */
  public HttpJsonRequestBody toHttpJsonRequestBody(String charset) throws UnsupportedEncodingException {
    return new HttpJsonRequestBody(//
        String.format("application/json; charset=%s", charset),
        encodeBodyContent(toString(), charset),
        charset);
  }

  @Override
  public String toString() {
    if(sb.length() > 0) {
      return "{" + sb.substring(0, sb.length() - 1) + "}";
    }
    return "{}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RequestJson)) return false;

    RequestJson sb1 = (RequestJson) o;

    return sb != null ? sb.equals(sb1.sb) : sb1.sb == null;

  }

  /**
   * Encode the body content to PagSeguro API requirement
   *
   * @param toEncodeString String to be encoded
   * @param charset Charset used to encode
   * @return String body encoded by charset param
   * @throws UnsupportedEncodingException if not accepted encode is used
   */
  public String encodeBodyContent(String toEncodeString, String charset) throws UnsupportedEncodingException {
    byte[] ptext = toEncodeString.getBytes();
    return new String(ptext, charset);
  }

  /**
   * Put boolean on map
   *
   * @param key   Key
   * @param value Boolean value
   * @return Request json
   */
  public RequestJson putBoolean(String key, Boolean value) {
    if (key == null) {
      throw new NullPointerException();
    }
    if (value == null) {
      return this;
    }
    sb.append(String.format("\"%s\":\"%b\",", key, value));
    return this;
  }
}
