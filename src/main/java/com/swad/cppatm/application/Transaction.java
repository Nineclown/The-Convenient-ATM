package com.swad.cppatm.application;

import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.TransactionType;
import com.swad.cppatm.exceptions.DataStoreError;
import com.swad.cppatm.exceptions.NegativeBalanceError;

import java.util.Date;

public class Transaction {
    private TransactionType tcType;
    private transient Account account;
    private int amount;
    private Date time;

    public Transaction(TransactionType type) {
        this.tcType = type;
    }

    // Getter

    public Date getTime() {
        return this.time;
    }

    public int getAmount() {
        return this.amount;
    }

    public Account getAccount() {
        return this.account;
    }

    // Setter
    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(int cashAmount) {
        this.amount = cashAmount;
    }

    public void addAmount(int cashAmount) {
        this.amount += cashAmount;
    }

    public void setTime() {
        this.time = new Date();
    }

    public void processTransaction() throws DataStoreError, NegativeBalanceError {
        this.calcFee();
        this.setTime();
        this.account.changeBalance(amount);
        this.account.addTransaction(this);
        this.account.saveAccount();
    }

    public void calcFee() {
        int fee;
        if(this.tcType == TransactionType.ReceiveTransfer){
            return;
        }

        if (this.account.getBank() == Bank.HANA) {
            if (this.tcType == TransactionType.Deposit) {
                fee = 0;
            } else {
                fee = 500;
            }
        } else {
            fee = 1000;
        }

        this.amount -= fee;
    }
}

