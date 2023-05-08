package io.github.gdtknight.myjsonparser.core;

/**
 * @author YongHo Shin
 * @version v1.1
 * @since 2023-05-08
 */
public class MyJsonBooleanValue extends MyJsonValue {
  private final Boolean value;

  public MyJsonBooleanValue(String value) {
    this.value = "true".equalsIgnoreCase(value);
  }

  @Override
  public Boolean getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
