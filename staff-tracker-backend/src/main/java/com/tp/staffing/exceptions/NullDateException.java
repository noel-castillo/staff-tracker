package com.tp.staffing.exceptions;

public class NullDateException extends Exception {

    public NullDateException(String message) {
        super(message);
    }

    public NullDateException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
