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
    public void selectFunctionCreatesTransactionByType() {

    }

    @Test
    public void enterAccountInfoLoadsAccountData() {

    }

    @Test
    public void enterAccountInfoRaisesExceptionWhenAccountNumberIsInvalid() {

    }

    @Test
    public void enterBillCorrectlyCountsBills() {

    }

    @Test
    public void enterBillDoesNotDoAnythingIfArgumentIsEmpty() {

    }

    @Test
    public void enterBillAsDolarUsesConvertedCashAmount() {

    }

    @Test
    public void enterBillAsDollarCorrectlyCountsBills() {

    }

    @Test
    public void enterBillAsDollarDoesNotDoAnythingIfArgumentIsEmpty() {

    }

    @Test
    public void enterPasswordPassWhenPasswordIsValid() {

    }

    @Test
    public void enterPasswordAccountInfoMustBeExist() {

    }

    @Test
    public void enterPasswordRejectsAfterFiveTimesOfIncorrectness() {

    }
}
