package com.tp.staffing.exceptions;

public class InvalidShiftTimeException extends Exception {

    public InvalidShiftTimeException(String message) {
        super(message);
    }

    public InvalidShiftTimeException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
