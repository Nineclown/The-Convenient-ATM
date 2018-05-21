import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityRunner.class)
public class AccountTest {

    @Steps
    Account account;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void shouldGetCorrectAccount() {
    Datastore ds = new Datastore();
    Account acc = ds.loadAccountData("HANA","123456789012345");
    assertEquals(acc.getBalance(),1004200);
    assertEquals(acc.getPassword(),5555);
    }

    @Test
    public void shouldGetCorrectBalance()
    {
        Datastore ds = new Datastore();
        Account acc = ds.loadAccountData("HANA","123456789012345");
        acc.changeBalance(5000);
        assertEquals(acc.getBalance(),10555);
    }

    @Test
    public void shouldGetCorrectTranasactions()
    {
        Datastore ds = new Datastore();
        Account acc = ds.loadAccountData("HANA","123456789012345");
        Transaction [] transactions = acc.getTransactions(new Date(2018,5,19,12,0,0),new Date(2018,5,20,0,0,0));
        assertEquals(transactions[1].getAmount(),100);
        assertEquals(transactions[2].getTime(),new Date(2018,5,19,15,16,27 );
        assertEquals(transactions.length,4);
    }

    @Test
    public void shouldAddTransactionCorrectly()
    {
        Transaction tc = new Transaction("DEPOSIT");
        tc.setAccount(new Account("KOOKMIN","15151515");
        tc.setAmount(5100);
        tc.setTime();
        Account acc = ds.loadAccountData("HANA","123456789012345");
        acc.addTransaction(tc);
        Transaction [] transactions = acc.getTransactions(new Date(2018,5,19,12,0,0),new Date(2018,5,30,0,0,0));
        assertEquals(transactions[4].getAmount(),5100);
        )
    }

}
