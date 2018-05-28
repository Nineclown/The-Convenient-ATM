package com.swad.cppatm;

import com.swad.cppatm.application.Admin;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.exceptions.InvalidAdminException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminTest {

    @Test(expected = InvalidAdminException.class)
    public void shouldConstructorCheckContactsValidity() throws InvalidAdminException {
        Admin admin = new Admin("1", "1234", "123412341234");
        admin = new Admin("1", "1234", "010-1234-1234");
        admin = new Admin("2", "1234", "01012341234");

        assertEquals("1", admin.getId());
        assertEquals("1234", admin.getPassword());
        assertEquals("01012341234", admin.getContact());
    }

    @Test(expected = InvalidAdminException.class)
    public void shouldConstructorCheckPasswordValidity() throws InvalidAdminException {
        Admin admin = new Admin("1", "abcd", "01012341234");
        admin = new Admin("1", "asifj9qfhe", "01012341234");
        admin = new Admin("1", "1234", "01012341234");

        assertEquals("1", admin.getId());
        assertEquals("1234", admin.getPassword());
        assertEquals("01012341234", admin.getContact());
    }

    @Test
    public void shouldCheckAdminAccount() {
        DataStore ds = new DataStore();
        ArrayList<Admin> adminList = ds.loadAdminData();
        assertTrue(adminList.get(0).checkAdminAccount("1", "1234"));
        assertFalse(adminList.get(0).checkAdminAccount("1", "1444"));
    }
}
