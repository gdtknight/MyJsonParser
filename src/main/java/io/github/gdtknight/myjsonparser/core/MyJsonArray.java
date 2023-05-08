package io.github.gdtknight.myjsonparser.core;

import io.github.gdtknight.myjsonparser.exception.MyJsonException;

import java.util.ArrayList;

import static io.github.gdtknight.myjsonparser.constants.MyJsonConstants.SQUARE_CLOSE_BRACKET;
import static io.github.gdtknight.myjsonparser.constants.MyJsonConstants.SQUARE_OPEN_BRACKET;

/**
 * Json 배열([])과 맵핑되는 클래스.
 *
 * <p>Json 배열 원소에 해당하는 각각의 Json Value 는 콤마(,)로 구분된다.
 *
 * <p>(참고 - <a href="https://www.json.org/json-ko.html">json.org</a>)
 *
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-08
 */
public class MyJsonArray implements MyJsonParser {
  /** 파싱한 Json 배열 원소를 저장 */
  private ArrayList<Object> objectArr;

  public MyJsonArray(String jsonStr) {
    try {
      parseMyJsonArray(jsonStr);
    } catch (MyJsonException e) {
      System.out.println(e.getMessage());
      objectArr = null;
    }
  }

  private void parseMyJsonArray(String jsonStr) throws MyJsonException {
    objectArr = new ArrayList<>();

    if (jsonStr.startsWith(String.valueOf(SQUARE_OPEN_BRACKET))
        && jsonStr.endsWith(String.valueOf(SQUARE_CLOSE_BRACKET))) {
      jsonStr = jsonStr.substring(1, jsonStr.length() - 1).trim();
    }

    if ("".equals(jsonStr)) return;

    StringBuilder builder = new StringBuilder(jsonStr);

    // 모든 엔트리 검색
    while (!builder.isEmpty()) {
      // Value 추출
      switch (builder.charAt(0)) {
        case '{' -> objectArr.add(parseJsonObjectValue(builder));
        case '[' -> objectArr.add(parseJsonArrayValue(builder));
        case '"' -> objectArr.add(parseJsonStringValue(builder));
        default -> objectArr.add(parseOtherJsonValue(builder));
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("[ ");
    objectArr.forEach(value -> sb.append(value).append(" , "));
    if (!objectArr.isEmpty()) {
      sb.delete(sb.length() - 3, sb.length());
    }
    sb.append(" ]");

    return sb.toString();
  }
}
