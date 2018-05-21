import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SerenityRunner.class)
public class ATMSystemTest {
    @Test
    public void enterBillCorrectlyCountsBills() {
        ATMSystem system = new ATMSystem();
        int [] billAmount = { 1, 2, 3, 4 };

        try {
            system.enterBill(billAmount);
        } catch (InvalidBillException e ) {

        }

        assertEquals(system.getCashAmount(),  1 * 1000 + 2 * 5000 + 3 * 10000 + 4 * 50000);
    }

    @Test(expected = InvalidBillException.class)
    public void enterBillRaisesExceptionIfLengthIsInvalid() {
        ATMSystem system = new ATMSystem();
        int [] billAmount = { 1, 2 };

        try {
            system.enterBill(billAmount);
        } catch (InvalidBillException e) {
            assertNull("throw InvalidBillException", e);
        }
    }
}
