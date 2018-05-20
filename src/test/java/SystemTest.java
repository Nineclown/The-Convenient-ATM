import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
public class SystemTest {

    @Steps
    System sys = new System();

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void currencyTest() {
        assertEquals(sys.getCurrency(), 1000, 150);
    }
}
