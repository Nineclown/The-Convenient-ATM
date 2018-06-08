package com.swad.cppatm;

import com.swad.cppatm.application.Account;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.application.User;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.exceptions.DataStoreError;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class DataStoreTest {

    public DataStore dataStore;

    @Before
    public void init() {
        dataStore = new DataStore();
    }


    @Test
    public void loadAccountDataReturnNullWhenAccountIsInvalid() {
        Account account = dataStore.loadAccountData(Bank.HANA, "1234567890");
        assertNull(account);
    }

    @Test
    public void saveAccountDataCreatesFile() throws DataStoreError {
        Account account = new Account(Bank.KOOKMIN, "1010101010101010");
        account.saveAccount();

        File file = new File("data/" + account.getBank() + "/" + account.getAccountNo() + ".json");
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    public void loadUserDataReturnNullWhenUserIsInvalid() {
        User user = new DataStore().loadUserData("1234567890");
        assertNull(user);
    }
}
