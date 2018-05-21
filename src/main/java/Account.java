import java.util.ArrayList;
import java.util.Date;

public class Account {

    private Bank bank;
    private String accountNumber;
    private int password;
    private int balance;
    private boolean state;
    private ArrayList<Transaction> transactions;
    private transient DataStore datastore;

    public void Account(Bank bank,String accountNo)
    {
        this.bank = bank;
        this.accountNumber = accountNo;
        this.password = 0;
        this.balance = 0;
        this.state = true;
        this.transactions = new ArrayList<Transaction>();
    }

    // Getter

    public int getBalance()
    {
        return this.balance;
    }
    public Bank getBank(){
        return this.bank;
    }
    public int getPassword() { return this.password; }
    public boolean isAccountEnabled() { return this.state; }

    // Setter

    public String getAccountNo(){
        return this.accountNumber;
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
        if ( !this.state ) {
            return;
        }
        this.state = false;
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

    public void saveAccount()
    {
        datastore.saveAccountData(this);
    }

}
