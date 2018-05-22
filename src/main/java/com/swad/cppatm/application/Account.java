package com.swad.cppatm.application;

import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.NegativeBalanceError;

import java.util.ArrayList;
import java.util.Date;

/**
 * Account class
 */
public class Account {

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
        this.transactions = new ArrayList<Transaction>();
    }

    // Getter

    /**
     * Get Account's Balance
     *
     * @return Account's balance
     */
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

    /**
     * Change balance
     *
     * @param cashAmount amount of cash
     */
    public void changeBalance(int cashAmount) throws NegativeBalanceError {
        if ( (balance + cashAmount) < 0 ) {
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
    }

    /**
     * Get account's transaction history
     *
     * @param startDateTime Start date
     * @param endDateTime   End date
     * @return Transaction history
     */
    public Transaction[] getTransactions(Date startDateTime, Date endDateTime) {
        // 최근거래 50개까지만 출력
        Transaction[] list = new Transaction[transactions.size()];

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getTime().compareTo(startDateTime) > 0 ||
                transactions.get(i).getTime().compareTo(endDateTime) < 0) {
                list[i] = transactions.get(i);
            }
        }

        return list;
    }

    /**
     * Save account data to DataStore
     */
    public void saveAccount() throws DataStoreError {
        if(datastore == null){
            datastore = new DataStore();
        }

        try {
            datastore.saveAccountData(this);
        } catch (DataStoreError e) {
            throw e;
        }
    }

}
