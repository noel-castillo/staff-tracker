package com.tp.staffing.exceptions;

public class NullShiftNameException extends Exception {

    public NullShiftNameException(String message) {
        super(message);
    }

    public NullShiftNameException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
