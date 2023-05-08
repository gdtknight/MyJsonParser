package io.github.gdtknight.myjsonparser.constants;

/**
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-08
 */
public enum MyJsonConstants {
  CURLY_OPEN_BRACKET('{'),
  CURLY_CLOSE_BRACKET('}'),
  SQUARE_OPEN_BRACKET('['),
  SQUARE_CLOSE_BRACKET(']'),
  KEY_VALUE_SEPERATOR(':'),
  PAIR_SEPERATOR(','),
  DOUBLE_QUOTES('"'),
  SPECIAL_BACKSLASH('\\'),
  SPACE(' ');

  private final char charVal;

  MyJsonConstants(char charVal) {
    this.charVal = charVal;
  }

  public char getCharVal() {
    return charVal;
  }

  @Override
  public String toString() {
    return String.valueOf(charVal);
  }
}
