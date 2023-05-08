package io.github.gdtknight.myjsonparser.exception;

/**
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-08
 */
public enum MyJsonErrorCode {
  EMPTY_VALUE_ERROR("Can't extract any json value."),
  INVALID_FORMAT_ERROR("Invalid formatted json."),
  INVALID_KEY_ERROR("Invalid key."),
  NO_MAPPING_OBJECT_ERROR("No mapping object.");

  private final String message;

  MyJsonErrorCode(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
