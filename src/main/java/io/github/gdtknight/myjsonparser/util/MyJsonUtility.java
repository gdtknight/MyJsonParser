package io.github.gdtknight.myjsonparser.util;

import io.github.gdtknight.myjsonparser.exception.MyJsonException;
import io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode;

import static io.github.gdtknight.myjsonparser.constants.MyJsonConstants.*;
import static io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode.EMPTY_VALUE_ERROR;
import static io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode.INVALID_FORMAT_ERROR;

/**
 * Json 형태의 문자열을 StringBuilder 타입으로 전달받아 의미있는 토큰으로 분리하여 문자열 형태로 반환해주는 유틸리티 클래스
 *
 * @author YongHoShin
 * @version v1.0
 * @since 2023-05-08
 */
public class MyJsonUtility {
  /**
   * @param builder Json Object or Json Array 를 포함하는 StringBuilder
   * @return 제일 처음에 있는 Json 객체 또는 Json 배열의 문자열.
   * @throws MyJsonException '{}','[]' 쌍의 개수가 맞지 않거나, 제대로 닫히지 않은 경우 INVALID_FORMAT 에러코드
   * @see MyJsonException
   * @see MyJsonErrorCode
   */
  public static String extractJsonObjOrJsonArrValueStr(StringBuilder builder)
      throws MyJsonException {
    int closeBracketIdx = getCloseBracketIdx(builder);

    String valueStr = builder.substring(0, closeBracketIdx + 1);
    builder.delete(0, closeBracketIdx + 1);

    // 구분자(',') 제거
    if (builder.indexOf(String.valueOf(PAIR_SEPERATOR)) != -1) {
      builder.delete(0, builder.indexOf(String.valueOf(PAIR_SEPERATOR)) + 1);
    }

    removeLeadingSpace(builder);

    return valueStr;
  }

  /**
   * @param builder Json 문자열을 포함하는 StringBuilder
   * @return 쌍따옴표('"') 를 벗겨낸 Json String Value 를 문자열 타입으로 반환.
   * @throws MyJsonException 정상적인 쌍따옴표('"') 로 끝나지 않을 경우 INVALID_FORMAT 에러
   */
  public static String extractJsonStringValueStr(StringBuilder builder) throws MyJsonException {
    int openQuotesIdx = builder.indexOf(String.valueOf(DOUBLE_QUOTES));
    int closeQuotesIdx = builder.indexOf(String.valueOf(DOUBLE_QUOTES), openQuotesIdx + 1);

    while (builder.charAt(closeQuotesIdx - 1) == SPECIAL_BACKSLASH.getCharVal()) {
      closeQuotesIdx = builder.indexOf(String.valueOf(DOUBLE_QUOTES), closeQuotesIdx + 1);
    }

    if (closeQuotesIdx == -1) {
      throw new MyJsonException(INVALID_FORMAT_ERROR);
    }

    String value = builder.substring(openQuotesIdx, closeQuotesIdx + 1);
    builder.delete(0, closeQuotesIdx + 1);

    value = value.substring(1, value.length() - 1);

    if (builder.indexOf(String.valueOf(PAIR_SEPERATOR)) != -1) {
      builder.delete(0, builder.indexOf(String.valueOf(PAIR_SEPERATOR)) + 1);
    }

    removeLeadingSpace(builder);

    return value;
  }

  /**
   * @param builder Json 문자열을 포함하는 StringBuilder
   * @return 숫자형, 논리형 Json value 를 문자열로 추출.
   * @throws MyJsonException 아무값도 없는 경우 EMPTY_VALUE 에러
   */
  public static String extractOtherValueStr(StringBuilder builder) throws MyJsonException {
    String valueStr;

    if (builder.indexOf(String.valueOf(PAIR_SEPERATOR)) != -1) {
      valueStr = builder.substring(0, builder.indexOf(String.valueOf(PAIR_SEPERATOR)));
      builder.delete(0, builder.indexOf(String.valueOf(PAIR_SEPERATOR)) + 1);
    } else {
      valueStr = builder.toString().trim();
      builder.delete(0, builder.length());
    }

    if (valueStr == null) throw new MyJsonException(EMPTY_VALUE_ERROR);

    removeLeadingSpace(builder);

    return valueStr;
  }

  /**
   * @param builder Json Object or Json Array 를 포함하는 StringBuilder
   * @return 첫번째 Json Object 또는 Json Array 의 마지막 인덱스. ('}',']' 가 처음으로 나타나는 인덱스)
   * @throws MyJsonException '{}','[]' 쌍의 개수가 맞지 않거나, 제대로 닫히지 않은 경우 INVALID_FORMAT 에러
   */
  private static int getCloseBracketIdx(StringBuilder builder) throws MyJsonException {
    int closeBracketIdx = -1;
    int checkSquarePair = 0;
    int checkCurlyPair = 0;

    for (int idx = 0; idx < builder.length(); idx++) {
      if (builder.charAt(idx) == CURLY_OPEN_BRACKET.getCharVal()) {
        checkCurlyPair++;
      } else if (builder.charAt(idx) == SQUARE_OPEN_BRACKET.getCharVal()) {
        checkSquarePair++;
      } else if (builder.charAt(idx) == CURLY_CLOSE_BRACKET.getCharVal()) {
        checkCurlyPair--;
      } else if (builder.charAt(idx) == SQUARE_CLOSE_BRACKET.getCharVal()) {
        checkSquarePair--;
      }

      if (checkCurlyPair == 0 && checkSquarePair == 0) {
        closeBracketIdx = idx;
        break;
      }
    }

    if (closeBracketIdx == -1) {
      throw new MyJsonException(INVALID_FORMAT_ERROR);
    }

    return closeBracketIdx;
  }

  /**
   * 인자로 전달받은 StringBuilder 시작 부분의 모든 공백 제거.
   *
   * <p>파싱 후 호출하여 다음 파싱이 제대로 수행될 수 있도록 한다.
   *
   * @param builder StringBuilder 객체
   */
  public static void removeLeadingSpace(StringBuilder builder) {
    while (!builder.isEmpty() && builder.charAt(0) == SPACE.getCharVal()) {
      builder.deleteCharAt(0);
    }
  }
}
