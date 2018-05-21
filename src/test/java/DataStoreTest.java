import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class DataStoreTest {

    @Steps
    DataStore datastore;

    @Managed(driver = "chrome")
    WebDriver browser;


    @Test (expected = DataStoreTest.class)
    public void loadAccountDataShouldRaiseExceptionWhenAccountIsInvalid() throws DataStoreError{
        Account account = new DataStore().loadAccountData(Bank.HANA, "1234567890");
    }

    @Test (expected = DataStoreError.class)
    public void saveAccountDataCreatesFile() throws DataStoreError {
        Account account = new Account(Bank.KOOKMIN, "1010101010101010");
        account.saveAccount();
        
        File file = new File("data/" + account.getBank() + "/" + account.getAccountNo() + ".json");
        assertTrue(file.exists());
        file.delete();
    }

    @Test (expected = DataStoreError.class)
    public void loadUserDataShouldRaiseExceptionWhenAccountIsInvalid() throws DataStoreError{
        User user = new DataStore().loadUserData("1234567890");
    }


}
