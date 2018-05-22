package com.swad.cppatm.exceptions;

public class NegativeBalanceError extends Exception {
    public NegativeBalanceError() {

    }

    public NegativeBalanceError(String message) {
        super(message);
    }
}
