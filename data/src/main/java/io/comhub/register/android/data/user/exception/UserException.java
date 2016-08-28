package io.comhub.register.android.data.user.exception;

public class UserException extends RuntimeException {

  public UserException() {
  }

  public UserException(String detailMessage) {
    super(detailMessage);
  }

  public UserException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public UserException(Throwable throwable) {
    super(throwable);
  }
}
