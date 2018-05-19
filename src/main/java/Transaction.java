import java.util.Date;

public class Transaction {
    private TransactionType tcType;
    private transient Account account;
    private int amount;
    private Date time;

    public Transaction(TransactionType type){
        this.tcType = type;
    }
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
    public void setTime()
    {
        this.time = new Date();
    }
    public Date getTime()
    {
        return this.time;
    }

    public void processTransaction()
    {
        this.calcFee();
        this.setTime();
        this.account.changeBalance(amount);
        this.account.addTransaction(this);
        this.account.saveAccount();
    }

    public void calcFee()
    {
        int fee;
        if(this.account.getBank() == Bank.HANA)
        {
            if(this.tcType == TransactionType.Deposit)
            {
                fee = 0;
            }
            else
            {
                fee = 500;
            }
        }
        else
        {
            fee = 1000;
        }
        this.amount -= fee;
    }


}

