package io.github.gdtknight.myjsonparser.exception;

/**
 * @author YongHo Shin
 * @version v1.0
 * @since 2023-05-08
 */
public class MyJsonException extends Exception {
  private final MyJsonErrorCode errorCode;
  private final String message;

  public MyJsonException(MyJsonErrorCode errorCode) {
    this.errorCode = errorCode;
    this.message = errorCode.getMessage();
  }

  public MyJsonException(MyJsonErrorCode errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }
}
