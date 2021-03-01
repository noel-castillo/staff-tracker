package com.tp.staffing.exceptions;

public class InvalidEmployeeLastNameException extends Exception {

    public InvalidEmployeeLastNameException(String message) {
        super(message);
    }

    public InvalidEmployeeLastNameException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
