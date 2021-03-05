package com.tp.staffing.exceptions;

public class InvalidShiftNameException extends Exception {

    public InvalidShiftNameException(String message) {
        super(message);
    }

    public InvalidShiftNameException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
