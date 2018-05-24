package com.swad.cppatm.exceptions;

public class FrozenAccountException extends Exception {
    // Parameterless Constructor
    public FrozenAccountException() {}

    // Constructor that accepts a message
    public FrozenAccountException(String message)
    {
        super(message);
    }
}
