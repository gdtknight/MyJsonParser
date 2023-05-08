package io.github.gdtknight.myjsonparser.core;

import io.github.gdtknight.myjsonparser.exception.MyJsonException;

import static io.github.gdtknight.myjsonparser.exception.MyJsonErrorCode.INVALID_FORMAT_ERROR;
import static io.github.gdtknight.myjsonparser.util.MyJsonUtility.*;

/**
 * Json value 를 포함하고 있는 StringBuilder 를 전달받아 MyJsonObject, MyJsonArray, MyJsonValue 로 파싱한다.
 *
 * @author YongHo Shin
 * @version v1.01
 * @since 2023-05-08
 */
public interface MyJsonParser {
  default MyJsonObject parseJsonObjectValue(StringBuilder builder) throws MyJsonException {
    return new MyJsonObject(extractJsonObjOrJsonArrValueStr(builder));
  }

  default MyJsonArray parseJsonArrayValue(StringBuilder builder) throws MyJsonException {
    return new MyJsonArray(extractJsonObjOrJsonArrValueStr(builder));
  }

  default MyJsonStringValue parseJsonStringValue(StringBuilder builder) throws MyJsonException {
    return new MyJsonStringValue(extractJsonStringValueStr(builder));
  }

  default MyJsonValue parseOtherJsonValue(StringBuilder builder) throws MyJsonException {
    final String numPattern = "^[0-9]+$";
    final String decimalPattern = "^\\d+\\.\\d+$";
    final String booleanPattern = "(true|false)";

    String extractedValueStr = extractOtherValueStr(builder);

    if (extractedValueStr.matches(numPattern)) {
      return new MyJsonNumberValue(extractedValueStr);
    } else if (extractedValueStr.matches(decimalPattern)) {
      return new MyJsonDNumberValue(extractedValueStr);
    } else if (extractedValueStr.toLowerCase().matches(booleanPattern)) {
      return new MyJsonBooleanValue(extractedValueStr.toLowerCase());
    }

    throw new MyJsonException(INVALID_FORMAT_ERROR);
  }
}
