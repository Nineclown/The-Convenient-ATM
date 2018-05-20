import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotEquals;

@RunWith(SerenityRunner.class)
public class SystemTest {

    @Steps
    System sys;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void currencyTest() {
        assertNotEquals(sys.getCurrency(), 0);
    }
}
