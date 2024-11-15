package com.admin.exception;

public class AppointmentNotFoundException extends RuntimeException {

  // Constructor không tham số
  public AppointmentNotFoundException() {
    super();
  }

  // Constructor với thông điệp lỗi
  public AppointmentNotFoundException(String message) {
    super(message);
  }

  // Constructor với thông điệp lỗi và nguyên nhân (Throwable)
  public AppointmentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  // Constructor với nguyên nhân Throwable
  public AppointmentNotFoundException(Throwable cause) {
    super(cause);
  }
}
