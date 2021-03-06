package com.swad.cppatm;

import com.swad.cppatm.application.Account;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.application.Transaction;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.TransactionType;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.NegativeBalanceError;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class AccountTest {
    private DataStore dataStore;

    @Before
    public void initDataStore() {
        this.dataStore = new DataStore();
    }

    @Test
    public void shouldGetCorrectAccount() {
        Account account = this.dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        assertEquals(account.getBalance(), 1004200);
        assertEquals(account.getPassword(), 5555);
    }

    @Test
    public void shouldGetCorrectBalance() {
        Account account = this.dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        try {
            account.changeBalance(5000);
        } catch (NegativeBalanceError e) {
            fail(e.getClass().getSimpleName());
        }
        assertEquals(account.getBalance(), 1009200);
    }

    @Test
    public void shouldGetCorrectTransactions() {
        Account account = this.dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        ArrayList<Transaction> transactions = account.getTransactions(new Date(118, 4, 19, 00, 0, 0), new Date(118, 4, 20, 0, 0, 0));
        assertEquals(transactions.get(0).getAmount(), 1400);
        assertEquals(transactions.get(0).getTime(), new Date(118, 4, 19, 15, 16, 59));
        assertEquals(transactions.size(), 1);
    }

    @Test
    public void shouldAddTransactionCorrectly() {
        Account account = new Account(Bank.KOOKMIN, "15151515141414");
        Date today = new Date();
        Date startToday = new Date(today.getYear(), today.getMonth(), today.getDate());
        Date endToday = new Date(today.getYear(), today.getMonth(), today.getDate()+1);

        Transaction transaction = new Transaction(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transaction.setAmount(5100);
        transaction.setTime();
        account.addTransaction(transaction);

        ArrayList<Transaction> transactions = account.getTransactions(startToday, endToday);
        assertEquals(transactions.get(0).getAmount(), 5100);
    }

    @Test
    public void checkPasswordTest() {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        assertTrue(!account.checkAccountPassword(1234));
        assertTrue(account.checkAccountPassword(5555));
    }

    @Test
    public void shouldFreezeAccountState() {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        assertTrue(account.getState());
        account.freezeAccount();
        assertFalse(account.getState());
        account.freezeAccount();
        assertFalse(account.getState());
    }

    @Test
    public void shouldChangeBalanceCorrectly() throws NegativeBalanceError {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        assertEquals(account.getBalance(), 1004200);
        account.changeBalance(10000000);
        assertEquals(account.getBalance(), 10000000+1004200);
        account.changeBalance(-5000000);
        assertEquals(account.getBalance(), 10000000+1004200-5000000);
    }

    @Test
    public void shouldSaveAccountCorrectly() throws NegativeBalanceError, DataStoreError {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        account.changeBalance(10000000);
        account.saveAccount();
        Account account2 = dataStore.loadAccountData(Bank.HANA, "123456789012345");
        assertEquals(account2.getBalance(), 10000000+1004200);
    }

    @After
    public void restoreFile() throws DataStoreError {
        Account account = dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        dataStore.saveAccountData(account);
    }
}
