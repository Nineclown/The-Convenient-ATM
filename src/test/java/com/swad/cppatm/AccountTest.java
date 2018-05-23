package com.swad.cppatm;

import com.swad.cppatm.application.Account;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.application.Transaction;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.TransactionType;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.NegativeBalanceError;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class AccountTest {
    private DataStore dataStore;
    private Account account;

    @Before
    public void initDataStore() {
        this.dataStore = new DataStore();
        this.account = new Account(Bank.HANA, "123456789012345");
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
        Transaction[] transactions = account.getTransactions(new Date(118, 4, 19, 12, 0, 0), new Date(118, 4, 20, 0, 0, 0));
        assertEquals(transactions[1].getAmount(), 1200);
        assertEquals(transactions[2].getTime(), new Date(118, 4, 16, 15, 16, 27));
        assertEquals(transactions.length, 4);
    }

    @Test
    public void shouldAddTransactionCorrectly() {
        Transaction transaction = new Transaction(TransactionType.Deposit);
        transaction.setAccount(new Account(Bank.KOOKMIN, "15151515141414"));
        transaction.setAmount(5100);
        transaction.setTime();
        Account account = this.dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        account.addTransaction(transaction);
        Transaction[] transactions = account.getTransactions(new Date(118, 4, 19, 12, 0, 0), new Date(118, 4, 30, 0, 0, 0));
        assertEquals(transactions[4].getAmount(), 5100);
    }

    @Test
    public void checkPasswordTest() {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        assertTrue(!account.checkAccountPassword(1234));
        assertTrue(account.checkAccountPassword(5555));
    }

    @Test
    public void shouldFreezeAccountState() {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345");
        assertTrue(account.getState());
        account.freezeAccount();
        assertFalse(account.getState());
        account.freezeAccount();
        assertFalse(account.getState());
    }

    @Test
    public void shouldChangeBalanceCorrectly() throws NegativeBalanceError {
        assertEquals(account.getBalance(), 0);
        account.changeBalance(10000000);
        assertEquals(account.getBalance(), 10000000);
        account.changeBalance(-5000000);
        assertEquals(account.getBalance(), 5000000);
    }

    @Test
    public void shouldSaveAccountCorrectly() throws NegativeBalanceError, DataStoreError {
        account.changeBalance(10000000);
        account.saveAccount();
        Account account2 = dataStore.loadAccountData(Bank.HANA, "123456789012345");
        assertEquals(account2.getBalance(), 10000000);
    }

    @After
    public void restoreFile() throws DataStoreError {
        account.setPassword(5555);
        dataStore.saveAccountData(account);
    }
}
