package com.swad.cppatm;

import com.swad.cppatm.application.Admin;
import com.swad.cppatm.application.DataStore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminTest {

    @Test
    public void shouldCheckAdminAccount() {
        DataStore ds = new DataStore();
        ArrayList<Admin> adminList = ds.loadAdminData();
        assertTrue(adminList.get(0).checkAdminAccount("1", "1234"));
        assertFalse(adminList.get(0).checkAdminAccount("1", "1444"));
    }
}
