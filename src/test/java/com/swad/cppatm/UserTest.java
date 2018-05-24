package com.swad.cppatm;

import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.application.User;
import com.swad.cppatm.exceptions.DataStoreError;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class UserTest {
    private DataStore dataStore;

    @Before
    public void init() {
        dataStore = new DataStore();
    }
    @Test
    public void loadUserTest() {
        User us = dataStore.loadUserData("9512221234567t");
        //No Korean.
        assertEquals(us.getUserName(), "DoHyeon");
        assertEquals(us.getUserId(), "9512221234567");
    }

    @Test
    public void removeCardRemoveCardFromList() {
        User us = dataStore.loadUserData("9512221234567t");
            assertEquals(us.getCardList().length, 2);

        try {
            us.removeCard("1234567890");
        } catch (DataStoreError e) {
            fail("throw DataStoreError: " + e.getMessage());
        }

        for(int i = 0 ; i < us.getCardList().length ; i++){
            assertNotEquals("1234567890", us.getCardList()[i]);
        }
    }
}
