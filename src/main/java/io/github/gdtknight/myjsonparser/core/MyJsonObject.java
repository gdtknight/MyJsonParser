package io.github.gdtknight.myjsonparser.core;

import io.github.gdtknight.myjsonparser.exception.MyJsonException;

import java.util.HashMap;
import java.util.Map;

import static io.github.gdtknight.myjsonparser.constants.MyJsonConstants.*;
import static io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode.INVALID_KEY_ERROR;
import static io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode.NO_MAPPING_OBJECT_ERROR;
import static io.github.gdtknight.myjsonparser.util.MyJsonUtility.removeLeadingSpace;

/**
 * Json 객체({})와 맵핑되는 클래스.
 *
 * <p>Key(String),Json Value 를 콜론(:)으로 구분. Key-Value Pair 는 콤마(,)로 구분.
 *
 * <p>(참고 - <a href="https://www.json.org/json-ko.html">json.org</a>)
 *
 * @author YongHo Shin
 * @version v1.1
 * @since 2023-05-08
 */
public class MyJsonObject implements MyJsonParser {

  /** Key (String), Value (Json value) 를 저장 */
  private Map<String, Object> objectMap;

  public MyJsonObject(String jsonStr) {
    try {
      parseToMyJsonObject(jsonStr);
    } catch (MyJsonException e) {
      System.out.println(e.getMessage());
      objectMap = new HashMap<>();
    }
  }

  public Object getValue(String key) throws MyJsonException {
    try {
      return objectMap.get(key);
    } catch (ClassCastException e) {
      throw new MyJsonException(INVALID_KEY_ERROR);
    } catch (NullPointerException e) {
      throw new MyJsonException(NO_MAPPING_OBJECT_ERROR);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("{ ");
    if (!objectMap.isEmpty()) {
      objectMap.forEach(
          (key, value) -> sb.append("\"").append(key).append("\" = ").append(value).append(" , "));
      sb.delete(sb.length() - 3, sb.length());
    }
    sb.append(" }");

    return sb.toString();
  }

  private void parseToMyJsonObject(String jsonStr) throws MyJsonException {
    objectMap = new HashMap<>();
    jsonStr = jsonStr.replaceAll("[\r\n\t]", "").trim();

    if (jsonStr.startsWith(String.valueOf(CURLY_OPEN_BRACKET))
        && jsonStr.endsWith(String.valueOf(CURLY_CLOSE_BRACKET))) {
      jsonStr = jsonStr.substring(1, jsonStr.length() - 1).trim();
    }

    if ("".equals(jsonStr)) return;

    StringBuilder builder = new StringBuilder(jsonStr);

    // Parse all entries (key-value pair)
    while (builder.indexOf(String.valueOf(KEY_VALUE_SEPERATOR)) != -1) {
      int seperatorIdx = builder.indexOf(String.valueOf(KEY_VALUE_SEPERATOR));

      // Key 추출
      String key =
          parseJsonStringValue(new StringBuilder(builder.substring(0, seperatorIdx))).getValue();
      builder.delete(0, seperatorIdx + 1);
      removeLeadingSpace(builder);

      // Value 추출
      switch (builder.charAt(0)) {
        case '{' -> objectMap.put(key, parseJsonObjectValue(builder));
        case '[' -> objectMap.put(key, parseJsonArrayValue(builder));
        case '"' -> objectMap.put(key, parseJsonStringValue(builder));
        default -> objectMap.put(key, parseOtherJsonValue(builder));
      }
    }
  }
}
