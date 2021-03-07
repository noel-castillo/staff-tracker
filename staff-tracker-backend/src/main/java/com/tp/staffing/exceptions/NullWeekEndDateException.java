package com.tp.staffing.exceptions;

public class NullWeekEndDateException extends Exception {

    public NullWeekEndDateException(String message) {
        super(message);
    }

    public NullWeekEndDateException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
