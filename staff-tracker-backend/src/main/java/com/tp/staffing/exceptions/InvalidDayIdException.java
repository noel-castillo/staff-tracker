package com.tp.staffing.exceptions;

public class InvalidDayIdException extends Exception {

    public InvalidDayIdException(String message) {
        super(message);
    }

    public InvalidDayIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
