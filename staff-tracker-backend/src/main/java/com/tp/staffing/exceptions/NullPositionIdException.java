package com.tp.staffing.exceptions;

public class NullPositionIdException extends Exception {

    public NullPositionIdException(String message) {
        super(message);
    }

    public NullPositionIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
