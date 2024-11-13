package com.admin.exception;

public class ServiceNotFoundException extends Throwable {
    public ServiceNotFoundException(String message) {
        super(message);
    }
}
