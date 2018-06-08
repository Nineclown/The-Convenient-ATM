package com.swad.cppatm.application;

import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.NegativeBalanceError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Account class
 */
public class Account implements Serializable {

    private Bank bank;
    private String accountNumber;
    private int password;
    private int balance;
    private boolean state;
    private ArrayList<Transaction> transactions;
    private transient DataStore datastore = new DataStore();

    /**
     * Account constructor
     *
     * @param bank      Which bank that manages this account.
     * @param accountNo Account Number
     */
    public Account(Bank bank, String accountNo) {
        this.bank = bank;
        this.accountNumber = accountNo;
        this.password = 0;
        this.balance = 0;
        this.state = true;
        this.transactions = new ArrayList<>();
    }

    // Getter

    /**
     * Get Account's Balance
     *
     * @return Account's balance
     */
    public boolean getState() {
        return this.state;
    }

    public int getBalance() {
        return this.balance;
    }

    /**
     * Get Account's Bank
     *
     * @return Account's bank
     */
    public Bank getBank() {
        return this.bank;
    }

    /**
     * Get Account Number
     *
     * @return Account Number
     */
    public String getAccountNo() {
        return this.accountNumber;
    }

    /**
     * Get Account's password
     *
     * @return Account's password
     */
    public int getPassword() {
        return this.password;
    }

    /**
     * Check Account is frozen
     *
     * @return Account's state
     */
    public boolean isAccountEnabled() {
        return this.state;
    }

    // Setter

    public void setPassword(int password) {
        this.password = password;
    }

    public void unfreezeAccount() {
        this.state = true;
    }

    /**
     * Change balance
     *
     * @param cashAmount amount of cash
     */
    public void changeBalance(int cashAmount) throws NegativeBalanceError {
        if ((balance + cashAmount) < 0) {
            throw new NegativeBalanceError();
        }

        this.balance += cashAmount;
    }

    /**
     * Add transaction to account's transaction list
     *
     * @param transaction Account's most recent transaction
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Check User's password input is valid
     *
     * @param password User Input
     * @return Validity of Password
     */
    public boolean checkAccountPassword(int password) {
        return this.password == password;
    }

    /**
     * Freeze Account
     * If account is already frozen, it does do nothing.
     */
    public void freezeAccount() {
        if (!this.state) {
            return;
        }
        this.state = false;
        try {
            this.saveAccount();
        } catch (DataStoreError ignored) {

        }
    }

    /**
     * Get account's transaction history
     *
     * @param start Start date
     * @param end   End date
     * @return Transaction history
     */
    public ArrayList<Transaction> getTransactions(Date start, Date end) {
        ArrayList<Transaction> list = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getTime().compareTo(start) >= 0 && t.getTime().compareTo(end) < 0) {
                list.add(t);
        }
        }

        return list;
    }

    /**
     * Save account data to DataStore
     */
    public void saveAccount() throws DataStoreError {
        if (datastore == null) {
            datastore = new DataStore();
        }

        datastore.saveAccountData(this);
    }
}
