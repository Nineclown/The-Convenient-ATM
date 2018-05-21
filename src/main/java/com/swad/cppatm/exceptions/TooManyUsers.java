package com.swad.cppatm.exceptions;

public class TooManyUsers extends Exception {
    public TooManyUsers() {

    }

    public TooManyUsers(String message) {
        super(message);
    }
}
