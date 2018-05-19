import java.util.ArrayList;
import java.util.Date;

public class Account {

    private Bank bank;
    private String accountNumber;
    private int password = 0;
    private int balance = 0;
    private boolean state = true;
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private transient DataStore datastore = new DataStore();


    public void Account(String accountNo)
    {
        this.accountNumber = new String(accountNo);
    }

    public void changeBalance(int cashAmount)
    {
        this.balance +=cashAmount;
    }


    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
    }

    public boolean checkAccountPassword(int password)
    {
        return this.password == password;
    }

    public void freezeAccount()
    {
        if(this.state == false)
        {
            return;
        }
        else
        {
            this.state = false;
        }
    }

    public Transaction[] getTransactions(Date startDateTime, Date endDateTime)
    {
        // 최근거래 50개까지만 출력
        Transaction[] list = new Transaction[50];

        for(int i = 0; i<transactions.size();i++)
        {
            if( transactions.get(i).getTime().compareTo(startDateTime) > 0 ||
                    transactions.get(i).getTime().compareTo(endDateTime) < 0 )
            {
                list[i] = transactions.get(i);
            }
        }
        return list;
    }

    public int getBalance()
    {
        return this.balance;
    }

    public Bank getBank(){
        return this.bank;
    }

    public String getAccountNo(){
        return this.accountNumber;
    }

    public void saveAccount()
    {
        datastore.saveAccountData(this);
    }

}
