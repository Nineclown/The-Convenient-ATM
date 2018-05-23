package com.swad.cppatm.exceptions;

public class UserDoestNotExist extends Exception {
    public UserDoestNotExist() {

    }

    public UserDoestNotExist(String message) {
        super(message);
    }
}
