package com.deliveroo.assignment.exception;

public class ApplicationException extends RuntimeException{

    private final String userMessage;

    private final String errorCode;

    public ApplicationException(String errorCode, String userMessage) {
        super(errorCode);
        this.errorCode = errorCode;
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
