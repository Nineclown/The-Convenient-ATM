import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class WhenInterpretingInstructions {

    @Steps
    Interpreting interpreting;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void shouldInterpretingBasedOn() {
        assertEquals(this.interpreting.plus(3, 5), 8);
    }
}
