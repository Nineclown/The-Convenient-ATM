package com.swad.cppatm.application;

import com.swad.cppatm.exceptions.InvalidAdminException;

import java.util.regex.Pattern;

public class Admin {
    private String id;
    private String password;
    private String contact;

    public Admin(String id, String password, String contact){
        this.id = id;
        this.password = password;
        this.contact = contact;
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
