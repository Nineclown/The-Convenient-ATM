import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class ATMSystem {
    private int cashAmount;
    private int selectedCardNumber;
    private int[] billAmount;

    private Account account;

    private ArrayList<Admin> admins;
    private Admin currentAdmin;

    private Transaction fromTransaction;
    private Transaction toTransaction;
    private ArrayList<Transaction> splitToTransaction;


    private transient DataStore dataStore;

    public int getCashAmount() {
        return this.cashAmount;
    }

    public Account getAccount() {
        return this.account;
    }

    public int getSelectedCardNumber() {
        return this.selectedCardNumber;
    }

    public Transaction[] getSplitToTransaction() {
        return this.splitToTransaction.stream().toArray(Transaction[]::new);
    }

    public void setCashAmount(int value) {
        this.cashAmount = value;
    }

    public Admin[] getAdmins() {
        return this.admins.stream().toArray(Admin[]::new);
    }

    public int[] getBillAmount() {
        return this.billAmount;
    }

    public ATMSystem() {
        this.cashAmount = 0;
        this.selectedCardNumber = 0;
        this.dataStore = new DataStore();
        this.admins = new ArrayList<Admin>();
    }

    public void selectFunction(FunctionType function) throws NoneOfFunctionSelected {
        switch (function) {
            case Deposit:
                this.toTransaction = new Transaction(TransactionType.Deposit);
                break;
            case Withdraw:
                this.toTransaction = new Transaction(TransactionType.Withdraw);
                break;
            case ForeignDeposit:
                this.toTransaction = new Transaction(TransactionType.ForeignDeposit);
                break;
            case ForeignWithdraw:
                this.toTransaction = new Transaction(TransactionType.ForeignWithdraw);
                break;
            case Transfer:
                this.fromTransaction = new Transaction(TransactionType.SendTransfer);
                this.toTransaction = new Transaction(TransactionType.ReceiveTransfer);
                break;
            case SplitPay:
                this.toTransaction = new Transaction(TransactionType.ReceiveTransfer);
                this.splitToTransaction = new ArrayList<Transaction>();
                break;
            case QueryBalance:
                break;
            case QueryTransactionList:
                break;
            case GetLotteryPrize:
                break;
            case ReportLostCard:
                break;
            case ChangeLocale:
                break;
            // Admin only functions
            case AddAdmin:
                break;
            case RemoveAdmin:
                break;
            case ToggleATMState:
                break;
            case QueryATMBalance:
                break;
            default:
                throw new NoneOfFunctionSelected();
        }
    }

    public void enterAccountInfo(Bank bank, String accountNo) throws AccountDoesNotExist, DataStoreError {
        this.account = dataStore.loadAccountData(bank, accountNo);

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }

        if (this.fromTransaction != null) {
            this.fromTransaction.setAccount(account);
        }

        if (this.toTransaction != null) {
            this.toTransaction.setAccount(account);

            if ( this.toTransaction.getAmount() > 0 ) {
                // Get Lottery Prize
                this.toTransaction.processTransaction();
            }
        }
    }

    public void enterBill(int[] billAmount) throws InvalidBillException, DataStoreError {
        int total = 0;

        if (billAmount.length != BillType.wonSize) {
            throw new InvalidBillException();
        }

        total += BillType.count(BillType.Thousand, billAmount[0]);
        total += BillType.count(BillType.FiveThousand, billAmount[1]);
        total += BillType.count(BillType.TenThousand, billAmount[2]);
        total += BillType.count(BillType.FiftyThousand, billAmount[3]);

        if (this.fromTransaction != null) {
            this.fromTransaction.setAmount(total);
            this.fromTransaction.processTransaction();
        }

        if (this.toTransaction != null) {
            this.toTransaction.setAmount(total);
            this.toTransaction.processTransaction();
        }
    }

    public void enterBillAsDollar(int[] billAmount) throws InvalidBillException, DataStoreError {
        int totalDollar = 0;

        if (billAmount.length != BillType.dollarSize) {
            throw new InvalidBillException();
        }

        totalDollar += BillType.count(BillType.DollarOne, billAmount[0]);
        totalDollar += BillType.count(BillType.DollarTwo, billAmount[1]);
        totalDollar += BillType.count(BillType.DollarTen, billAmount[2]);
        totalDollar += BillType.count(BillType.DollarFifty, billAmount[3]);
        totalDollar += BillType.count(BillType.DollarHundred, billAmount[4]);

        this.cashAmount += (int) (this.getCurrency() * totalDollar);
        totalDollar += (int) (this.getCurrency() * totalDollar);

        if (this.fromTransaction != null) {
            this.fromTransaction.setAmount(totalDollar);
            this.fromTransaction.processTransaction();
        }

        if (this.toTransaction != null) {
            this.toTransaction.setAmount(totalDollar);
            this.toTransaction.processTransaction();
        }
    }

    public void enterPassword(int password) throws InvalidPasswordException, AccountDoesNotExist {
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
    }


    public void enterBillAmountToWithdraw(int cashAmount) {
        this.cashAmount = cashAmount;
        billAmount = calcBillAmount(this.cashAmount, "WON");
    }

    public void enterBillAmountToWithdrawAsDollar(int cashAmount) {
        this.cashAmount = cashAmount;
        billAmount = calcBillAmount(this.cashAmount, "Dollar");

    }

    public int[] calcBillAmount(int cashAmount, String cashType) {

        int[] bill= new int[] {0,0,0,0};
        int[] billType;
        int length = bill.length;

        if (cashType.equals(("WON"))) {
            billType = new int[]{50000, 10000};
            length = billType.length;
            for (int i = 0; i < length; i++) {
                bill[length - (i + 1)] = cashAmount / billType[i];
                cashAmount -= billType[i] * bill[length - (i + 1)];
            }
        } else if (cashType.equals("Dollar")) {
            billType = new int[]{
                100, 50, 20, 10
            };
            length = billType.length;
            for (int i = 0; i < length; i++) {
                bill[length - (i + 1)] = cashAmount / billType[i];
                cashAmount -= billType[i] * bill[length - (i + 1)];
            }
        } else {
            return null;
        }
        return bill;
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

    public void enterCashAmountToTransfer(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void enterTotalCashAmountToGet(int cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void enterNumberOfUsers(int userNumber) throws TooFewUser, TooManyUsers {
        int amountPerUser;
        Transaction transaction;

        if (userNumber == 0) {
            throw new TooFewUser();
        }

        if (userNumber > this.cashAmount) {
            throw new TooManyUsers();
        }

        amountPerUser = this.cashAmount / userNumber;

        for (int i = 0; i < userNumber; i++) {
            transaction = new Transaction(TransactionType.Withdraw);
            transaction.setAmount(amountPerUser);
            this.splitToTransaction.add(transaction);
        }
    }

    public void enterPeriodToQuery(Date start, Date end) throws AccountDoesNotExist {
        Transaction[] transactions;

        if (this.account == null) {
            throw new AccountDoesNotExist();
        }

        transactions = this.account.getTransactions(start, end);
    }

    public void enterUserId(String userId) {

    }

    public void selectCard(String cardNumber) {
        this.selectedCardNumber = Integer.parseInt(cardNumber);
    }

    public void requestStopCard(String cardNumber) {
        this.selectedCardNumber = Integer.parseInt(cardNumber);
    }

    public void askRenewCard(boolean answer) {
        if (answer) {

        } else {

        }
    }

    public void requestRenewCard(boolean answer) {
        if (answer) {

        } else {

        }
    }

    public void enterLottery(Lottery lottery) throws LotteryFailed {
        int result = lottery.checkResult();

        if (result == 0) {
            throw new LotteryFailed();
        }

        this.toTransaction = new Transaction(TransactionType.Deposit);
        this.toTransaction.setAmount(result);
    }

    public void enterAdminInfo(String adminPw, String contact) {
        Admin newAdmin = new Admin(this.createAdminId(), adminPw, contact);

        this.admins.add(newAdmin);
    }

    public String createAdminId() {
        if (this.admins.size() == 0) {
            return "0";
        }
        return MessageFormat.format("{0}",
            Integer.parseInt(this.admins.get(this.admins.size() - 1).getId() + 1));
    }

    public int enterATMBalance(int[] billAmount) throws InvalidBillException {
        int balance = 0;
        double currency = this.getCurrency();

        if (billAmount.length != (BillType.wonSize + BillType.dollarSize)) {
            throw new InvalidBillException();
        }

        balance += BillType.count(BillType.Thousand, billAmount[0]);
        balance += BillType.count(BillType.FiveThousand, billAmount[1]);
        balance += BillType.count(BillType.TenThousand, billAmount[2]);
        balance += BillType.count(BillType.FiftyThousand, billAmount[3]);
        balance += BillType.dollarCount(BillType.DollarOne, billAmount[4]);
        balance += BillType.dollarCount(BillType.DollarTwo, billAmount[5]);
        balance += BillType.dollarCount(BillType.DollarTen, billAmount[6]);
        balance += BillType.dollarCount(BillType.DollarFifty, billAmount[7]);
        balance += BillType.dollarCount(BillType.DollarHundred, billAmount[8]);

        return balance;
    }
}
