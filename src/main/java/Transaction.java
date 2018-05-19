import java.util.Date;

public class Transaction {
    private TransactionType type;
    private transient Account  account;
    private int amount;
    private Date time;

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public void setAmount(int cashAmount)
    {
        this.amount = cashAmount;
    }
    public void addAmount(int cashAmount)
    {
        this.amount+=cashAmount;
    }

    public void processTransaction()
    {
        this.calcFee();
        this.setTime();
        this.account.changeBalance(amount);
        account.addTransaction(this);
        account.saveAccount();
    }

    public void calcFee()
    {

    }
    public void setTime()
    {
        this.time = new Date();
    }

    public Date getTime()
    {
        return this.time;
    }


}

