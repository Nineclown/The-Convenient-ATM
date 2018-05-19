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
    public void shouldInterpretingBasedOn() {
        DataStore ds = new DataStore();
        Account acct = ds.loadAccountData("123456789012345");
        System.out.println(acct.getAccountNo());
        Transaction tr = new Transaction();
        tr.setAccount(acct);
        tr.setAmount(100);
        tr.processTransaction();
    }
}
