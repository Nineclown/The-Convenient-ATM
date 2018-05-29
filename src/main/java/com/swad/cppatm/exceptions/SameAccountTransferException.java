package com.swad.cppatm.exceptions;

public class SameAccountTransferException  extends Exception {
    // Parameterless Constructor
    public SameAccountTransferException() {}

    // Constructor that accepts a message
    public SameAccountTransferException(String message)
    {
        super(message);
    }
}
