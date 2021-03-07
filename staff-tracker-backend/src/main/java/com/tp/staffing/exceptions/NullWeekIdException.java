package com.tp.staffing.exceptions;

public class NullWeekIdException extends Exception {

    public NullWeekIdException(String message) {
        super(message);
    }

    public NullWeekIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
