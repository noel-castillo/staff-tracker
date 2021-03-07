package com.tp.staffing.exceptions;

public class NullDayIdException extends Exception {

    public NullDayIdException(String message) {
        super(message);
    }

    public NullDayIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
