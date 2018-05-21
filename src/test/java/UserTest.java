import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class UserTest {

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void loadUserTest() {
        DataStore ds = new DataStore();
        User us = ds.loadUserData("9512221234567");
        //No Korean.
        assertEquals(us.getUserName(), "DoHyeon");
        assertEquals(us.getUserId(), "9512221234567");
    }

    @Test
    public void removeCardListTest() {
        DataStore ds = new DataStore();
        User us = ds.loadUserData("9512221234567");
        us.removeCard("1234567890");
        for(int i = 0 ; i < us.getCardList().length ; i++){
            assertNotEquals("1234567890", us.getCardList()[i]);
        }
    }
}
