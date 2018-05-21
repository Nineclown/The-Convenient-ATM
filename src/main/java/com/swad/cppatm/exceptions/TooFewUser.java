package com.swad.cppatm.exceptions;

public class TooFewUser extends Exception {

    public TooFewUser() {

    }

    public TooFewUser(String message) {
        super(message);
    }
}
