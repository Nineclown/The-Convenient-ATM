package com.swad.cppatm.enums;

public enum FunctionType {
    Deposit,
    Withdraw,
    ForeignDeposit,
    ForeignWithdraw,
    Transfer,
    SplitPay,
    QueryTransactionList,
    QueryBalance,
    GetLotteryPrize,
    ReportLostCard,
    ChangeLocale,
    // Admin only functions
    AddAdmin,
    RemoveAdmin,
    QueryATMBalance,
    ToggleATMState,
    ChangeATMBalance;

    public static FunctionType[] getUserFunctions() {
        return new FunctionType[]{ Deposit, Withdraw, ForeignDeposit, ForeignWithdraw, Transfer, SplitPay,
            QueryTransactionList, QueryBalance, GetLotteryPrize, ReportLostCard, ChangeLocale };
    }

    public static FunctionType[] getAdminFunctions() {
        return new FunctionType[]{ AddAdmin, RemoveAdmin, QueryATMBalance, ToggleATMState, ChangeATMBalance };
    }
}
