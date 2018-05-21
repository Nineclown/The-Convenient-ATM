import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;



@RunWith(SerenityRunner.class)
public class SystemBalanceTest {

    @Steps

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test(expected = AdminAlarmException.class)
    public void throwAdminAlarmExceptionTest() throws AdminAlarmException, OverflowBillException{
        int [] balance = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
        int [] input = {40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        SystemBalance sysbal = new SystemBalance(100, 80, 20, 0);
        sysbal.setATMBalance(balance);
        sysbal.changeSystemBalance(input);
    }

    @Test(expected = OverflowBillException.class)
    public void throwOverflowBillExceptionTest() throws AdminAlarmException, OverflowBillException{
        int [] balance = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
        int [] input = {60, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        SystemBalance sysbal = new SystemBalance(100, 80, 20, 0);
        sysbal.setATMBalance(balance);
        sysbal.changeSystemBalance(input);
    }
}
