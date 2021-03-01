package com.tp.staffing.exceptions;

public class InvalidPositionTitleException extends Exception {

    public InvalidPositionTitleException(String message) {
        super(message);
    }

    public InvalidPositionTitleException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
