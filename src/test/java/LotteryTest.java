import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

@RunWith(SerenityRunner.class)
public class LotteryTest {

    @Steps
    Account account;

    @Managed(driver = "chrome")
    WebDriver browser;

    @Test
    public void prizeno() {
        int[] num = {1, 2, 3, 4, 5, 7};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 0);
    }

    @Test
    public void prize5() {
        int[] num = {6, 10, 18, 4, 5, 7};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 5000);
    }

    @Test
    public void prize4() {
        int[] num = {6, 10, 18, 25, 5, 7};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 50000);
    }

    @Test
    public void prize3() {
        int[] num = {6, 10, 18, 25, 34, 7};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 500000);
    }

    @Test
    public void prize2() {
        int[] num = {6, 10, 18, 25, 34, 33};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 5000000);
    }

    @Test
    public void prize1() {
        int[] num = {6, 10, 18, 25, 34, 35};
        Lottery lot = new Lottery(807, num);
        assertEquals(lot.checkResult(), 50000000);
    }
}
