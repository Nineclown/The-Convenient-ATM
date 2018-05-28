package com.swad.cppatm.exceptions;

public class FrozenAccountException extends Exception {
    // Constructor with no parameter
    public FrozenAccountException() {
    }

    // Constructor that accepts a message
    public FrozenAccountException(String message) {
        super(message);
    }
}
