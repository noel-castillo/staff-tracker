package com.tp.staffing.exceptions;

public class NullWeekStartDateException extends Exception {

    public NullWeekStartDateException(String message) {
        super(message);
    }

    public NullWeekStartDateException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
