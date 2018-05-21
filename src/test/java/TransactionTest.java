import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class TransactionTest {
    public DataStore dataStore;

    @Before
    public void initDataStore() {
        this.dataStore = new DataStore();
    }

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void calcFeeTest (){
        Account account = new DataStore().loadAccountData(Bank.WOORI, "110412644105");
        Transaction tr = new Transaction(TransactionType.Deposit);
        tr.setAccount(account);
        tr.setAmount(10000);
        tr.calcFee();
        assertEquals(tr.getAmount(), 9000);
    }

    @Test
    public void processTranscationTest(){

    }
}
