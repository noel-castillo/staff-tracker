package com.tp.staffing.exceptions;

public class NullEmployeeIdException extends Exception {

    public NullEmployeeIdException(String message) {
        super(message);
    }

    public NullEmployeeIdException(String message, Throwable innerException) {
        super(message, innerException);
    }
}
