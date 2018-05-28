package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.InvalidAdminException;

import java.util.regex.Pattern;

public class Admin {
    private String id;
    private String password;
    private String contact;

    public Admin(String id, String password, String contact) throws InvalidAdminException {
        if (!this.checkPassword(password) || !this.checkContact(contact)) {
            throw new InvalidAdminException();
        }

        this.id = id;
        this.password = password;
        this.contact = contact;
    }

    private boolean checkPassword(String password) {
        return Pattern.matches("^(\\d{4})$", password);
    }

    private boolean checkContact(String contact) {
        return Pattern.matches("^(0\\d{8,10})$", contact);
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getContact() {
        return this.contact;
    }

    public boolean checkAdminAccount(String adminID, String adminPW) {
        if (this.id.equals(adminID) && this.password.equals(adminPW)) {
            return true;
        }
        return false;
    }
}
