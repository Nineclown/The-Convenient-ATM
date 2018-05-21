import com.swad.cppatm.application.Account;
import com.swad.cppatm.application.DataStore;
import com.swad.cppatm.application.Transaction;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.enums.TransactionType;
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
        Transaction transaction = new Transaction(TransactionType.Deposit);
        transaction.setAccount(account);
        transaction.setAmount(10000);
        transaction.calcFee();
        assertEquals(transaction.getAmount(), 9000);
    }

    @Test
    public void processTranscationTest(){
        Account account = new DataStore().loadAccountData(Bank.HANA, "123456789012345t");
        Transaction transaction = new Transaction(TransactionType.Deposit);
        transaction.setAccount(account);
        transaction.setAmount(10000);
        try {
            transaction.processTransaction();
        } catch (Exception e) {
            fail("throw Exception");
        }

        assertEquals(account.getTransactions(new Date(118,4,20,0,0,0),new Date(118,4,21,23,0,0)).length,5);
        assertEquals(account.getBalance(),1014200);
        assertEquals(transaction.getTime().after(new Date(118,4,20,0,0,0)),true);
    }
}
