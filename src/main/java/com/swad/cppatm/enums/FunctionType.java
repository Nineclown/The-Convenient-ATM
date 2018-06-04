package com.swad.cppatm.enums;

public enum FunctionType {
    DEPOSIT,
    WITHDRAW,
    FOREIGN_DEPOSIT,
    FOREIGN_WITHDRAW,
    TRANSFER,
    SPLIT_PAY,
    QUERY_TRANSACTION_LIST,
    QUERY_BALANCE,
    GET_LOTTERY_PRIZE,
    REPORT_LOST_CARD,
    CHANGE_LOCALE,
    // Admin only functions
    ADD_ADMIN,
    REMOVE_ADMIN,
    QUERY_ATM_BALANCE,
    TOGGLE_ATM_STATE,
    CHANGE_ATM_BALANCE;

    public static FunctionType[] getUserFunctions() {
        return new FunctionType[]{DEPOSIT, WITHDRAW, FOREIGN_DEPOSIT, FOREIGN_WITHDRAW, TRANSFER, SPLIT_PAY,
            QUERY_TRANSACTION_LIST, QUERY_BALANCE, GET_LOTTERY_PRIZE, REPORT_LOST_CARD, CHANGE_LOCALE};
    }

    public static FunctionType[] getAdminFunctions() {
        return new FunctionType[]{ADD_ADMIN, REMOVE_ADMIN, QUERY_ATM_BALANCE, TOGGLE_ATM_STATE, CHANGE_ATM_BALANCE};
    }
}
