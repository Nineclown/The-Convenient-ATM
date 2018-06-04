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

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class TransactionTest {
    private DataStore dataStore;

    @Before
    public void initDataStore() {
        this.dataStore = new DataStore();
    }

    @Test
    public void calcFeeTest() {
        Account account = new DataStore().loadAccountData(Bank.WOORI, "110412644105");
        Transaction transaction = new Transaction(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transaction.setAmount(10000);
        transaction.calcFee();
        assertEquals(transaction.getAmount(), 9000);
    }

    @Test
    public void processTransactionTest() {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        Transaction transaction = new Transaction(TransactionType.DEPOSIT);
        transaction.setAccount(account);
        transaction.setAmount(10000);
        try {
            transaction.processTransaction();
        } catch (Exception e) {
            fail("throw Exception");
        }

        assertEquals(account.getTransactions(new Date(118, 4, 20, 0, 0, 0), new Date(118, 4, 21, 23, 0, 0)).size(), 0);
        assertEquals(account.getBalance(), 1014200);
        assertTrue(transaction.getTime().after(new Date(118, 4, 20, 0, 0, 0)));
    }

    @Test(expected = NegativeBalanceError.class)
    public void processTransactionBalanceMustBePositive() throws NegativeBalanceError {
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345");
        Transaction transaction = new Transaction(TransactionType.WITHDRAW);

        transaction.setAccount(account);
        transaction.setAmount(-99999999);
        try {
            transaction.processTransaction();
        } catch (DataStoreError ex) {
            fail(ex.getClass().getSimpleName());
        }
    }

    @After
    public void restoreFile() throws DataStoreError {
        Account account = dataStore.loadAccountData(Bank.HANA, "123456789012345t");
        dataStore.saveAccountData(account);
    }
}
