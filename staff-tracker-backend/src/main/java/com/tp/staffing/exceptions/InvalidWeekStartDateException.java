package com.tp.staffing.exceptions;

public class InvalidWeekStartDateException extends Exception {

    public InvalidWeekStartDateException(String message) {
        super(message);
    }

    public InvalidWeekStartDateException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
