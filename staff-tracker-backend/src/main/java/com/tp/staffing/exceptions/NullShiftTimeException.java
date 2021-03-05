package com.tp.staffing.exceptions;

public class NullShiftTimeException extends Exception {

    public NullShiftTimeException(String message) {
        super(message);
    }

    public NullShiftTimeException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
