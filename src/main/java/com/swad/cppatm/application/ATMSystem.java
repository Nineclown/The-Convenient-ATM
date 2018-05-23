package com.swad.cppatm.application;

import com.swad.cppatm.enums.*;
import com.swad.cppatm.exceptions.*;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ATMSystem {
    private int cashAmount;
    private int numberUser;
    private String selectedCardNumber;

    private Account account;

    private ArrayList<Admin> admins;
    private Admin currentAdmin;
    private User user;
    private String[] cardList;
    private Transaction fromTransaction;
    private Transaction toTransaction;
    private ArrayList<Transaction> transactionList;

    private SystemState state;
    private SystemBalance balance;

    private FunctionType function;

    public int getCashAmount() {
        return this.cashAmount;
    }

    public int getNumberUser() {
        return this.numberUser;
    }

    public Account getAccount() {
        return this.account;
    }

    public String getSelectedCardNumber() {
        return this.selectedCardNumber;
    }

    public Transaction getFromTransaction() {
        return this.fromTransaction;
    }

    public Transaction getToTransaction() {
        return this.toTransaction;
    }

    public Transaction[] getTransactionList() {
        return this.transactionList.stream().toArray(Transaction[]::new);
    }

    public FunctionType getFunction() {
        return this.function;
    }

    public SystemState getState() {
        return this.state;
    }

    public SystemBalance getBalance() {
        return this.balance;
    }

    public void setCashAmount(int value) {
        this.cashAmount = value;
    }

    public Admin[] getAdmins() {
        return this.admins.stream().toArray(Admin[]::new);
    }

    public Admin getCurrentAdmin() {
        return this.currentAdmin;
    }

    public ATMSystem() {
        this.cashAmount = 0;
        this.selectedCardNumber = "";
        DataStore dataStore = new DataStore();
        this.admins = dataStore.loadAdminData();
        this.user = new User();
        this.state = new SystemState();
        this.balance = new SystemBalance();
    }

    public void selectFunction(FunctionType function) throws NoneOfFunctionSelected {
        this.function = function;
        switch (function) {
            case Deposit:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.toTransaction = new Transaction(TransactionType.Deposit);
                break;
            case Withdraw:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.toTransaction = new Transaction(TransactionType.Withdraw);
                break;
            case ForeignDeposit:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.toTransaction = new Transaction(TransactionType.ForeignDeposit);
                break;
            case ForeignWithdraw:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.toTransaction = new Transaction(TransactionType.ForeignWithdraw);
                break;
            case Transfer:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.fromTransaction = new Transaction(TransactionType.SendTransfer);
                this.toTransaction = new Transaction(TransactionType.ReceiveTransfer);
                break;
            case SplitPay:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                this.fromTransaction = new Transaction(TransactionType.SendTransfer);
                this.toTransaction = new Transaction(TransactionType.ReceiveTransfer);
                break;
            case QueryBalance:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                break;
            case QueryTransactionList:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                break;
            case GetLotteryPrize:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                break;
            case ReportLostCard:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                break;
            case ChangeLocale:
                if (!state.available()) {
                    throw new NoneOfFunctionSelected();
                }
                break;
            // Admin only functions
            case AddAdmin:
                break;
            case RemoveAdmin:
                if (this.currentAdmin == null) {
                    throw new NoneOfFunctionSelected();
                }

                if (this.admins.size() <= 1) {
                    throw new NoneOfFunctionSelected();
                }

                this.admins.remove(this.currentAdmin);
                try {
                    DataStore dataStore = new DataStore();
                    dataStore.saveAdminData(admins);
                } catch (DataStoreError er) {

                }
                break;
            case ToggleATMState:
                this.state.toggleSystem();
                break;
            case QueryATMBalance:
                break;
            case ChangeATMBalance:
                break;
            default:
                throw new NoneOfFunctionSelected();
        }
    }

    public void enterAccountInfo(Bank bank, String accountNo) throws AccountDoesNotExist, DataStoreError, NoneOfFunctionSelected {
        DataStore dataStore = new DataStore();
        this.account = dataStore.loadAccountData(bank, accountNo);

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }

        if (this.function == null) {
            throw new NoneOfFunctionSelected();
        }

        switch (this.function) {
            case Deposit:
            case ForeignDeposit:
            case Withdraw:
            case ForeignWithdraw:
                this.toTransaction.setAccount(this.account);
                break;
            case GetLotteryPrize:
                this.toTransaction.setAccount(this.account);
                try {
                    this.toTransaction.processTransaction();
                } catch (NegativeBalanceError e) {

                }
                break;
            case Transfer:
                //Transfer enter Account from.
                if (this.fromTransaction.getAccount() == null) {
                    this.fromTransaction.setAccount(this.account);
                } else {
                    this.toTransaction.setAccount(this.account);
                }
                break;
            case SplitPay:
                if (this.toTransaction.getAccount() == null) {
                    this.toTransaction.setAccount(this.account);
                } else {
                    this.fromTransaction.setAccount(this.account);
                }
                break;
            case QueryBalance:
            case QueryTransactionList:
                break;
        }
    }

    public void enterBill(int[] billAmount) throws InvalidBillException, DataStoreError, OverflowBillException {
        int total = 0;

        if (billAmount.length != 11) {
            throw new InvalidBillException();
        } else if ((billAmount[4] != 0) || (billAmount[5] != 0) || (billAmount[6] != 0) && (billAmount[7] != 0) || (billAmount[8] != 0) || (billAmount[9] != 0) || (billAmount[10] != 0)) {
            throw new InvalidBillException();
        }

        total += BillType.count(BillType.Thousand, billAmount[0]);
        total += BillType.count(BillType.FiveThousand, billAmount[1]);
        total += BillType.count(BillType.TenThousand, billAmount[2]);
        total += BillType.count(BillType.FiftyThousand, billAmount[3]);

        //Dose Enter Bill Could not use toTransaction?
        if (this.toTransaction != null) {
            this.toTransaction.setAmount(total);
            try {
                this.toTransaction.processTransaction();
            } catch (NegativeBalanceError e) {

            }
        }
        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            //Alarm to Admin;
        }
    }

    public void enterBillAsDollar(int[] billAmount) throws InvalidBillException, DataStoreError, OverflowBillException {
        int totalDollar = 0;

        if (billAmount.length != 11) {
            throw new InvalidBillException();
        } else if ((billAmount[0] != 0) || (billAmount[1] != 0) || (billAmount[2] != 0) && (billAmount[3] != 0)) {
            throw new InvalidBillException();
        }

        totalDollar += BillType.dollarCount(BillType.DollarOne, billAmount[4]);
        totalDollar += BillType.dollarCount(BillType.DollarTwo, billAmount[5]);
        totalDollar += BillType.dollarCount(BillType.DollarFive, billAmount[6]);
        totalDollar += BillType.dollarCount(BillType.DollarTen, billAmount[7]);
        totalDollar += BillType.dollarCount(BillType.DollarTwenty, billAmount[8]);
        totalDollar += BillType.dollarCount(BillType.DollarFifty, billAmount[9]);
        totalDollar += BillType.dollarCount(BillType.DollarHundred, billAmount[10]);

        this.cashAmount = (int) (this.getCurrency() * totalDollar);
        System.out.print(this.fromTransaction);

        if (this.toTransaction != null) {
            this.toTransaction.setAmount(this.cashAmount);
            try {
                this.toTransaction.processTransaction();
            } catch (NegativeBalanceError e) {
            }
        }
        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            //Alarm to Admin
        }

    }

    public void enterPassword(int password) throws InvalidPasswordException, AccountDoesNotExist, DataStoreError, NegativeBalanceError {
        int retry = 0;
        final int maxRetry = 5;

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }


        for (; retry < maxRetry; retry++) {
            if (this.account.checkAccountPassword(password)) {
                break;
            }
        }

        if (retry == maxRetry) {
            this.account.freezeAccount();
            throw new InvalidPasswordException();
        }

        if (this.function == FunctionType.SplitPay) {
            numberUser--;
            try {
                this.fromTransaction.processTransaction();
                this.toTransaction.addAmount(cashAmount);
            } catch (NegativeBalanceError e) {
                throw e;
            }
            if (numberUser > 0) {
                this.fromTransaction = new Transaction(TransactionType.SendTransfer);
                this.fromTransaction.setAmount(-cashAmount);
            } else {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
                this.toTransaction.processTransaction();
            }


        }
    }


    public void enterBillAmountToWithdraw(int cashAmount) throws DataStoreError, NegativeBalanceError, OverflowBillException {
        this.cashAmount = -cashAmount;
        int[] billAmount = calcBillAmount(this.cashAmount, "WON");
        this.toTransaction.setAmount(cashAmount);
        this.toTransaction.processTransaction();
        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            //Alarm to admin;
        }
    }

    public void enterBillAmountToWithdrawAsDollar(int cashAmount) throws DataStoreError, NegativeBalanceError, OverflowBillException {
        this.cashAmount = -cashAmount;
        int[] billAmount = calcBillAmount(this.cashAmount, "Dollar");
        this.toTransaction.setAmount((cashAmount * (int) this.getCurrency()));
        this.toTransaction.processTransaction();
        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            //Alarm to admin;
        }
    }

    public int[] calcBillAmount(int cashAmount, String cashType) {

        int[] bills = new int[11];
        int[] billType;
        int length;

        if (cashType.equals(("WON"))) {
            billType = new int[]{50000, 10000};
            length = billType.length;
            for (int i = 0; i < length; i++) {
                int bill = cashAmount / billType[i];
                bills[3 - i] = bill;
                cashAmount -= billType[i] * bill;
            }
        } else if (cashType.equals("Dollar")) {
            billType = new int[]{
                100, 50, 20, 10, 5, 2, 1
            };
            length = billType.length;
            for (int i = 0; i < length; i++) {
                int bill = cashAmount / billType[i];
                bills[10 - i] = bill;
                cashAmount -= billType[i] * bill;
            }
        } else {
            return null;
        }
        return bills;
    }

    public double getCurrency() {
        double currency = 1000; //default currency

        String url = "https://free.currencyconverterapi.com/api/v5/convert?q=USD_KRW&compact=ultra";
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                currency = Double.parseDouble(inputLine.substring(inputLine.indexOf(':') + 1, inputLine.length() - 1));
            }
        } catch (Exception e) {

        }
        return currency;
    }

    public void enterCashAmountToTransfer(int cashAmount) throws DataStoreError, NegativeBalanceError {
        this.cashAmount = cashAmount;
        this.fromTransaction.setAmount(-cashAmount);
        this.toTransaction.setAmount(cashAmount);
        this.fromTransaction.processTransaction();
        this.toTransaction.processTransaction();
    }

    public void enterTotalCashAmountToGet(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void enterNumberOfUsers(int userNumber) throws TooFewUser, TooManyUsers {
        if (userNumber == 0) {
            throw new TooFewUser();
        }

        if (userNumber > this.cashAmount) {
            throw new TooManyUsers();
        }
        this.numberUser = userNumber;
        this.cashAmount = this.cashAmount / userNumber;
        this.fromTransaction = new Transaction(TransactionType.SendTransfer);
        fromTransaction.setAmount(-this.cashAmount);
    }

    public void enterPeriodToQuery(Date start, Date end) throws AccountDoesNotExist {
        if (account == null) {
            throw new AccountDoesNotExist();
        }

        transactionList = new ArrayList<Transaction>(Arrays.asList(account.getTransactions(start, end)));
    }

    public void enterUserId(String userId) throws UserDoestNotExist {
        DataStore dataStore = new DataStore();
        this.user = dataStore.loadUserData(userId);
        if(this.user == null)
        {
            throw new UserDoestNotExist();
        }
        this.cardList = this.user.getCardList();
    }

    public void selectCard(String cardNumber) {
        this.selectedCardNumber = cardNumber;
        this.requestStopCard(cardNumber);
    }

    public void requestStopCard(String cardNumber) {
        //카드를 중지했다는 메세지가 뜨게 함
    }

    public void askRenewCard(boolean answer) throws DataStoreError {
        if (answer) {
            requestRenewCard(selectedCardNumber);
        }
    }

    public void requestRenewCard(String cardNumber) {
        // 재발급 신청이 완료되었다는 메시지 띄워주기
    }

    public void changeLocale(Locale locale) {
        this.state.changeSystemLocale(locale);
    }

    public void enterLottery(Lottery lottery) throws LotteryFailed {
        int result = lottery.checkResult();

        if (result == 0) {
            throw new LotteryFailed();
        }

        this.toTransaction = new Transaction(TransactionType.Deposit);
        this.toTransaction.setAmount(result);
    }

    public void authorizeAdmin(String adminId, String adminPw) throws InvalidAdminException {
        for (int i = 0; i < this.admins.size(); i++) {
            if (this.admins.get(i).checkAdminAccount(adminId, adminPw)) {
                this.currentAdmin = this.admins.get(i);
                return;
            }
        }

        throw new InvalidAdminException();
    }

    public void enterAdminInfo(String adminPw, String contact) throws DataStoreError {
        DataStore dataStore = new DataStore();
        Admin newAdmin = new Admin(this.createAdminId(), adminPw, contact);

        admins.add(newAdmin);
        dataStore.saveAdminData(admins);
    }

    public String createAdminId() {
        if (this.admins.size() == 0) {
            return "1";
        }

        return MessageFormat.format("{0}",
            Integer.parseInt(this.admins.get(this.admins.size() - 1).getId()) + 1);
    }

    public void enterATMBalance(int[] billAmount) throws InvalidBillException, OverflowBillException {
        if (billAmount.length != (BillType.wonSize + BillType.dollarSize)) {
            throw new InvalidBillException();
        }

        this.balance.setATMBalance(billAmount);
    }

    public String[] getCardList()
    {
        return this.cardList;
    }
}
