package com.swad.cppatm.application;

import java.io.Serializable;

public class Admin implements Serializable {
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
        return this.id.equals(adminID) && this.password.equals(adminPW);
    }
}
