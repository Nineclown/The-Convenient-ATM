package com.swad.cppatm.application;

import com.swad.cppatm.enums.*;
import com.swad.cppatm.exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class ATMSystem {
    private int cashAmount;
    private int numberOfUser;
    private int retry;
    private String selectedCardNumber;

    private Account account;

    private Transaction fromTransaction;
    private Transaction toTransaction;
    private ArrayList<Transaction> transactionList;

    private ArrayList<Admin> admins;
    private Admin currentAdmin;

    private User user;

    private SystemState state;
    private SystemBalance balance;

    private FunctionType function;

    public int getCashAmount() {
        return this.cashAmount;
    }

    public int getNumberUser() {
        return this.numberOfUser;
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

    public User getUser() {
        return this.user;
    }

    public void removeFunctionSelection() {
        this.function = null;
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

    public void selectFunction(FunctionType function) throws NoneOfFunctionSelected, MultipleFunctionsExecuted {
        if(!state.available() && Arrays.asList(FunctionType.getUserFunctions()).contains(function)){
            throw new NoneOfFunctionSelected();
        }

        // 중복 실행 불가!
        if (this.function != null) {
            throw new MultipleFunctionsExecuted();
        }

        switch (function) {
            case DEPOSIT:
                this.toTransaction = new Transaction(TransactionType.DEPOSIT);
                break;
            case WITHDRAW:
                this.toTransaction = new Transaction(TransactionType.WITHDRAW);
                break;
            case FOREIGN_DEPOSIT:
                this.toTransaction = new Transaction(TransactionType.FOREIGN_DEPOSIT);
                break;
            case FOREIGN_WITHDRAW:
                this.toTransaction = new Transaction(TransactionType.FOREIGN_WITHDRAW);
                break;
            case TRANSFER:
            case SPLIT_PAY:
                this.fromTransaction = new Transaction(TransactionType.SEND_TRANSFER);
                this.toTransaction = new Transaction(TransactionType.RECEIVE_TRANSFER);
                break;
            case QUERY_BALANCE:
                break;
            case QUERY_TRANSACTION_LIST:
                break;
            case GET_LOTTERY_PRIZE:
                break;
            case REPORT_LOST_CARD:
                break;
            case CHANGE_LOCALE:
                break;
            // Admin only functions
            case ADD_ADMIN:
                break;
            case REMOVE_ADMIN:
                if (this.admins.size() <= 1) {
                    throw new NoneOfFunctionSelected();
                }

                this.admins.remove(this.currentAdmin);
                try {
                    DataStore dataStore = new DataStore();
                    dataStore.saveAdminData(admins);
                } catch (DataStoreError er) {
                    //DataStroeError, File System Error.
                }
                break;
            case TOGGLE_ATM_STATE:
                this.state.toggleSystem();
                break;
            case QUERY_ATM_BALANCE:
                break;
            case CHANGE_ATM_BALANCE:
                break;
            default:
                throw new NoneOfFunctionSelected();
        }

        this.function = function;
    }

    public void enterAccountInfo(Bank bank, String accountNo) throws AccountDoesNotExist, DataStoreError, FrozenAccountException, NoneOfFunctionSelected, SameAccountTransferException {
        DataStore dataStore = new DataStore();
        this.account = dataStore.loadAccountData(bank, accountNo);
        retry = 0;

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }

        if (!this.account.isAccountEnabled()) {
            throw new FrozenAccountException();
        }

        if (this.function == null) {
            throw new NoneOfFunctionSelected();
        }

        switch (this.function) {
            case DEPOSIT:
            case FOREIGN_DEPOSIT:
            case WITHDRAW:
            case FOREIGN_WITHDRAW:
                this.toTransaction.setAccount(this.account);
                break;
            case GET_LOTTERY_PRIZE:
                this.toTransaction.setAccount(this.account);
                try {
                    this.toTransaction.processTransaction();
                } catch (NegativeBalanceError e) {
                    //GET_LOTTERY_PRIZE Don't make Negative balance Error.
                }
                break;
            case TRANSFER:
                //TRANSFER enter Account from.
                if (this.fromTransaction.getAccount() == null) {
                    this.fromTransaction.setAccount(this.account);
                } else if (this.fromTransaction.getAccount().getAccountNo().equals(this.account.getAccountNo())) {
                    throw new SameAccountTransferException();
                } else {
                    this.toTransaction.setAccount(this.account);
                }
                break;
            case SPLIT_PAY:
                if (this.toTransaction.getAccount() == null) {
                    this.toTransaction.setAccount(this.account);
                } else {
                    this.fromTransaction.setAccount(this.account);
                }
                break;
            case QUERY_BALANCE:
            case QUERY_TRANSACTION_LIST:
                break;
            default :
                //Do Nothing.
                break;
        }
    }

    public void enterBill(int[] billAmount) throws InvalidBillException, DataStoreError, OverflowBillException {
        int total = 0;

        if ((billAmount.length != 11) || ((billAmount[4] != 0) ||
            (billAmount[5] != 0) || (billAmount[6] != 0) && (billAmount[7] != 0) || (billAmount[8] != 0) || (billAmount[9] != 0) || (billAmount[10] != 0))) {
            throw new InvalidBillException();
        }

        total += BillType.count(BillType.THOUSAND, billAmount[0]);
        total += BillType.count(BillType.FIVE_THOUSAND, billAmount[1]);
        total += BillType.count(BillType.TEN_THOUSAND, billAmount[2]);
        total += BillType.count(BillType.FIFTY_THOUSAND, billAmount[3]);


        this.toTransaction.setAmount(total);

        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            System.out.println("Alter to admin");
            //Alarm to Admin;
        } catch (OverflowBillException e) {
            System.out.print("Export Bills : ");
            for (int i = 0; i < billAmount.length; i++) {
                System.out.print(-billAmount[i] + "/");
            }
            System.out.println();
            state.toggleSystem();
            throw e;
        }

        try {
            this.toTransaction.processTransaction();
        } catch (NegativeBalanceError e) {
            //Insert Money Could not reach NegativeBalanceError.
        }

    }

    public void enterBillAsDollar(int[] billAmount) throws InvalidBillException, DataStoreError, OverflowBillException {
        int totalDollar = 0;

        if ((billAmount.length != 11) || ((billAmount[0] != 0) || (billAmount[1] != 0) || (billAmount[2] != 0) && (billAmount[3] != 0)))
        {
            throw new InvalidBillException();
        }

        totalDollar += BillType.dollarCount(BillType.DOLLAR_ONE, billAmount[4]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_TWO, billAmount[5]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_FIVE, billAmount[6]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_TEN, billAmount[7]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_TWENTY, billAmount[8]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_FIFTY, billAmount[9]);
        totalDollar += BillType.dollarCount(BillType.DOLLAR_HUNDRED, billAmount[10]);

        this.cashAmount = (int) (this.getCurrency() * totalDollar);
        System.out.print(this.fromTransaction);


        this.toTransaction.setAmount(this.cashAmount);

        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            System.out.println("Alter to admin");
            //Alarm to Admin
        } catch (OverflowBillException e) {
            System.out.print("Export Bills : ");
            for (int i = 0; i < billAmount.length; i++) {
                System.out.print(-billAmount[i] + "/");
            }
            System.out.println();
            state.toggleSystem();
            throw e;
        }

        try {
            this.toTransaction.processTransaction();
        } catch (NegativeBalanceError e) {
            //Insert Money Could not reach NegativeBalanceError.
        }
    }

    public void enterPassword(int password) throws InvalidPasswordException, AccountDoesNotExist, DataStoreError, NegativeBalanceError, FrozenAccountException {
        final int maxRetry = 5;

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }

        if (!this.account.checkAccountPassword(password)) {
            retry++;
            if (retry >= maxRetry) {
                this.account.freezeAccount();
                throw new FrozenAccountException();
            }
            throw new InvalidPasswordException();
        }

        //Split Pat Process Transaction After enter Password.
        if (this.function == FunctionType.SPLIT_PAY) {
            numberOfUser--;
            try {
                this.fromTransaction.processTransaction();
                this.toTransaction.addAmount(cashAmount);
            } finally {
                if (numberOfUser > 0) {
                    this.fromTransaction = new Transaction(TransactionType.SEND_TRANSFER);
                    this.fromTransaction.setAmount(-cashAmount);
                } else {
                    this.toTransaction.processTransaction();
                }
            }

        }
    }


    public void enterBillAmountToWithdraw(int cashAmount) throws DataStoreError, NegativeBalanceError, OverflowBillException {
        this.cashAmount = cashAmount;
        int[] billAmount = calcBillAmount(this.cashAmount, "WON");
        this.toTransaction.setAmount(cashAmount);
        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            System.out.println("Alter to admin");
            //Alarm to admin;
        } catch (OverflowBillException e) {
            state.toggleSystem();
            throw e;
        }

        try {
            this.toTransaction.processTransaction();
        } catch (NegativeBalanceError e) {
            for (int i = 0; i < billAmount.length; i++) {
                billAmount[i] = -billAmount[i];
            }
            try {
                balance.changeSystemBalance(billAmount);
            } catch (AdminAlarmException | OverflowBillException e1) {
                //Cancel Transaction, Ignore Exception.
            }

            throw e;
        }

        System.out.print("Export Bills : ");
        for (int i = 0; i < billAmount.length; i++) {
            System.out.print(-billAmount[i] + "/");
        }
        System.out.println();
    }

    public void enterBillAmountToWithdrawAsDollar(int cashAmount) throws DataStoreError, NegativeBalanceError, OverflowBillException {
        this.cashAmount = cashAmount;
        int[] billAmount = calcBillAmount(this.cashAmount, "Dollar");
        this.toTransaction.setAmount((cashAmount * (int) this.getCurrency()));

        try {
            balance.changeSystemBalance(billAmount);
        } catch (AdminAlarmException e) {
            System.out.println("Alter to admin");
            //Alarm to admin;
        } catch (OverflowBillException e) {
            state.toggleSystem();
            throw e;
        }

        try {
            this.toTransaction.processTransaction();
        } catch (NegativeBalanceError e) {
            for (int i = 0; i < billAmount.length; i++) {
                billAmount[i] = -billAmount[i];
            }
            try {
                balance.changeSystemBalance(billAmount);
            } catch (AdminAlarmException | OverflowBillException e1) {
                //Cancel Transaction, Ignore Exception.
            }

            throw e;
        }

        System.out.print("Export Bills : ");
        for (int bills : billAmount) {
            System.out.print(-bills + "/");
        }
        System.out.println();
    }

    public int[] calcBillAmount(int cashAmount, String cashType) {

        int[] bills = new int[11];
        int[] billType;
        int length;

        switch (cashType) {
            case "WON":
                billType = new int[]{50000, 10000};
                length = billType.length;
                for (int i = 0; i < length; i++) {
                    int bill = cashAmount / billType[i];
                    bills[3 - i] = bill;
                    cashAmount -= billType[i] * bill;
                }
                break;
            case "Dollar":
                billType = new int[]{100, 50, 20, 10, 5, 2, 1};
                length = billType.length;
                for (int i = 0; i < length; i++) {
                    int bill = cashAmount / billType[i];
                    bills[10 - i] = bill;
                    cashAmount -= billType[i] * bill;
                }
                break;
            default:
                return new int[]{0,0,0,0,0,0,0,0,0,0,0};
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
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                currency = Double.parseDouble(inputLine.substring(inputLine.indexOf(':') + 1, inputLine.length() - 1));
            }
        } catch (IOException e) {
            //IOException
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

    public void enterNumberOfUsers(int userNumber) throws TooFewUser {
        if (userNumber == 0) {
            throw new TooFewUser();
        }

        this.numberOfUser = userNumber;
        this.cashAmount = this.cashAmount / userNumber;
        this.fromTransaction = new Transaction(TransactionType.SEND_TRANSFER);
        fromTransaction.setAmount(-this.cashAmount);
    }

    public void enterPeriodToQuery(Date start, Date end) throws AccountDoesNotExist {
        if (account == null) {
            throw new AccountDoesNotExist();
        }

        transactionList = account.getTransactions(start, end);
    }

    public void enterUserId(String userId) throws UserDoestNotExist {
        DataStore dataStore = new DataStore();
        this.user = dataStore.loadUserData(userId);
        if (this.user == null) {
            throw new UserDoestNotExist();
        }
    }

    public void selectCard(String cardNumber) {
        this.selectedCardNumber = cardNumber;
        this.requestStopCard(cardNumber);
    }

    public void requestStopCard(String cardNumber) {
        System.out.println(cardNumber + "카드를 중지처리하였습니다");
        try {
            this.user.removeCard(cardNumber);
        } catch (DataStoreError e) {
            //Ignore Error.
        }
    }

    public void askRenewCard(boolean answer) throws DataStoreError {
        if (answer) {
            requestRenewCard(selectedCardNumber);
        }
    }

    public void requestRenewCard(String cardNumber) {
        System.out.println(cardNumber + "카드 재발급을 신청하였습니다");
    }

    public void changeLocale(Locale locale) {
        this.state.changeSystemLocale(locale);
    }

    public void enterLottery(Lottery lottery) throws LotteryFailed {
        int result = lottery.checkResult();

        if (result == 0) {
            throw new LotteryFailed();
        }

        this.toTransaction = new Transaction(TransactionType.DEPOSIT);
        this.toTransaction.setAmount(result);
    }

    public void authorizeAdmin(String adminId, String adminPw) throws InvalidAdminException {
        for (Admin admin : this.admins) {
            if (admin.checkAdminAccount(adminId, adminPw)) {
                this.currentAdmin = admin;
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
        if (billAmount.length != (BillType.WON_SIZE + BillType.DOLLAR_SIZE)) {
            throw new InvalidBillException();
        }

        this.balance.setATMBalance(billAmount);
    }
}
