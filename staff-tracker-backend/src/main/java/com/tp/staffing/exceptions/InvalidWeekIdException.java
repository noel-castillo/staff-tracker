package com.tp.staffing.exceptions;

public class InvalidWeekIdException extends Exception {

    public InvalidWeekIdException(String message) {
        super(message);
    }

    public InvalidWeekIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
