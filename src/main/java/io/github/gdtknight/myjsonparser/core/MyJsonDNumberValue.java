package io.github.gdtknight.myjsonparser.core;

/**
 * @author YongHo Shin
 * @version v1.1
 * @since 2023-05-08
 */
public class MyJsonDNumberValue extends MyJsonValue {
  private final Double value;

  public MyJsonDNumberValue(String value) {
    this.value = Double.parseDouble(value);
  }

  @Override
  public Double getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
