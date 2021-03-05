package com.tp.staffing.exceptions;

public class NullDayDateException extends Exception {

    public NullDayDateException(String message) {
        super(message);
    }

    public NullDayDateException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
