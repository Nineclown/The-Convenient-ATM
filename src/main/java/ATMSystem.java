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

    private Account account;

    private ArrayList<Admin> admins;

    private Transaction fromTransaction;
    private Transaction toTransaction;
    private ArrayList<Transaction> splitToTransaction;


    public int getCashAmount() {
        return this.cashAmount;
    }

    public void setCashAmount(int value) {
        this.cashAmount = value;
    }

    public ATMSystem() {
        this.cashAmount = 0;
        this.selectedCardNumber = 0;
        this.admins = new ArrayList<Admin>();
    }

    public void selectFunction(FunctionType function) {
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
                break;
            case QueryBalance:
                break;
            case QueryTransactionList:
                break;
            case AddAdmin:
                break;
            case ReportLostCard:
                break;
            case GetLotteryPrize:
                break;
            case RemoveAdmin:
                break;
            case ToggleATMState:
                break;
            case QueryATMBalance:
                break;
        }
    }

    public void enterAccountInfo(Bank bank, String accountNo) {

    }

    public void enterBill(int[] billAmount) throws InvalidBillException {
        int total = 0;

        if (billAmount.length != BillType.wonSize) {
            throw new InvalidBillException();
        }

        total += BillType.count(BillType.Thousand, billAmount[0]);
        total += BillType.count(BillType.FiveThousand, billAmount[1]);
        total += BillType.count(BillType.TenThousand, billAmount[2]);
        total += BillType.count(BillType.FiftyThousand, billAmount[3]);

        this.cashAmount += total;
    }

    public void enterBillAsDollar(int[] billAmount) throws InvalidBillException {
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
    }

    public void enterPassword(int password) {

    }

    public void enterBillAmountToWithdraw(int cashAmount) {
        this.cashAmount = cashAmount;
        calcBillAmount((this.cashAmount), "WON");


    }

    public void enterBillAmountToWithdrawAsDollar(int cashAmount) {
        this.cashAmount = cashAmount;
        calcBillAmount(this.cashAmount, "Dollar");

    }

    public int[] calcBillAmount(int cashAmount, String cashType) {

        int[] dollarBill;
        int[] wonBill;
        int[] billType;
        int length;

        if (cashType.equals(("WON"))) {
            wonBill = new int[2];
            billType = new int[]{50000, 10000};
            length = billType.length;
            for (int i = 0; i < length; i++) {
                wonBill[length - (i + 1)] = cashAmount / wonBill[i];
                cashAmount -= billType[i] * wonBill[length - (i + 1)];
            }
        } else if (cashType.equals("Dollar")) {
            dollarBill = new int[4];
            billType = new int[]{
                100, 50, 20, 10
            };
            length = billType.length;
            for (int i = 0; i < length; i++) {
                dollarBill[length - (i + 1)] = cashAmount / billType[i];
                cashAmount -= billType[i] * dollarBill[length - (i + 1)];
            }
        } else {
            return null;
        }
        return billType;
    }

    public double getCurrency() {
        double currency = 0;

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

    }

    public void enterTotalCashAmountToGet(int cashAmount) {

    }

    public void enterNumberOfUsers(int userNumber) {

    }

    public void enterPeriodToQuery(Date start, Date end) {

    }

    public void enterUserId(String userId) {

    }

    public void selectCard(String cardNumber) {

    }

    public void requestStopCard(String cardNumber) {

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

    public void enterLottery(Lottery lottery) {

    }

    public void enterAdminInfo(String adminId, String adminPw) {

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
