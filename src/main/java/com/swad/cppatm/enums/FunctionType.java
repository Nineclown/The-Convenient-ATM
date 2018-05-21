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
}
