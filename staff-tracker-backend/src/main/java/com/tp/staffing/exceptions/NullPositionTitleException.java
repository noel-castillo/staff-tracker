package com.tp.staffing.exceptions;

public class NullPositionTitleException extends Exception {

    public NullPositionTitleException(String message) {
        super(message);
    }

    public NullPositionTitleException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
