package com.tp.staffing.exceptions;

public class InvalidPositionIdException extends Exception {

    public InvalidPositionIdException(String message) {
        super(message);
    }

    public InvalidPositionIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
