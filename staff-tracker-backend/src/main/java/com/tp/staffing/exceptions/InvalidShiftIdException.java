package com.tp.staffing.exceptions;

public class InvalidShiftIdException extends Exception {

    public InvalidShiftIdException(String message) {
        super(message);
    }

    public InvalidShiftIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
