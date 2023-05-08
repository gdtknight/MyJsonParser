package io.github.gdtknight.myjsonparser.core;

/**
 * @author YongHo Shin
 * @version v1.1
 * @since 2023-05-08
 */
public class MyJsonNumberValue extends MyJsonValue {
  private final Long value;

  public MyJsonNumberValue(String value) {
    this.value = Long.parseLong(value);
  }

  @Override
  public Long getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
