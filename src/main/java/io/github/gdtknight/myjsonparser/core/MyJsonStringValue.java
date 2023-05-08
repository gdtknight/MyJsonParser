package io.github.gdtknight.myjsonparser.core;

/**
 * @author YongHo Shin
 * @version v1.1
 * @since 2023-05-08
 */
public class MyJsonStringValue extends MyJsonValue {
  private final String value;

  public MyJsonStringValue(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "\"" + value + "\"";
  }
}
