package com.tp.staffing.exceptions;

public class NullShiftIdException extends Exception {

    public NullShiftIdException(String message) {
        super(message);
    }

    public NullShiftIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
